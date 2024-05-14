import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());

        // N라는 수를 K개로 표현했을때 가지수
        long DP[][] = new long[N+1][K+1];

        // 모든 수는 1가지 방법으로 자신을 표현 가능
        // 또한 i+1 가지 방법으로 자신을 표현 가능
        for(int i=0;i<=N;i++) {
            DP[i][1] = 1;
            //
            // DP[i][2] = i+1;
        }

        // i번 숫자를 j개로 만드는 방법 : m~i 까지를 j-1개로 만드는 모든 경우의 수를 더한다
        for(int i=0;i<=N;i++){
            for(int j=2;j<=K;j++){
                for(int m=0;m<=i;m++){
                    DP[i][j] = (DP[i][j] + DP[m][j-1])%1000000000;
                }
            }
        }

        System.out.println(DP[N][K]);
    }
}
