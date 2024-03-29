import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Box{
	int x;
	int y;
	int scope;
	public Box(int x, int y, int scope) {
		this.x = x;
		this.y = y;
		this.scope = scope;
	}
	
}

public class Solution {
	private static int[][] map;
	private static Queue<Box> que;
	private static int N,W,H;
	private static int totalBox,max;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		que = new LinkedList<>();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());
			map = new int[H][W];
			que.clear();
			totalBox = 0;
			for(int i=0;i<H;i++) {
				stk = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if(map[i][j] != 0) totalBox++;
				}
			}
			max = 0;
			DFS(0,0);
			bw.write("#"+tc+" "+(totalBox-max)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void DFS(int depth,int count) {
		if(depth == N) {
			max = Math.max(max, count);
			return;
		}
		// 0 - W 
		int[][] nowMap = copyMap();
		for(int ball = 0; ball<W; ball++) {
			que.clear();
			
			int h=0;
			int remove = 0;
			// 폭탄을 터트리고 몇개 터트렸는지 저장한다.
			for(h=0;h<H;h++) {
				if(map[h][ball] != 0){
					remove = boom(h, ball);
					break;
				}
			}
			
			// 터트릴 박스가 없으면 구슬을 쏘지 않는다.
			// h == H?

			// 박스를 내린다.
			gravity();
			
			
			DFS(depth+1,count+remove);
			
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					map[i][j] = nowMap[i][j];
				}
			}
			
		}
	}

	private static int boom(int x,int y) {
		// 폭발이 시작한 자리를 세고 시작
		int count = 1;
		
		que.add(new Box(x, y, map[x][y]));
		map[x][y] = 0;
		
		while(!que.isEmpty()) {
			Box now= que.poll();

			for(int i=1;i<now.scope;i++) {
				for(int d=0;d<4;d++) {
					int nx = now.x + dx[d]*i;
					int ny = now.y + dy[d]*i;
					if(nx < 0 || H <= nx || ny < 0 || W <= ny) continue;
					if(map[nx][ny] != 0) {
						count++;
						// 스코프가 1보다 큰경우 연쇄 폭발을 위해 큐에 저장
						if(map[nx][ny] != 1) que.add(new Box(nx,ny,map[nx][ny]));
						
						map[nx][ny] = 0; // 폭발 처리
					}
				}
			}
			
		}
		
		return count;
	}
	
	private static void gravity() {
		for(int y=0;y<W;y++) {
			int bot = H;
			
			// 박스가 붙어야할 위치를 찾는다.
			while(0 <= --bot) 
				if(map[bot][y] == 0) break;
			
			// 떨어져야할 박스를  하나씩 떨어트린다미어.
			int drop = bot;
			while(0 <= --drop) {
				if(map[drop][y] != 0) {
					map[bot][y] = map[drop][y];
					map[drop][y] = 0;
					bot--; // 박스를 떨어트리고 떨어지는 위치를 하나 올린다.
				}
					
			}
		}
	}
	
	private static int[][] copyMap(){
		int[][] arr = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				arr[i][j] = map[i][j];
			}
		}
		return arr;
	}
	
}
