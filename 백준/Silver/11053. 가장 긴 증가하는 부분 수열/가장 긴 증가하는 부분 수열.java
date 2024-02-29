
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		int[] arr = new int[N];
		int[] DP = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			DP[i] = 1;
		}
		
		// i = 0은 1로 고정이기 때문에 고려 X
		// 각 자릿수에 대해 i번째에서 나올수 있는 가장 긴 수열만을 고려
		int max = 1;
		for(int i=1; i<N;i++) {
			for(int j=0;j<i;j++) {
				if(arr[j] < arr[i]) // j번째 숫자에서 i번째 숫자로 수열을 만들수 있다면? ( arr[j] < arr[i] 라면?)
					DP[i] = Math.max(DP[i],DP[j]+1);  // i번째의 최대길이 를 갱신할수 있는지 확인한다.
			}
			max = Math.max(max,DP[i]); // 갱신 후에는 지금까지 구한 모든 수열중 최대인지 확인
		}
		bw.write(max+"");
		bw.flush();
		bw.close();
	}

}
