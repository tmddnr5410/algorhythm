import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	private static int N,M;
	private static ArrayList<Integer>[] quests;
	private static int[] remainSolve;
	private static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		quests = new ArrayList[N+1];
		remainSolve = new int[N+1];
		
		for(int i=1;i<=N;i++) 
			quests[i] = new ArrayList<Integer>();
		
		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			
			int must = Integer.parseInt(stk.nextToken());  // curr을 풀기전에 풀어야할 문제
			int curr = Integer.parseInt(stk.nextToken()); // must 를 풀어야 풀수있는 문제
			
			quests[must].add(curr); // must를 풀면 풀수있는 문제들에 추가
			remainSolve[curr]++;
		}
		
		solve();
		bw.flush();
		bw.close();
	}

	
	private static void solve() throws IOException {
		PriorityQueue<Integer> solveOrder = new PriorityQueue<>();
		
		for(int i=1;i<=N;i++) {
			if(remainSolve[i]==0){
				solveOrder.offer(i);
			}
		}
		
		while(!solveOrder.isEmpty()) {
			int now = solveOrder.poll();
			
			bw.write(now+" ");
			
			for(int quest:quests[now]) {   // now를 풀면 풀수있는 문제들의 남은 문제수를 1씩 줄여준다.
				remainSolve[quest]--;
				if(remainSolve[quest] == 0) // 해당 문제를 풀수있으면 큐에 삽입
					solveOrder.offer(quest);
			}
		}
	}
}


