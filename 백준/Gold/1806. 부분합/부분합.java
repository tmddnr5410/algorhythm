import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken());
        int[] nums = new int[N];

        stk = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++)
            nums[i] = Integer.parseInt(stk.nextToken());


        int start=0;
        int end = 0;
        int min = 100001;
        int now = 1;

        int sum=nums[0];

        
        while(true){
            // start 가 end보다 뒤라면, 길이가 1인 수열을 찾은것
            if(start > end) break;
            
            // 현재 합이 조건을 만족한다면 시작점을 줄여가며 최솟값을 찾는다.
            if(sum >= S) {
                min = Math.min(min, now);
                sum -= nums[start];
                start++;
                now--;
            }
            // 현재 합이 조건보다 작다면, end를 늘려 합을 만족하는 구간을 찾는다.
            else{
                end++;
                if(end == N) break;
                sum += nums[end];
                now++;
            }
        }
        
        if(min == 100001) min = 0;
        System.out.println(min);

    }
}
