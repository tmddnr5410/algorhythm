import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        parents = new int[N+1];

        int ans = 0;
        for(int i=1;i<=M;i++){
            stk = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(stk.nextToken()) + 1;
            int y = Integer.parseInt(stk.nextToken()) + 1;

            // 두 노드를 집합에 포함시킬수 없다는건, 사이클이 생긴다는것
            if(!union(x,y)) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    // 해당 노드의 부모를 찾는다.
    private static int findParents(int now){
        if(parents[now] == 0)
            return now;
        return findParents(parents[now]);
    }

    // 두 노드를 같은 집합에 포함시킨다. 같은 부모를 가진다면 사이클을 가지는것
    private static boolean union(int x,int y){
        int xParents = findParents(x);
        int yParents = findParents(y);

        if(xParents == yParents) return false;

        
        if(xParents < yParents){
            parents[yParents] = xParents;
            return true;
        }

        parents[xParents] = yParents;
        return true;
    }
}
