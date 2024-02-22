
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Dot{
	int x;
	int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

public class Main {
	private static final int[] dx = {0,1,0,-1};
	private static final int[] dy = {1,0,-1,0};
	private static int R,C,time;
	private static boolean find;
	private static char[][] map;
	private static Queue<Dot> gosm = new LinkedList<Dot>();
	private static Queue<Dot> water = new LinkedList<Dot>();
	private static Queue<Dot> nextGosm = new LinkedList<Dot>();
	private static Queue<Dot> nextWater = new LinkedList<Dot>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		Dot end;
		
		map = new char[R][C];
		
		for(int i=0;i<R;i++) {
			String line = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S') {
					gosm.add(new Dot(i, j));
				}
				else if(map[i][j] == '*') {
					water.add(new Dot(i, j));
				}
				else if(map[i][j] == 'D' ) {
					end = new Dot(i, j);
				}
			}
		}

		time = 0;
		find = false;
		while(true) {
			time++;
//			System.out.println(time+"초에서");

			if(gosm.isEmpty()) {
				bw.write("KAKTUS");
				break;
			}
			
			flood();
			//printMap();
			
			moveGosm();
			if(find) {
//			System.out.println("!!!!찾았다!!!!");
//			System.out.println("!!!!찾았다!!!!");
//			System.out.println("!!!!찾았다!!!!");
				bw.write(time+"");
				break;
			}
			//printMap();
			
			//System.out.println("====");
		}
		bw.flush();
	}
	
	private static void printMap() {
		for(char[] line:map) {
			for(char ch:line) {
				System.out.print(ch);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void flood() {
		int x,y;

	//	System.out.println("물");
		while(!water.isEmpty()) {
			x = water.peek().getX();
			y = water.peek().getY();
		//	System.out.println(x+","+y+"방문");
			water.poll();

			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(0<=nx && nx<R && 0<= ny && ny< C && (map[nx][ny] == 'S'|| map[nx][ny] == '.') ) {
					map[nx][ny] = '*';
					nextWater.add(new Dot(nx, ny));
				}
			}
		}
		
		water.clear();
		Queue<Dot> temp = nextWater;
		nextWater = water;
		water = temp;
	}
	
	private static void moveGosm() {
		int x,y;
		//System.out.println("고슴도치");
		while(!gosm.isEmpty()) {
			x = gosm.peek().getX();
			y = gosm.peek().getY();
			//System.out.println(x+","+y+"방문");
			gosm.poll();
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(0<=nx && nx<R && 0<= ny && ny< C && (map[nx][ny] == '.' || map[nx][ny]== 'D' ) ) {
					if(map[nx][ny] == 'D' ) {
						find = true;
						return;
					}
					map[nx][ny] = 'S';
					nextGosm.add(new Dot(nx, ny));
				}
			}
		}
		
		gosm.clear();
		Queue<Dot> temp = nextGosm;
		nextGosm = gosm;
		gosm = temp;
	}

}
