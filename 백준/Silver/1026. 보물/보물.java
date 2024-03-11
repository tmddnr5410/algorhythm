import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++)
            A.add(Integer.parseInt(stk.nextToken()));
        stk = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++)
            B.add(Integer.parseInt(stk.nextToken()));

        Collections.sort(A);
        Collections.sort(B);

        int sum=0;
        for(int i=0;i<N;i++){
            sum += A.get(i) * B.get(N-1-i);
        }

        System.out.println(sum);
    }
}
