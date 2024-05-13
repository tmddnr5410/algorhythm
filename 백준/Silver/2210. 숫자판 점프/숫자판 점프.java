
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static final int N = 5;
    private static HashSet<String> set;
    private static char[][] map;

    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {1,0,-1,0};

    private static char[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        set = new HashSet<>();

        map = new char[N][N];
        for(int i=0;i<N;i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j] = stk.nextToken().charAt(0);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                selected = new char[6];
                selected[0] = map[i][j];
                DFS(i,j,1);
            }
        }

        System.out.println(set.size());
    }

    private static void DFS(int x,int y,int depth){
        if(depth==6){
            set.add(String.valueOf(selected));
            return;
        }

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx <0 || nx >= N || ny <0 || ny>=N )continue;
            selected[depth] = map[nx][ny];
            DFS(nx,ny,depth+1);
        }
    }
}
