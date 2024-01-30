import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	private static int[][] map;
	private	static int N;
	private static Deque<int[]> snake;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	private static int direction = 0;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		Queue<Integer> rotateSecs = new LinkedList<>();
		Queue<String> rotateDirections = new LinkedList<>();
		snake = new LinkedList<>();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		
		int round = Integer.parseInt(br.readLine());
		
		for(int i=0; i< round;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			map[Integer.parseInt(stk.nextToken())-1][Integer.parseInt(stk.nextToken())-1] = 2;
		}
		
		round = Integer.parseInt(br.readLine());
	
		for(int i=0; i< round;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			rotateSecs.add((Integer.parseInt(stk.nextToken())));
			rotateDirections.add(stk.nextToken());
		}
		
		

		int sec = 0,nx=0,ny=0;
		snake.addLast(new int[] {nx,ny});
		
		while(true) {
			

			// rotate snake
			if(!rotateSecs.isEmpty() && rotateSecs.peek() == sec) {
				if(rotateDirections.peek().equals("D")) {
					direction = (direction+1)%4;
				}
				else if(rotateDirections.peek().equals("L")){
					if(direction == 0)
						direction = 3;
					else
						direction--;
				}

				rotateSecs.poll();
				rotateDirections.poll();
			}

			
			if(isWall(nx,ny) || isBody(nx,ny))
				break;

			sec++;
			//move snake
			nx += dx[direction];
			ny += dy[direction];
			
			snake.addLast(new int[] {nx,ny});
		
			if(map[nx][ny] == 2)
				map[nx][ny] = 0;
			else
				snake.pollFirst();

		}
		
		System.out.println(sec+1);
		
	}
	
	public static boolean isWall(int x, int y) {
		return x+dx[direction]<0 || N <= x+dx[direction] || y+dy[direction]<0 || N <= y+dy[direction];
	}
	
	public static boolean isBody(int x, int y) {
		int nextX = x+dx[direction];
		int nextY = y+dy[direction];
		
		for(int[] position:snake) {
			if(position[0] == nextX && position[1] == nextY)
				return true;
		}
		
		return false;
	}
	
	
}
