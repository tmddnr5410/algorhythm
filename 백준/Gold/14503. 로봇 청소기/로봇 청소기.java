import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 북 서 남 동
    private static int[] dx ={-1,0,1,0};
    private static int[] dy ={0,-1,0,1};
    private static int[][] map;

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");

        int N,M;
        int nx,ny,nd;

        int cleanCnt = 0;

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine()," ");
        nx = Integer.parseInt(stk.nextToken());
        ny = Integer.parseInt(stk.nextToken());

        nd = Integer.parseInt(stk.nextToken());
        
        // 문제에서 정해준 방향을 회전 기준으로 변환
        if(nd == 1)
            nd = 3;
        else if(nd == 3)
            nd = 1;

        map = new int[N][M];

        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        while(map[nx][ny] != 1 ){
            if(map[nx][ny] == 0) {
                cleanCnt++;
                map[nx][ny] = 2;
            }

            if(isNearClean(nx,ny)){
                nx -= dx[nd];
                ny -= dy[nd];
            }
            else{
                for(int i=0;i<4;i++) {
                    nd = rotate(nd);
                    if (isNotClean(nx,ny,nd)) {
                        nx += dx[nd];
                        ny += dy[nd];
                        break;
                    }
                }
            }

        }

        System.out.println(cleanCnt);
    }
    public static boolean isNearClean(int x,int y){
        for(int i=0;i<4;i++){
            if(isNotClean(x,y,i))
                return false;
        }
        return true;
    }

    public static boolean isNotClean(int x,int y,int direction){
        return map[x+dx[direction]][y+dy[direction]] == 0;
    }

    public static int rotate(int direction){
        return (direction+1)%4;
    }

}


