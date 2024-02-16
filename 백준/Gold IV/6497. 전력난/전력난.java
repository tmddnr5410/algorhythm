import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	private int start;
	private int end;
	private int weight;
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getWeight() {
		return weight;
	}
	public Edge(int start, int end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}
	
	
	
}

public class Main {
	private static int M;
	private static int[] graph;
	private static boolean[] visit;
	private static boolean isCycle;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		
		while(true) {
			stk = new StringTokenizer(br.readLine()," ");
			// 집의 수 M , 간선수 N
			M = Integer.parseInt(stk.nextToken());
			int N = Integer.parseInt(stk.nextToken());
			
			// 입력이 끝나면 끝
			if(M == 0 && N == 0) break;

			// 모든 그래프의 부모를 자신으로 초기화
			graph= new int[M];
			for(int i=0;i<M;i++)
				graph[i] = i;
			
			// 간선이 최소 정렬하는 과정
			PriorityQueue<Edge> edges = new PriorityQueue<>();
			
			int weightSum=0;
			for(int i=0;i<N;i++){
				stk = new StringTokenizer(br.readLine()," ");
				
				int start = Integer.parseInt(stk.nextToken());
				int end = Integer.parseInt(stk.nextToken());
				int weight = Integer.parseInt(stk.nextToken());
				
				weightSum += weight;
				edges.add(new Edge(start, end, weight));
			}
			
			// 크루스칼 알고리즘 시작
			int edgeCount=0, minWeight=0;
			while(true) {
				if(edgeCount == M-1)
					break;
				// 현재 가장 최소간선을 큐에서 빼냄
				Edge now = edges.poll();
				
				int x = findParent(now.getStart());
				int y = findParent(now.getEnd());
				
				// 부모노드가 같으면 싸이클 생길 가능성이 있으니 넘어감
				if(x == y)
					continue;
				
				// x,y의 부모를 기록하는 함수
				union(x,y);
				
				minWeight += now.getWeight();
				edgeCount++;
			}
			// 전체 전력량 - 만들수있는 최소 전력랑 = 아낀 최대 전력량
			bw.write(weightSum - minWeight+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	// 부모노드가 더 작은것을 기록
	private static void union(int x,int y) {
		if(x>y)
			graph[x] = y;
		else
			graph[y]=x;
	}
	
	private static int findParent(int now) {
		if(graph[now] == now) {
			return now;
		}
		return findParent(graph[now]);
	}

}
