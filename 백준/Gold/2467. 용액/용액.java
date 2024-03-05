import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N,min,f1,f2;
    private static int[] fluids;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fluids = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        for(int i=0;i<N;i++)
            fluids[i] = Integer.parseInt(stk.nextToken());


        min = Integer.MAX_VALUE;

        // i번쨰 용액에 대해 가장 0과 가까운 용액조합을 찾는다.
        // 혼합결과가 0인 용액보다 더 잘 혼합될수 없으니 break
        for(int i=0;i<N-1;i++)
            binarySerach(i,i+1,N-1);

        System.out.print(f1+" "+f2);
    }

    private static boolean binarySerach(int now,int start,int end) {
        while(start <= end) {
            int mid = (start+end)/2;
            int mix = fluids[now] + fluids[mid];


            if(Math.abs(mix) < min){  // 0과 더 가깝게 만드는 조합을 찾으면 갱신
                min = Math.abs(mix);
                f1 = fluids[now];
                f2 = fluids[mid];
            }

            if(mix < 0) // 혼합값이 음수이면 더 큰 양수를 섞어야 0에 가깝게 만들수 있음
                start = mid + 1;

            else // 혼합값이 양수이면 더 큰 음수를 섞어야 0에 가깝게 만들수 있음
                end = mid - 1;
        }

        return false;
    }

}
