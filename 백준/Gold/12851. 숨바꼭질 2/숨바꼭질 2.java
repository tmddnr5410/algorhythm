import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(stk.nextToken());
		int end = Integer.parseInt(stk.nextToken());
		boolean[] visit = new boolean[200001];
		
		Queue<int[]> que = new LinkedList<>();
	
		que.add(new int[] {start,0});
		
		int min=Integer.MAX_VALUE,count=0;
		while(!que.isEmpty()) {
			int now = que.peek()[0];
			int sec = que.peek()[1];
			que.poll();
            visit[now]= true;
            // min이 갱신된 이후로 더 큰 시간부터는 계산하지 않아도됨
			if(min < sec)
				break;
			
            // 목적지에서 최솟값을 갱신하고 경로 추가
			if(now == end) {
				min = sec;
				count++;
			}
			
            // 각 경우의수로 탐색
			if(now>0 && !visit[now-1])
				que.add(new int[] {now-1,sec+1});

			if(now+1<=200000 && !visit[now+1])
				que.add(new int[] {now+1,sec+1});
			

			if(now*2<=200000 && !visit[now*2])
				que.add(new int[] {now*2,sec+1});
			
		}
		
		System.out.println(min+"\n"+count);
	}

}
