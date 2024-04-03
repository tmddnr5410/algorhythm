import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Dust{
	int x;
	int y;
	int spread;
	
	public Dust(int x, int y,int spread) {
		this.x = x;
		this.y = y;
		this.spread = spread;
	}
	
}

public class Main {
	private static int[][] map;
	private static int head_x,head_y,bot_x,bot_y;
	private static int R,C,T;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	

	private static int[] clock_dx = {0,-1,0,1};
	private static int[] clock_dy = {-1,0,1,0};



	private static int[] reverse_dx = {1,0,-1,0};
	private static int[] reverse_dy = {0,1,0,-1};

	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		T = Integer.parseInt(stk.nextToken());
		
		map = new int[R][C];
		head_x = -1;
		for(int i=0;i<R;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == -1) {
					if(head_x == -1) {
						head_x = i;
						head_y = j;
						bot_x = i+1;
						bot_y = j;
					}
				}
			}
		}
		
		for(int sec=0;sec<T;sec++) {
			spread();
			cleanHead();
			cleanBot();
		}
		int answer = remainDust();
		System.out.println(answer);
		
	}
	private static void printMap() {
		System.out.println();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) { 
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static int remainDust() {
		int sum = 0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) { 
				if(map[i][j] == -1) continue;
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	private static void spread() {
		Queue<Dust> que = new LinkedList<Dust>();
		
		// 큐에 확산해야할 먼지의 위치와 확산량을 저장한다.
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) { 
				if(map[i][j] > 0) {
					que.add(new Dust(i, j, map[i][j]));
					map[i][j] = 0;
				}
			}
		}
		
		// 먼지를 확산시킨다.
		while(!que.isEmpty()) {
			Dust now = que.poll();
			int remain = now.spread;
			
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				
				if(nx < 0 || R <= nx || ny < 0 || C <= ny) continue;
				if(map[nx][ny] == -1) continue;
				
				// 먼지를 각 방향에 확산시키고, 남은양을 계산한다.
				map[nx][ny] += now.spread / 5;
				remain -= now.spread / 5;
			}
			
			map[now.x][now.y] += remain;
	
		}
		
		
	}

	private static void cleanHead() {
		int x = head_x;
		int y = head_y;
		int preX;
		int preY;
		
		int d=0;

		if(y == 0)
			d=1;
		if(x == 0)
			d=2;
		if(y == C)
			d=3;

		if(x+clock_dx[d] < 0 || head_x+1 <= x+clock_dx[d] || y+clock_dy[d] < 0 || C <= y+clock_dy[d]) {
			d = (d+1)%4;
		}
		
		x = x+clock_dx[d];
		y = y+clock_dy[d];
		
		while(true) {
			if(x+clock_dx[d] < 0 || head_x+1 <= x+clock_dx[d] || y+clock_dy[d] < 0 || C <= y+clock_dy[d]) {
				d = (d+1)%4;
			}
			
			preX = x;
			preY = y;
			
			x = x+clock_dx[d];
			y = y+clock_dy[d];
			
			if(x == head_x && y == head_y) {
				map[preX][preY] = 0;
				break;
			}

			map[preX][preY] = map[x][y];
			
		}
	}
	
	private static void cleanBot() {
		int x = bot_x;
		int y = bot_y;
		int preX;
		int preY;
		int d=3;

		if(y == C)
			d=2;
		if(x == R)
			d=1;
		if(y == 0)
			d=0;

		if(x+reverse_dx[d] < bot_x || R <= x+reverse_dx[d] || y+reverse_dy[d] < 0 || C <= y+reverse_dy[d]) {
			d = (d+1)%4;
		}

		x = x+reverse_dx[d];
		y = y+reverse_dy[d];
		
		while(true) {
			if(x+reverse_dx[d] < bot_x || R <= x+reverse_dx[d] || y+reverse_dy[d] < 0 || C <= y+reverse_dy[d]) {
				d = (d+1)%4;
			}
			
			preX = x;
			preY = y;
			
			x = x+reverse_dx[d];
			y = y+reverse_dy[d];
			
			if(x == bot_x && y == bot_y) {
				map[preX][preY] = 0;
				break;
			}

			map[preX][preY] = map[x][y];
			
		}
	}
	
}
