import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot {
	int x;
	int y;
	Dot(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

public class Main  {

	private static int N,min,max;
	private static int[][] map;
	private static boolean[][] visited;
	private static final int[] dx = {1,0,-1,0};
	private static final int[] dy = {0,1,0,-1};

	private static Queue<Dot> que  = new LinkedList<>();
	private static ArrayList<Dot> union = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		N =Integer.parseInt(stk.nextToken());
		min =Integer.parseInt(stk.nextToken());
		max =Integer.parseInt(stk.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		
		boolean isMove;
		int day=0;
		
		while(true) {
			isMove = false;
			initVisited();
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j] && findUnion(i,j)) {
						isMove = true;
					}
				}
			}
			
			if(!isMove)
				break;
			
			day++;
		}
		
		System.out.println(day);
		
	}
	
	public static boolean findUnion(int px,int py) {

		union.clear();
		
		int sum=0,nx=0,ny=0, x,y;
		Dot position;
		
		que.add(new Dot(px,py));
		union.add(new Dot(px,py));
		visited[px][py] = true;
		
		while(!que.isEmpty()) {
			position = que.poll();
			x = position.getX();
			y = position.getY();
			sum += map[x][y];
			
			for(int i=0;i<4;i++) {
				nx =x+dx[i];
				ny =y+dy[i];
				// 각 방향에 대해 경로가 있다면 연합은 모두 같은 연합임
				if(0<=nx && nx<N && 0<=ny && ny < N && !visited[nx][ny] && isUnion(x,y,nx,ny)) {
					visited[nx][ny] = true;
					que.add(new Dot(nx,ny));
					union.add(new Dot(nx,ny));
				}
			}
			
		}

		// 연합 크기가 1인경우 연합을 생성하지 못한것
		if(union.size() == 1)
			return false;

		// 아닌 경우에는 연합들의 인구수를 이동
		sum = sum / union.size();
		
		for(Dot p:union) {
			map[p.getX()][p.getY()] = sum;
		}
		
		return true;
	}
	
	public static void initVisited() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static boolean isUnion(int x,int y,int nx,int ny) {
		return min <= Math.abs(map[x][y] - map[nx][ny]) && Math.abs(map[x][y] - map[nx][ny]) <= max;
	}

}
