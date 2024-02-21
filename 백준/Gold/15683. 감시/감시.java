import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Dot{
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

public class Main {
	private static int N,M;
	private static ArrayList<Dot>[] CCTVs;
	private static int[][] map;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	private static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		CCTVs = new ArrayList[6];
		min = 100;
		
		for(int i=1;i<=5;i++)
			CCTVs[i] = new ArrayList<>(); 
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j]= Integer.parseInt(stk.nextToken());
				if(map[i][j]!=0 && map[i][j]!= 6)
					CCTVs[map[i][j]].add(new Dot(i, j));
			}
		}
		
        // 5번 CCTV는 회전하지 않음
		for(int i=1;i<=5;i++)
			for(Dot p:CCTVs[5]) {
				for(int d=0;d<4;d++) {
					check(p.getX(), p.getY(), true, 5, d);
				}
		}
		
		DFS(4, 0,7);
		System.out.println(min);
	}
	
    // cctv : 현재 cctv 
    // idx : 현재 종류의 몇번 cctv를 놓아볼것인가
    // check : 모든 cctv를 놓아볼때 각자 다른 cctv로 가정해야 다음 같은 cctv종류끼리 중복되지 않음
	private static void DFS(int cctv,int idx,int check) {
		if(cctv == 0) {
			min = Math.min(min, sagakCnt());
			return;
		}
		
		if(idx == CCTVs[cctv].size()) {
			DFS(cctv-1, 0,check+1);
			return;  // <- ?
		}

		Dot now = CCTVs[cctv].get(idx);
		
		switch(cctv) {
			case 4:
				for(int d=0;d<4;d++) {
					check(now.getX(), now.getY(), true, check, d);
					check(now.getX(), now.getY(), true, check, (d+1)%4);
					check(now.getX(), now.getY(), true, check, (d+2)%4);
					DFS(cctv, idx+1,check+1);
					check(now.getX(), now.getY(), false, check, d);
				}
				break;
		
			case 3:
				for(int d=0;d<4;d++) {
					check(now.getX(), now.getY(), true, check, d);
					check(now.getX(), now.getY(), true, check, (d+1)%4);
					DFS(cctv, idx+1,check+1);
					check(now.getX(), now.getY(), false, check, d);
					check(now.getX(), now.getY(), false, check, (d+1)%4);
				}
			break;
		
			case 2:
				for(int d=0;d<2;d++) {
					check(now.getX(), now.getY(), true, check, d);
					check(now.getX(), now.getY(), true, check, d+2);
					DFS(cctv, idx+1,check+1);
					check(now.getX(), now.getY(), false, check, d);
					check(now.getX(), now.getY(), false, check, d+2);
				}
				break;
		
			case 1:
				for(int d=0;d<4;d++) {
					check(now.getX(), now.getY(), true, check, d);
					DFS(cctv, idx+1,check+1);
					check(now.getX(), now.getY(), false, check, d);
				}
				break;

		}
		
	}
	
	private static void check(int x,int y,boolean isSee,int cctv,int direction) {
		int check = cctv,unCheck = 0;
		if(!isSee) {
			check = 0;
			unCheck = cctv;
		}
		
		int nx=x,ny=y;
		while(true) {
			nx = nx + dx[direction];
			ny = ny + dy[direction];
			if(!isValid(nx, ny) || map[nx][ny] == 6)
				return;
			
			if(map[nx][ny] == unCheck )
				map[nx][ny] = check;
		}
	}
	
	private static int sagakCnt() {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
	
	private static boolean isValid(int x,int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
