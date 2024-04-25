import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    private static int N;
    private static HashSet<Integer> set;
    private static int[] roma = {1,5,10,50};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        set = new HashSet<>();

        DFS(0,0,0);

        System.out.println(set.size());
    }

    public static void DFS(int depth,int now,int start){
        if(depth == N) {
            set.add(now);
            return;
        }

        for(int i=start;i<4;i++)
            DFS(depth + 1, now + roma[i],i);

    }
}
