import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore.PrivateKeyEntry;
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
	@Override
	public String toString() {
		return "Dot [x=" + x + ", y=" + y + "]";
	}
	
}

public class Main {
	private static int N,M;
	private static ArrayList<Dot>[] CCTVs;
	private static int[][] map;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	private static int zeroCnt = 0;
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
				if(map[i][j]== 0)
					zeroCnt++;
				if(map[i][j]!=0 && map[i][j]!= 6)
					CCTVs[map[i][j]].add(new Dot(i, j));
			}
		}
		
		for(int i=1;i<=5;i++)
			for(Dot p:CCTVs[5]) {
				for(int d=0;d<4;d++) {
					check(p.getX(), p.getY(), true, 5, d);
				}
		}
			
		//for(int[] line:map)
		//	System.out.println(Arrays.toString(line));
		
		DFS(4, 0,7);
		System.out.println(min);
	}
	
	private static void DFS(int cctv,int idx,int check) {
		//System.out.println(cctv);
		if(cctv == 0) {
			min = Math.min(min, sagakCnt());
//			for(int[] line:map)
//				System.out.println(Arrays.toString(line));
//			System.out.println();
			return;
		}
		
		if(idx == CCTVs[cctv].size()) {
			DFS(cctv-1, 0,check+1);
			return;  // <- ?
		}

		Dot now = CCTVs[cctv].get(idx);
		
		if(cctv == 4) {
			for(int d=0;d<4;d++) {
				check(now.getX(), now.getY(), true, check, d);
				check(now.getX(), now.getY(), true, check, (d+1)%4);
				check(now.getX(), now.getY(), true, check, (d+2)%4);
				DFS(cctv, idx+1,check+1);
				check(now.getX(), now.getY(), false, check, d);
			}
		}
		else if(cctv == 3) {
			for(int d=0;d<4;d++) {
				check(now.getX(), now.getY(), true, check, d);
				check(now.getX(), now.getY(), true, check, (d+1)%4);
				DFS(cctv, idx+1,check+1);
				check(now.getX(), now.getY(), false, check, d);
				check(now.getX(), now.getY(), false, check, (d+1)%4);
			}
		}
		else if(cctv == 2) {
			for(int d=0;d<2;d++) {
				check(now.getX(), now.getY(), true, check, d);
				check(now.getX(), now.getY(), true, check, d+2);
				DFS(cctv, idx+1,check+1);
				check(now.getX(), now.getY(), false, check, d);
				check(now.getX(), now.getY(), false, check, d+2);
			}
		}
		else if(cctv == 1) {
			for(int d=0;d<4;d++) {
				check(now.getX(), now.getY(), true, check, d);
				DFS(cctv, idx+1,check+1);
				check(now.getX(), now.getY(), false, check, d);
			}
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
	
	private static void checkRight(int x,int y,boolean isSee,int cctv) {
		int check = cctv,unCheck = 0;
		if(!isSee) {
			check = 0;
			unCheck = cctv;
		}
		
		int nx,ny;
		for(int j=y+1;j<M;j++) {
			
			if(!isValid(x, j) || map[x][j] == 6)
				return;
			
			if(map[x][j] == unCheck )
				map[x][j] = check;
		}
	}
	
	
	private static void checkDown(int x,int y,boolean isSee,int cctv) {
		int check = cctv,unCheck = 0;
		if(!isSee) {
			check = 0;
			unCheck = cctv;
		}
		
		for(int i=x+1;i<N;i++) {
			if(!isValid(i, y) || map[i][y] == 6)
				return;
			
			if(map[i][y] == unCheck )
				map[i][y] = check;
		}
	}

	private static void checkLeft(int x,int y,boolean isSee,int cctv) {
		int check = cctv,unCheck = 0;
		if(!isSee) {
			check = 0;
			unCheck = cctv;
		}
		
		for(int j=y-1;0<=j;j--) {
			if(!isValid(x, j) || map[x][j] == 6)
				return;

			if(map[x][j] == unCheck)
				map[x][j] = check;
		}
	}
	
	private static void checkUp(int x,int y,boolean isSee,int cctv) {
		int check = cctv,unCheck = 0;
		if(!isSee) {
			check = 0;
			unCheck = cctv;
		}
		
		for(int i=x-1;0<=i;i--) {
			if(!isValid(i, y) || map[i][y] == 6)
				return;
			if(map[i][y] == unCheck)
				map[i][y] = check;
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
