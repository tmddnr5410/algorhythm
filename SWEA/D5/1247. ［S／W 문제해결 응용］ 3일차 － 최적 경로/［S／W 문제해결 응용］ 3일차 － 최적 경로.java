import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class dot{
	private int x;
	private int y;
	public dot(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
	@Override
	public String toString() {
		return "dot [x=" + x + ", y=" + y + "]";
	}
	public void setY(int y) {
		this.y = y;
	}
	
}

public class Solution {
	private static int N,min;
	private static ArrayList<dot> graph;
	private static boolean[] visit;
	private static dot home;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			graph = new ArrayList<>(); 
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			visit = new boolean[N];
			
			stk = new StringTokenizer(br.readLine()," ");
			
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			dot company = new dot(x,y);
	
			x = Integer.parseInt(stk.nextToken());
			y = Integer.parseInt(stk.nextToken());
			home = new dot(x,y);
			
			for(int i=0;i<N;i++) {
				x = Integer.parseInt(stk.nextToken());
				y = Integer.parseInt(stk.nextToken());
				graph.add(new dot(x,y));
			}
			
			// 회사에서 시작
			DFS(company,0,0);
			
			bw.write("#"+tc+" "+min+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void DFS(dot now,int dist,int depth) throws IOException {
		// 현재 정점까지의 거리가 이미 구한 최소거리보다 큰경우 더 가볼 필요가 없음
		if(min < dist) 
			return;
		
		// N개를 모두 탐색했으면, 집으로 가는 거리를 더한다.
		if(depth == N) {
			min = Math.min(min, dist+getDist(now,home));
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(!visit[i]) {
				visit[i] = true;
				dot next = graph.get(i);
				// 다음 정점까지의 거리를 더한후 다음 정점 방문
				DFS(next,dist+getDist(now,next),depth+1);
				visit[i] = false;
			}
		}
	}
	
	private static int getDist(dot p1,dot p2) {
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}

}
