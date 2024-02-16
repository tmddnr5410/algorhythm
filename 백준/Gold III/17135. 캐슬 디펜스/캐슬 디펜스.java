import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Dot{
	private int x;
	private int y;
	private int scope;
	public Dot(int x, int y, int scope) {
		super();
		this.x = x;
		this.y = y;
		this.scope = scope;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getScope() {
		return scope;
	}
	
}

public class Main {
	private static int[][] init,map;
	private static int N,M,D;
	private static int maxEnemy = 0,ans=0;
	private static Stack<Integer> stack;
	private static int[] dx = {0,-1,0};
	private static int[] dy = {-1,0,1};
	private static ArrayList<Dot> shootDot;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		
		init = new int[N][M];
		stack = new Stack<>();
		shootDot = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				init[i][j] = Integer.parseInt(stk.nextToken());
				if(init[i][j] == 1)
					maxEnemy += 1;
			}
		}
		
		DFS(0,0);
		
		System.out.println(ans);
		
		
	}
	
	private static void DFS(int depth,int now) {
		if(depth == 3) {
			gameStart(new int[] {stack.get(0),stack.get(1),stack.get(2)});
			return;
		}
		
		for(int i=now;i<M;i++) {
			stack.add(i);
			 DFS(depth+1,i+1);
			 stack.pop();
		}
	}
	
	private static void printMap() {
		for(int[] line:map) {
			for(int num:line) {
				System.out.print(num);
			}
			System.out.println();
		}
		
	}
	
	private static void gameStart(int[] archers) {
		map = initMap();
		int attack=0;
		for(int time=N-1;0<=time;time--) {
			// 모든 궁수의 화살발사
			shootDot.clear();
			for(int archerY:archers) {
				shootDot.add(BFS(time,archerY));
			}
			enemyAttack();
			attack += liveEnemy(time);
		}
		ans = Math.max(ans, (maxEnemy-attack));
	}
	
	// 화살 쏠 적을 찾아 왼쪽부터 탐색하는 BFS로 1을 만나면 적을 쏘고 바로 리턴
	private static Dot BFS(int x,int y) {
		Queue<Dot> que = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		
		Dot now;
		int nowX,nowY,shootX,shootY;

		que.add(new Dot(x, y,1));
		visit[x][y] = true;
		while(!que.isEmpty()) {
			now = que.poll();
			nowX = now.getX();
			nowY = now.getY();
			
			if(now.getScope() > D)
				break;
			
			if(map[nowX][nowY] == 1) {
				return new Dot(nowX, nowY, 0);
			}

			
			for(int d=0;d<3;d++) {
				shootX = nowX+dx[d];
				shootY = nowY+dy[d];
				if(isValid(shootX,shootY) && !visit[shootX][shootY]) {
					visit[shootX][shootY] = true;
					que.add(new Dot(shootX,shootY,now.getScope()+1));
					visit[shootX][shootY] = false;
				}
			}
			
		}
		return new Dot(-1, -1, 0);
	}

	private static void enemyAttack() {
		for(Dot dot : shootDot) {
			if(dot.getX() == -1)
				continue;
			if(map[dot.getX()][dot.getY()] == 1) {
				map[dot.getX()][dot.getY()] = 0;
			}
		}
	}

	private static int liveEnemy(int time) {
		int sum=0;
		for(int i=0; i<M;i++)
			sum += map[time][i];
		return sum;
	}
	
	private static boolean isValid(int x,int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
	
	

	private static int[][] initMap(){
		int[][] map = new int[N][M];
		
		for(int i=0;i<N;i++) 
			for(int j=0;j<M;j++) 
				map[i][j] = init[i][j];
		
		return map;
	}
}
