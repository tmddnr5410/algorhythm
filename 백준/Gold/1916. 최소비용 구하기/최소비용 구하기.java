import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Edge{
	private int end;
	private int dist;
	public int getEnd() {
		return end;
	}
	public Edge(int end, int dist) {
		this.end = end;
		this.dist = dist;
	}
	public int getDist() {
		return dist;
	}
}

public class Main {
	private static final int MaxCost = Integer.MAX_VALUE;
	private static int N,M;
	private static ArrayList<Edge>[] graph;
	private static int[] dist;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++) 
			graph[i] = new ArrayList<>(); 
		
		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			int dist = Integer.parseInt(stk.nextToken());
			graph[start].add(new Edge(end, dist));
		}
		
		stk = new StringTokenizer(br.readLine()," ");
		int departure = Integer.parseInt(stk.nextToken());
		int destination = Integer.parseInt(stk.nextToken());
		
		System.out.println(dijkstra(departure,destination));
		
	}

    // 출발지 dep와 도착지 dest에 대해 다익스트라 알고리즘을 통해 최소거리를 return 해주는 함수
	private static int dijkstra(int dep,int dest) {
		int[] dist = new int[N+1];
		boolean[] visit = new boolean[N+1];
        
		for(int i=1;i<=N;i++)
			dist[i]= MaxCost; 
        
		for(Edge e:graph[dep])
			dist[e.getEnd()] = e.getDist();
        
		dist[dep] = 0; 
        
		while(true) {
			if(allVisit(visit)) 
				break;
			
			int now = nextVertex(visit,dist);
            
            if(now == -1)
				break;
            
			visit[now] = true; 
			
			for(Edge e:graph[now]) {
				dist[e.getEnd()] = Math.min(dist[e.getEnd()], dist[now]+e.getDist());
			}
		}
		
		
		return dist[dest];
	}
	
    // 방문할 다음 정점을 찾는 함수
    // 방문하지 않은 정점중 최소거리가 되는 정점을 찾음
	private static int nextVertex(boolean[] visit,int[] dist) {
		int min = MaxCost;
		int next = -1;
		for(int i=1;i<=N;i++) {
			if(!visit[i] && dist[i] < min) {
				min = dist[i];
				next = i;
			}
		}
		
		return next;
	}
	
    // 모든 방문을 탐색했는지 확인하는 함수
	private static boolean allVisit(boolean[] visit) {
		for(int i=1;i<=N;i++) {
			if(!visit[i])
				return false;
		}
		return true;
	}

}
