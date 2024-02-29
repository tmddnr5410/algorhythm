import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N,start,min;
	private static boolean[] visit;
	private static int[][] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		
		visit = new boolean[N];
		visit[0] = true;
		start = 0;
		DFS(0,0,0);
		
		System.out.println(min);
	}
	
	private static void DFS(int now, int cost,int depth) {
		if(min < cost) // 지금까지 구한 비용이 최솟값보다 크면 탐색할 필요가 없음
			return;
		
		if(depth == N-1) {  // 모두 순회한경우
			if(graph[now][start] != 0) { // 마지막 경로가 존재하면  최솟값 갱신
				min = Math.min(min,cost+graph[now][start]);
			}
			return;
		}
		
		for(int next=0;next<N;next++) {
			if(graph[now][next] != 0 && !visit[next]) {
				visit[next] = true;
				DFS(next,cost+graph[now][next],depth+1);
				visit[next] = false;
			}
		}
	}

}
