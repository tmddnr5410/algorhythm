import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static ArrayList<Integer>[] graph; 
	private static int N,min;
	private static int[] peoples;
	private static int[] visit;
	private static Queue<Integer> queue; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		
		queue = new LinkedList<>();
		peoples = new int[N+1];
		visit = new int[N+1];
		graph = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++)
			graph[i] = new ArrayList<>();
		

		stk = new StringTokenizer(br.readLine()," ");
		
		for(int i=1;i<=N;i++)
			peoples[i] = Integer.parseInt(stk.nextToken());  
		
		for(int i=1;i<=N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			int k=Integer.parseInt(stk.nextToken());
			for(int j=0;j<k;j++) {
				graph[i].add(Integer.parseInt(stk.nextToken()));
			}
		}


		for(int i=1;i<=N/2;i++) {
			Comb(1, 0, i);
		}

		
		if(min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}

//	private static void DFS(int idx) {
//		
//		
//		if(idx == N+1)
//			return;
//		
//		
//		visit[idx] = 1;
//
//		
//		for(int i=1;i<=N;i++)
//			System.out.print(visit[i]);
//		System.out.println();
//		
//		DFS(idx+1);
//		visit[idx] = 0;
//		DFS(idx+1);
//	}
	
	private static void Comb(int now,int depth,int R) {
		if(depth == R) {
			int a = BFS(0, 1); 
			int b = BFS(1, 0);
			
			if(a != -1 && b != -1)
				min = Math.min(min, Math.abs(a-b));
			
			return;
		}
		
		for(int i=now;i<=N;i++) {
			visit[i]= 1;
			Comb(i+1, depth+1, R);
			visit[i]= 0;
		}
		
	}
	
	private static int BFS(int district,int other) {
		queue.clear();
		int[] separate = copyVisit();
		int start = -1;
		int total = 0;
		
		for(int i=1;i<=N;i++) {
			if(separate[i] == district) {
				start = i;
				break;
			}
		}
			if(start == -1)
			return -1;
				
		queue.add(start);
		separate[start] = other; 
		total += peoples[start];
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int next:graph[now]) {
				// 아직 방문하지 않은곳은
				if(separate[next] == district) {
					separate[next] = other;
					total += peoples[next];
					queue.add(next);
				}
			}
		}
		
		for(int i=1;i<=N;i++)
			// 가보지못한곳이 있다는건 연결되어 있지 않다는것
			if(separate[i] == district)
				return -1;
		
		return total;
	}
	
	private static int[] copyVisit() {
		int[] temp = new int[N+1];
		for(int i=1;i<=N;i++) {
			temp[i]= visit[i];
		}
		return temp;
	}
	
	
}
