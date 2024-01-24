
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static int minTime,N,K;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		
		
		int now;
		Queue<Integer> que = new LinkedList<>();
		int[] visited = new int[100001];
		
		que.add(N);
		
		while(!que.isEmpty()) {
			now = que.poll();
			if(now == K) {
				System.out.println(visited[now]);
				return;
				}
			
			if(now-1 >= 0 && visited[now-1]==0) {
				visited[now-1] = visited[now] + 1;
				que.add(now-1);
			}
			
			if(now+1 < 100001 && visited[now+1]==0) {
				visited[now+1] = visited[now] +1;
				que.add(now+1);
			}
			
			if(now*2 < 100001 && visited[now*2]==0) {	
				visited[now*2] = visited[now] +1;
				que.add(now*2);
			}
			
		}
		
		

		
		
	}

}
