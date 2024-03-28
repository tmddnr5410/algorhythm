import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

class Dot{
	int x;
	int y;
	int key;
	int dist;
	public Dot(int x, int y, int key,int dist) {
		this.x = x;
		this.y = y;
		this.key = key;
		this.dist = dist;
	}
	
}

public class Main {
	private static char[][] map;
	private static boolean[][][] visit;
	private static boolean find;
	private static int N,M,min;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Dot> que = new LinkedList<>();
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		visit = new boolean[129][N][M];
		map = new char[N][M];
		
		int startX = 0,startY = 0;
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<M;j++)
				if(map[i][j] == '0') {
					startX = i;
					startY = j;
				}
		}
		
		find = false;
		min = -1;
		
		que.add(new Dot(startX, startY, 0,1));
		visit[0][startX][startY] = true;
		map[startX][startY] = '.';
		while(!que.isEmpty()) {
			if(find)
				break;
			
			Dot now = que.poll();

			for(int i=0;i<4;i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
				if(map[nx][ny] == '#') continue;

				if(map[nx][ny] == '1') {
					find = true;
					min = now.dist;
					continue;
				}


				if(map[nx][ny] == '.' && !visit[now.key][nx][ny]) {
					visit[now.key][nx][ny] = true;
					que.add(new Dot(nx, ny, now.key,now.dist+1));
					continue;
				}
				else if(97 <= map[nx][ny] && map[nx][ny] <= 102){
					int shift = map[nx][ny] - 'a' + 1;
					int nextKey = (1 << shift);  // 현재 마주친 키의 2진수
					int earnKey = nextKey;
					
					if((now.key&nextKey) == nextKey) // 이미 얻은 키는 얻지않고 방문
						earnKey = 0;
					
					if(!visit[now.key+earnKey][nx][ny]) {
							visit[now.key+earnKey][nx][ny] = true;
							que.add(new Dot(nx, ny, now.key+earnKey,now.dist+1));
					}
				
					
				} else if( 65 <= map[nx][ny] && map[nx][ny] <= 70){
					int shift = map[nx][ny] - 'A' + 1;
					int door = (1 << shift); // 현재 마주친 문의 2진수

					if((now.key&door) == door && !visit[now.key][nx][ny]) {
						visit[now.key][nx][ny] = true;
						que.add(new Dot(nx, ny, now.key,now.dist+1));
					}
				}
				
			}
		}
		
		System.out.println(min);
		
	}

}
