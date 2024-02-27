import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	private int end;
	private int cost;
	public int getEnd() {
		return end;
	}

	public int getCost() {
		return cost;
	}

	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return o.cost-this.cost;
	}
	
	
	
}

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		int V = Integer.parseInt(stk.nextToken());
		int E = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] map = new ArrayList[V+1];
		
		for(int i=1;i<=V;i++)
			map[i] = new ArrayList<>();
		
		
		for(int i=0;i<E;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			
			int start =  Integer.parseInt(stk.nextToken());
			int end =  Integer.parseInt(stk.nextToken());
			int cost =  Integer.parseInt(stk.nextToken());
			
			map[start].add(new Edge(end, cost));
		}
		
		
		for(int i=1;i<=V;i++)
			Collections.sort(map[i]);
		
		
		// dijkstra
		int[] dist = new int[V+1];
		boolean[] visit = new boolean[V+1];
		
		
		// dist배열을 초기화
		
		for(int i=1;i<=V;i++)
			dist[i] = Integer.MAX_VALUE;
		
		for(Edge e:map[K])
			dist[e.getEnd()] = e.getCost();
		
		dist[K] = 0;
		visit[K] = true;

		while(true) {
			// 방문 안한곳 중 최단거리를 방문한다.
			int min = Integer.MAX_VALUE;
			int next = -1;
			
			for(int i=1;i<=V;i++) {
				if(!visit[i] && dist[i] < min) {
					next = i;
					min = dist[i];
				}
			}
            
			if(next == -1)
				break;
			
			visit[next] = true;
			
			// 최단거리를 갱신한다.
			for(Edge e:map[next]) {
				int end = e.getEnd();
				int cost = e.getCost();
				
				// end로의 경로에 대해 next를 통해 더 짧게 갈수 있다면 갱신
				dist[end] = Math.min(dist[end],dist[next]+cost);
			}
		}
		
		for(int i=1;i<=V;i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				bw.write("INF\n");
			}
			else {
				bw.write(dist[i]+"\n");
			}
		}
		
		bw.flush();
	}

}
