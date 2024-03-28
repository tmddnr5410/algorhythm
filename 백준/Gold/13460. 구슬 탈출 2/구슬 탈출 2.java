import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Balls{
    int red_x;
    int red_y;
    int blue_x;
    int blue_y;
    int dist;

    public Balls(int red_x, int red_y, int blue_x, int blue_y,int dist) {
        this.red_x = red_x;
        this.red_y = red_y;
        this.blue_x = blue_x;
        this.blue_y = blue_y;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Balls{" +
                "red_x=" + red_x +
                ", red_y=" + red_y +
                ", blue_x=" + blue_x +
                ", blue_y=" + blue_y +
                ", dist=" + dist +
                '}';
    }
}

public class Main {
    private static int N,M,min;
    private static char[][] map;
    private static boolean[][][][] visit;
    private static boolean find;
    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new char[N][M];

        // 맵 입력, 공의 시작 위치 확인
        Balls start = new Balls(0,0,0,0,0);
        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<M;j++){
                if(map[i][j] == 'R') {
                    start.red_x = i;
                    start.red_y = j;
                    map[i][j] = '.';
                }

                if(map[i][j] == 'B') {
                    start.blue_x = i;
                    start.blue_y = j;
                    map[i][j] = '.';
                }
            }
        }

        // 공 두개의 현재위치를 저장하기 위한 큐
        Queue<Balls> queue = new LinkedList<>();

        // 공 두개의 현재위치에 대한 방문 여부를 저장할 배열
        visit = new boolean[N][M][N][M];
        visit[start.red_x][start.red_y][start.blue_x][start.blue_y] = true;
        find = false;
        min = -1;
        queue.add(new Balls(start.red_x, start.red_y, start.blue_x, start.blue_y,0));
        while(!queue.isEmpty()){
            if(find)
                break;

            // 현재
            Balls now = queue.poll();
            
            if(now.dist == 10) continue;
            
            // System.out.println(now);
            // 공을 각 방향으로 움직여본다.
            for(int i=0;i<4;i++) {
                boolean blueFirst = isBlueFisrt(i,now);
                boolean redHole = false;
                boolean blueHole = false;

                int red_nx = now.red_x;
                int red_ny = now.red_y;
                int blue_nx = now.blue_x;
                int blue_ny = now.blue_y;

                if(blueFirst) {
                    while(true){
                        blue_nx += dx[i];
                        blue_ny += dy[i];
                        if(map[blue_nx][blue_ny] != '.') {
                            if(map[blue_nx][blue_ny] == 'O') {
                                blueHole = true;
                                blue_nx = 0;
                                blue_ny = 0;
                            }
                            else {
                                blue_nx -= dx[i];
                                blue_ny -= dy[i];
                            }
                            break;
                        }
                    }

                    while(true) {
                        red_nx += dx[i];
                        red_ny += dy[i];
                        if((red_nx == blue_nx && red_ny == blue_ny) || map[red_nx][red_ny] != '.') {
                            if(map[red_nx][red_ny] == 'O') {
                                redHole = true;
                                red_nx = 0;
                                red_ny = 0;
                            }
                            else {
                                red_nx -= dx[i];
                                red_ny -= dy[i];
                            }
                            break;
                        }
                    }
                }
                else {
                    while(true) {
                        red_nx += dx[i];
                        red_ny += dy[i];
                        if( map[red_nx][red_ny] != '.') {
                            if(map[red_nx][red_ny] == 'O') {
                                redHole = true;
                                red_nx = 0;
                                red_ny = 0;
                            }
                            else {
                                red_nx -= dx[i];
                                red_ny -= dy[i];
                            }
                            break;
                        }
                    }

                    while(true){
                        blue_nx += dx[i];
                        blue_ny += dy[i];
                        if((red_nx == blue_nx && red_ny == blue_ny) ||map[blue_nx][blue_ny] != '.') {
                            if(map[blue_nx][blue_ny] == 'O') {
                                blueHole = true;
                                blue_nx = 0;
                                blue_ny = 0;
                            }
                            else {
                                blue_nx -= dx[i];
                                blue_ny -= dy[i];
                            }
                            break;
                        }
                    }
                }
                if(blueHole) continue;

                if(redHole && !blueHole) {
                    find = true;
                    min = now.dist+1;
                    break;
                }

                // 두공이 각 위치에 똑같이 배치된적 있다면 방문하지 않는다.
                if(visit[red_nx][red_ny][blue_nx][blue_ny]) continue;

                visit[red_nx][red_ny][blue_nx][blue_ny] = true;
                queue.add(new Balls(red_nx, red_ny, blue_nx, blue_ny, now.dist+1));
            }

        }

        System.out.println(min);

    }


    private static boolean isBlueFisrt(int d,Balls now){
        if(dx[d] == 1 && now.red_x < now.blue_x){
            return true;
        }

        if(dx[d] == -1 && now.red_x > now.blue_x){
            return true;
        }

        if(dy[d] == 1 && now.red_y < now.blue_y){
            return true;
        }

        if(dy[d] == -1 && now.red_y > now.blue_y){
            return true;
        }

        return false;

    }
}