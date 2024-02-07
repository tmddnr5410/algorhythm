import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class dot implements Comparable<dot>{
	private int x;
	private int y;
	private int value;
	public dot(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public int compareTo(dot o) {
		if(this.value < o.value)
			return -1;
		return 1;
	}
	
}

public class Solution {
	private static int[][] map;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			int N2 = N*N;
			map = new int[N][N];
			
			PriorityQueue<dot> pQue = new PriorityQueue<>();
			
			int num;
			for(int i=0;i<N;i++) {
				stk = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					num = Integer.parseInt(stk.nextToken());
					map[i][j] = num;
					pQue.add(new dot(i,j,num));
				}
			}
			int startX,startY,value;
			
			int max = -1,dist = 0, maxNum = 0;
			
			while(!pQue.isEmpty()) {
				startX = pQue.peek().getX();
				startY = pQue.peek().getY();
				value = pQue.peek().getValue();
				pQue.poll();
				
				// 현재 최대치보다 더 긴 거리를 구할수 있는 정점인가?
				if(N2-value+1 <= max)
					break;
				
				dist = BFS(startX,startY);
				if(max < dist) {
					max=dist;
					maxNum=value;
				}
					
				
			}
			
			bw.write("#"+tc+" "+maxNum+" "+max+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int BFS(int startX,int startY) {
		Queue<dot> queue = new LinkedList<dot>();
		
		
		queue.add(new dot(startX,startY,1));
		
		int x,y,nx,ny,dist=1;
		
		while(!queue.isEmpty()) {
			x = queue.peek().getX();
			y = queue.peek().getY();
			dist = queue.peek().getValue();
			queue.poll();
			
			for(int i=0;i<4;i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(0<=nx && nx<N &&0<=ny && ny<N && map[nx][ny] == map[x][y] + 1) {
					queue.add(new dot(nx, ny, dist+1));
				}
			}
		}
		
		// 마지막 dist는 반드시 최댓값
		return dist;
		
	}

}
