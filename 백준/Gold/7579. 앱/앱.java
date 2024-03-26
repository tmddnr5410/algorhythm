import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
        // 걸릴수 있는 최대 시간 = 100 * 100
		int min = 100000;
		int[][] process = new int[N+1][2];


		stk = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<=N;i++)
			process[i][0] = Integer.parseInt(stk.nextToken());
		
		int totalSec = 0;
		stk = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<=N;i++) {
			process[i][1] = Integer.parseInt(stk.nextToken());
			totalSec += process[i][1];
		}
		
		int[][] DP = new int[N+1][totalSec+1];
		
        // i번째 프로세스 종료시에
		for(int i=1;i<=N;i++) {
			int memory = process[i][0];
			int off = process[i][1];

            // sec초에 얻을수있는 최대 메모리를 계산
			for(int sec=0;sec<=totalSec;sec++) {
				
				if(sec < off)
					DP[i][sec] = DP[i-1][sec];
				else 
					DP[i][sec] = Math.max(DP[i-1][sec-off]+memory,DP[i-1][sec]);
				
				// sec초에 얻은 최대 메모리가 할당받아야할 메모리보다 작거나 같다면
				if(sec < min && M <= DP[i][sec])
					min = sec;
				
				
			}
		}
		System.out.println(min);
	
	}

}
