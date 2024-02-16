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
		
		// 입력과 함께 전체 적의 수를 구함
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				init[i][j] = Integer.parseInt(stk.nextToken());
				if(init[i][j] == 1)
					maxEnemy += 1;
			}
		}
		
		// M C 3으로 구할수있는 모든 경우의 수에 대해 궁수를 배치
		DFS(0,0);
		
		System.out.println(ans);
		
		
	}
	
	private static void DFS(int depth,int now) {
		if(depth == 3) {
			// 궁수를 배치하면 게임을 시작
			gameStart(new int[] {stack.get(0),stack.get(1),stack.get(2)});
			return;
		}
		
		for(int i=now;i<M;i++) {
			stack.add(i);
			 DFS(depth+1,i+1);
			 stack.pop();
		}
	}
	
	
	private static void gameStart(int[] archers) {
		// 궁수를 새로 배치했기 떄문에 map을 초기화
		map = initMap();
		
		// 없엔 모든 적을 저장할 변수
		int attack=0;
		
		for(int time=N-1; 0<=time;time--) {
			// 화살을 쏠 위치를 지정함
			shootDot.clear();
			// 모든 궁수가 발사할 화살의 위치를 먼저 구함
			for(int archerY:archers) {
				shootDot.add(BFS(time,archerY));
			}
			// 해당 위치에 화살을 쏨 화살을 쏘는 위치는 겹칠수 있음
			enemyAttack();
			
			// 현재 열에서 죽이지 못한 적의 숫자를 구함
			attack += liveEnemy(time);
		}
		
		// 전체 적의수 - 이번 게임에서 맞은수 = 죽인 모든 적의 수
		ans = Math.max(ans, (maxEnemy-attack));
	}
	
	// 화살 쏠 위치를 찾아주는 함수
	// 화살 쏠 적을 찾아 왼쪽부터 탐색하는 BFS
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
