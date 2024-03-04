import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int cost;
	
	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
	
}

public class Main {
	private static int N,M,sum;
	private static int[] parent;
	private static ArrayList<Edge> edges;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		edges = new ArrayList<>();
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			int cost = Integer.parseInt(stk.nextToken());
			edges.add(new Edge(start, end, cost));
		}
		Collections.sort(edges);
		
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		}
		
        int cnt = 0;
		for(int i=0;i<M;i++){
			if(cnt == N-2) break;
            
			int start = edges.get(i).start;
			int end = edges.get(i).end;
			int cost = edges.get(i).cost;
			
			int startP = findParent(start);
			int endP = findParent(end);
			
			if(startP == endP) continue;
			
			union(startP,endP);
			cnt++;
			sum += cost;
		}
		System.out.println(sum);
	}
	
	private static int findParent(int now) {
		if(parent[now] == now)
			return now;
		else
			return findParent(parent[now]);
	}
	
	private static void union(int a,int b) {
		if(a>b) 
			parent[b] = a;
		else
			parent[a] = b;
	}
	

}
