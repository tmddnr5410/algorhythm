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
        int start=0,end=0;
        int[] arr = new int[N];

        stk = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++)
            arr[i] = Integer.parseInt(stk.nextToken());


        int count = 0;
        if(arr[end]==1) count++;

        int min = Integer.MAX_VALUE;
        while(true){
            // 현재 K개이상의 라이언을 포함한다면 현재 길이를 최소로 갱신
            if(count >= K)
                min = Math.min((end-start)+1,min);
            
            // 라이언을 찾기위해 구간을 늘린다.
            if(count < K) {
                end++;
                if(end == N) break;
                if(arr[end] == 1)
                    count++;
            }
            // 이미 라이언을 찾았기 때문에 구간을 줄여본다
            else {
                if(arr[start] == 1)
                    count--;
                start++;
            }

        }
        
        // 최솟값을 찾지 못했다면 -1
        if(min == Integer.MAX_VALUE) min = -1;
        
        System.out.println(min);
    }
}
