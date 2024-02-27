import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;



public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		Queue<int[]> que = new LinkedList<>();
		
		que.add(new int[] {x,0});
		
		int now,depth=0;
		while(!que.isEmpty()) {
			now = que.peek()[0];
			depth = que.peek()[1];
			que.poll();

			if(now == 1)
				break;
			
			if(now%3 == 0)
				que.add(new int[] {now/3,depth+1});
			
			if(now%2 == 0)
				que.add(new int[] {now/2,depth+1});
			
			que.add(new int[] {now-1,depth+1});
			
			
		}
		
		System.out.println(depth);
	}

}
