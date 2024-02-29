import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] DP = new int[N];
		int[] reverseDP = new int[N];
		
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			DP[i] = 1;
			reverseDP[i] = 1;
		}
		
		int last = N-1;
		for(int i=1;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(arr[j] < arr[i])  // j번째 까지의 최대 수열에서 i번째를 선택할수 있다면, 최대 수열길이로 갱신 
					DP[i] = Math.max(DP[i],DP[j]+1);
				if(arr[last-j] < arr[last-i]) // 마지막 문자가 처음이라고 가정하고, 최대수열을 갱신
					reverseDP[last-i] = Math.max(reverseDP[last-i],reverseDP[last-j]+1);
			}
		}
		
		// K번째를 기준으로 나올수있는 최대 길이를 계산
		int max = 0;
		for(int k=0;k<N;k++) {
			// k번 숫자가 겹치니 1을 뺴줌
			max = Math.max(max,DP[k]+reverseDP[k]-1);
		}
		System.out.println(max);
	}

}
