import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N까지의 소수들을 찾는 과정
        ArrayList<Integer> pNums = new ArrayList<>();
        for(int i=2;i<=N;i++){
            if(isPrime(i))
                pNums.add(i);
        }

        // 만들어진 소수 리스트중 합이 N인 구간을 찾는다
        int start = 0;
        int end = 0;
        int len = pNums.size();

        int count = 0;

        int sum=0;

        if(N == 1)
            start = 1;
        else
            sum = pNums.get(start);

        while(start <= end){
            // 합이 N인 경우 찾음을 표시하고 다음 구간합을 찾기위해 구간을 줄인다.
            if(sum == N) {
                count++;
                sum -= pNums.get(start);
                start++;
            }
            // 합이 N보다 작다면 구간을 늘린다
            else if(sum < N){
                // 구간의 소수를 모두 고려했으면 종료
                if(end+1 == len)
                    break;
                end ++;
                sum += pNums.get(end);
            }
            // 합이 N보다 크면 구간을 늘린다
            else if(sum > N){
                sum -= pNums.get(start);
                start++;
            }
        }

        System.out.print(count);
    }

    public static boolean isPrime(int num){
        int sqrt = (int) Math.sqrt(num);
        for(int i=2;i<=sqrt;i++){
            if(num%i == 0)
                return false;
        }
        return true;
    }
}
