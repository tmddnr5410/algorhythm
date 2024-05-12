import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0,1,0,-1};
    private static final int[] dy = {1,0,-1,0};
    private static int[][] DP;
    private static int[][] map;
    private static boolean[][] visit;
    private static int N,M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        DP = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        DFS(0,0);
        System.out.println(DP[0][0]);

    }
    static int DFS(int x,int y){
        // 종점에 도달시 1 리턴
        if(x == N-1 && y == M-1)
            return DP[x][y] = 1;

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            // 지도 밖으로 나가거나, 내려가지 않는 정점은 방문하지 않는다.
            if(nx < 0 || nx>=N || ny < 0 || ny >= M) continue;
            if(map[x][y] <= map[nx][ny]) continue;

            // 계산한 적이 있는 경로를 더한다
            if(visit[nx][ny]){ DP[x][y] += DP[nx][ny];}

            // 방문한적 없는 정점은 새로 계산한다.
            else{
                visit[nx][ny] = true;
                DP[x][y] += DFS(nx,ny);
            }
        }
        return DP[x][y];
    }
}
