import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int HP=0;
	private static final int SCORE=1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] health = new int[N];
		int[] happy = new int[N];
		int[] DP = new int[101];
		
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		StringTokenizer stk2 = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			health[i] = Integer.parseInt(stk.nextToken());
			happy[i] = Integer.parseInt(stk2.nextToken());
		}
	
        // 각 사람과 악수를 했을때 남은 체력에 대한 모든 경우의 수에 대해 갱신
		for(int i=0;i<N;i++) {
			for(int j=1;j<=100;j++) {
				if(j-health[i] > 0) {
					DP[j-health[i]] = Math.max(DP[j-health[i]],DP[j]+happy[i]) ;
				}
			}
		}
		
		System.out.println(DP[1]);
	}

}
