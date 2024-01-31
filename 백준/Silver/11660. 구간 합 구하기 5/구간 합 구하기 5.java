import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		stk = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int temp=0;

		int[][] sum = new int[N+1][N+1];
		for(int i=1;i<N+1;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int k=1;k<N+1;k++) {
				temp = Integer.parseInt(stk.nextToken());
				if(k==0)
					sum[i][k] = temp;
				else
					sum[i][k] = sum[i][k-1]+temp;
			}
		}
		
		
		int startX,endX,startY,endY,answer=0;
		
		for(int j=0;j<M;j++) {
			stk = new StringTokenizer(br.readLine()," ");
			startX = Integer.parseInt(stk.nextToken());
			startY = Integer.parseInt(stk.nextToken());
			endX = Integer.parseInt(stk.nextToken());
			endY = Integer.parseInt(stk.nextToken());

			answer=0;
			for(int s = startX; s < endX+1;s++) {
				answer += sum[s][endY]-sum[s][startY-1];
			}
			bw.write(answer+"\n");
		}

		bw.flush();
	}
}
