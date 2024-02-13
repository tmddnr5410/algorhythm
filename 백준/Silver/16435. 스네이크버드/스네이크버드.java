import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		PriorityQueue<Integer> que = new PriorityQueue<>();
		
		int N,L;
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		
		stk = new StringTokenizer(br.readLine()," ");
		
		// 과일이 정렬된상태로 들어가도록
		for(int i=0;i<N;i++) {
			que.add(Integer.parseInt(stk.nextToken()));
		}
		
		int now;
		
		while(!que.isEmpty()) {
			now = que.poll();
			// 과일을 먹을수 있다면 길이를 늘림
			if(now <= L)
				L++;
			// 먹을수 없다면 그 과일부터는 모두 먹을수 없으니 탈출
			else
				break;
		}
		
		System.out.println(L);
		
		
	}

}
