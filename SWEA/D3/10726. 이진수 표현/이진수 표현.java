
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int T;
        T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            stk = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(stk.nextToken());
            int M = Integer.parseInt(stk.nextToken());
            int check = (1 << N) - 1;
            String ans = "OFF";
            if ((check & M) == check) {
                ans = "ON";
            }
            System.out.printf("#%d %s\n" ,test_case,ans);
        }
    }
}