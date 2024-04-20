import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static char[] arr;
    private static int[] DP;
    private static int[][] isPel;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        N = arr.length;
        DP = new int[N];
        isPel = new int[N][N];

        // DP 배열 = i번쨰 까지 로 만들수 있는 최소 펠린드롬 분할갯수
        for(int i=0;i<N;i++)
            DP[i] = 9999;

        // 0번째 까지의 수열로 만드는 분할갯수는 항상 1
        DP[0] = 1;

        for(int i=1;i<N;i++){
            // 0부터 i번째 까지가 펠린드롬 수이면 최소로 나눈것.
            if(checkPel(0,i) == 1){
                DP[i] = 1;
                continue;
            }
            // i번째까지의 수열을 최소로 나누는 펠린드롬 분할을 계산
            minPelynDiv(i);
        }

        System.out.println(DP[N-1]);
    }

    // idx까지의 수열을 최소의 갯수로 펠린드롬 분할하는 수로 저장
    private static void minPelynDiv(int idx) {
        for(int j=1;j<=idx;j++){
            // j~idx까지가 펠린드롬 수이면, 현재까지 구한 수와, j-1번째 까지의 최소 분할갯수에 1을 더해 비교
            if(checkPel(j,idx) == 1)
                DP[idx] = Math.min(DP[idx],DP[j-1]+1);
        }
    }

    private static int checkPel(int start, int end) {
        if(start > end)
            return 1;
        // 펠린드롬인지 계산한적 있다면 리턴
        if(isPel[start][end] != 0)
            return isPel[start][end];

        // 끝 숫자가 같다면 다음 숫자열이 펠린드롬 인지 확인
        if(arr[start] == arr[end]){
            isPel[start][end] = checkPel(start+1,end-1);
            return isPel[start][end];
        }
        else{ // 끝숫자가 다르면 펠린드롬 숫자가 될수 없음
            isPel[start][end] = -1;
            return -1;
        }
    }
}
