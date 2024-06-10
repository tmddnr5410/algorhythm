
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ice{
    int x;
    int y;

    public Ice(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

public class Main {
    private static final int[] dx = {0,1,0,-1};
    private static final int[] dy = {1,0,-1,0};

    private static int[][] map;
    private static int[][] temp;
    private static int N,M;

    private static boolean[][] visit;

    private static Queue<Ice> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk  = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        temp = new int[N][M];
        visit = new boolean[N][M];
        que = new LinkedList<>();

        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }


        int split = 0;
        int year = 0;
        boolean allMelt = true;

        while(true){
            allMelt = true;
            split = 0;
            visit = new boolean[N][M];

            // 방문하지 않은 빙하 조각들에 대한 BFS를 진행한다.
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j] == 0) continue;
                    if(visit[i][j]) continue;
                    BFS(i,j);
                    split++;
                    allMelt = false;
                }
            }

//            System.out.println(year+"년째 "+split+" 개의빙하조각");
//            for(int i=0;i<N;i++){
//                for(int j=0;j<M;j++){
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }


            // 빙하 조각이 2개이상이거나, 모두 녹았다면 종료
            if(allMelt || split >= 2)
                break;

            // 1년이 지난다.
            year++;

            // 빙하가 녹는다
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    // temp에 다음 얼음의 높이를 구해놓는다.
                    temp[i][j] = map[i][j];
                    if(temp[i][j] == 0) continue;

                    // 각 4방향에 대한 얼음 녹이기
                    for(int d=0;d<4;d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                        if (map[nx][ny] == 0) temp[i][j]--;
                        if (temp[i][j] == 0) break;
                    }
                }
            }

            // 빙하가 녹은 상태를 현재 map에 덮어씌운다
            map = temp;
            temp = new int[N][M];
        }

        // 모두 녹아서 종료된 경우 0을 출력하도록
        if(allMelt) year = 0;

        System.out.println(year);

    }

    private static void BFS(int x,int y){
        que.clear();
        que.add(new Ice(x,y));
        visit[x][y] = true;

        while (!que.isEmpty()){
            Ice now = que.poll();

            for(int d=0;d<4;d++){
                int nx = now.x+dx[d];
                int ny = now.y+dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;

                visit[nx][ny] = true;
                que.add(new Ice(nx,ny));
            }

        }
    }

}
