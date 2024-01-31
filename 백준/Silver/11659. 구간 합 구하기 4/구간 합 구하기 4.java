import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		stk = new StringTokenizer(br.readLine()," ");
		
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		

		int[] sum = new int[N+1];
		sum[0] = 0;
		stk = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<N+1;i++) {
			sum[i] = sum[i-1] + Integer.parseInt(stk.nextToken());
		}
		
		int start,end;
		for(int j=0;j<M;j++) {
			stk = new StringTokenizer(br.readLine()," ");
			start = Integer.parseInt(stk.nextToken());
			end = Integer.parseInt(stk.nextToken());
			System.out.println(sum[end] - sum[start-1]);
		}
		
		
	}

}
