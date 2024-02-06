import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int[] dx = {1,0,-1,0};
	private static int[] dy = {0,1,0,-1};
	private static int N,M,R,d=0;
	private static boolean[][] visited;
	private static int[][] map;
	private static BufferedWriter bw;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());

		int count = Math.min(N, M) /2;
		
		visited = new boolean[N][M];
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for(int j=0;j<R;j++) {
			roundAll(count);
		}
		printMap();
		
		bw.close();
	}
	
	public static void printMap() throws IOException {
		
		for(int[] line:map) {
			for(int num:line) {
				bw.write(num+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
	
	public static void initVisited() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void roundAll(int count) {
		initVisited();
		for(int i=0;i<count;i++) {
			round(i,i);
		}
	}
	
	
	
	public static void round(int startX,int startY) {
		int temp,temp2;
		int x=startX,y=startY;
		
		temp=map[x][y];
		d=0;
		while(true) {
			if(x+dx[d] < 0 || N <= x+dx[d] || y+dy[d] <0 || M <= y+dy[d] || visited[x+dx[d]][y+dy[d]]) {
				d = (d+1)%4;
			}
			
			x = x+dx[d];
			y = y+dy[d];

			if(visited[x][y])
				break;
			

			temp2 = map[x][y];
			map[x][y] = temp;
			temp = temp2;
			
			visited[x][y] = true;
		}
	}
}
