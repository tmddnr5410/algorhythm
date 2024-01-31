import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class node{
	int end,dist;
	public node(int end,int dist) {
		this.end = end;
		this.dist = dist;
	}
	
	public int getEnd() {
		return end;
	}
	public int getDist() {
		return dist;
	}
}

public class Main {
	private static ArrayList<node>[] map;
	private static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new ArrayList[N+1];
		
		for(int s=1;s<N+1;s++) {
			map[s] = new ArrayList<node>();
		}
		
		int start,end,distance,K;
		
		for(int i=0;i<N-1;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			
			start = Integer.parseInt(stk.nextToken());
			end = Integer.parseInt(stk.nextToken());
			distance = Integer.parseInt(stk.nextToken());
			
			map[start].add(new node(end,distance));
			map[end].add(new node(start,distance));
		}
		
		
		for(int j=0;j<M;j++) {
			stk = new StringTokenizer(br.readLine()," ");
			
			K = Integer.parseInt(stk.nextToken());
			start = Integer.parseInt(stk.nextToken());
			
			System.out.println(BFS(K,start));
		}
		
		
		
	}
	
	public static int BFS(int k,int start) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int now,cnt=0;
		int next,distance;
		que.add(start);
		
		
		while(!que.isEmpty()){
			now = que.poll();
			visited[now] = true;
			
			for(node n:map[now]) {
				next = n.getEnd();
				distance = n.getDist();
				if(!visited[next] && distance >= k) {
					cnt++;
					visited[next] = true;
					que.add(next);
				}
			}
			
		}
		return cnt;
	}
	
}
