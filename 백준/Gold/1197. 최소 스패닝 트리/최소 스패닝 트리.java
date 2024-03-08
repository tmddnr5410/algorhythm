import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int weight;
	
	public Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight,o.weight);
	}
	
}

public class Main {
	private static int V,E;
	private static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
	
		parent = new int[V+1];
		
		for(int i=1;i<=V;i++)
			parent[i] = i;
		
		for(int i=0;i<E;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(stk.nextToken());;
			int end= Integer.parseInt(stk.nextToken());;
			int weight= Integer.parseInt(stk.nextToken());;
			
			edges.add(new Edge(start,end,weight));
		}
		
		int cnt=0,totalWeight=0;
		while(cnt < V-1) {
			Edge now = edges.poll();
			
			if(!union(now.start,now.end)) continue; // cycle이 생기는 간선은 추가하지 않음
				
			cnt++;
			totalWeight += now.weight;
		}
		
		System.out.println(totalWeight);
	}
	
	private static int findParents(int now) {
		if(parent[now] == now)
			return now;
		
		return findParents(parent[now]);
	}

	private static boolean union(int start,int end) {
		int startRoot = findParents(start);
		int endRoot = findParents(end);
		
		if(startRoot == endRoot) // cycle이 생기는 간선은 추가하지 않음
			return false;
		
		if(startRoot > endRoot) {  // 부모 노드는 항상 정점번호가 작도록 유지
			int temp = endRoot;
			endRoot = startRoot;
			startRoot = temp;
		}
		
		parent[endRoot] = startRoot;  // 간선을 추가하면 병합성공
		return true;
	}
	
}
