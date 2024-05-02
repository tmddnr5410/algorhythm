import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        int[] nums = new int[N];
        ArrayList<Integer>[] DP = new ArrayList[N];


        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(stk.nextToken());
            DP[i] = new ArrayList();
            DP[i].add(nums[i]);
        }

        int max = 0;
        ArrayList<Integer> maxArr = new ArrayList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                int len = DP[j].size();
                // j번쨰 까지 만든 최대수열에서 증가하며, 
                // i번째까지 만들어본 최대수열보다 길다면 갱신
                if(DP[j].get(len-1) < nums[i] && DP[i].size() < len+1) {
                    DP[i] = new ArrayList<>(DP[j]);
                    DP[i].add(nums[i]);
                }
            }

            if(max < DP[i].size()){
                max = DP[i].size();
                maxArr = DP[i];
            }
        }

        bw.write(max+"\n");
        for(int num:maxArr)
            bw.write(num+" ");
        bw.flush();
        bw.close();
    }
}
