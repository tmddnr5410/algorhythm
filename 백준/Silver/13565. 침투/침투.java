import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {1,0,0,-1};
    private static int[] dy = {0,1,-1,0};
    private static boolean isFind;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk  =new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = line.charAt(j)-'0';
            }
        }

        for(int i=0;i<M;i++){
            // 첫번째 열에서 시작할수 있다면 탐색시작
            if(map[0][i] == 0 && !visit[0][i])
                DFS(0,i);
            if(isFind)
                break;
        }

        if (isFind) System.out.println("YES");
        else System.out.println("NO");

    }

    public static void DFS(int x,int y){
        // 끝에 도달했다면 찾았다는 표시
        if(x == N-1)
            isFind = true;

        // 찾았다면 더 이상 탐색하지 않음
        if(isFind)
            return;

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(map[nx][ny] == 1) continue;
            if(visit[nx][ny]) continue;

            visit[nx][ny] = true;
            DFS(nx,ny);
        }
    }
}
