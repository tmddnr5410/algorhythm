import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int M,N,H;
    private static int[][][] tomatos;

    private static final int[] dx ={0,0,0,0,1,-1};
    private static final int[] dy ={0,0,1,-1,0,0};
    private static final int[] dz ={1,-1,0,0,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        Queue<int[]> que = new LinkedList<>();

        M = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());

        tomatos = new int[H][N][M];

        int nz,nx,ny;
        int mx,my,mz;
        int isTomato,max;

        for(int i=0;i<H;i++){
            for(int j=0; j<N;j++){
                stk = new StringTokenizer(br.readLine());
                for(int k=0;k<M;k++){
                    isTomato = Integer.parseInt(stk.nextToken());
                    if(isTomato == 1)
                        que.add(new int[]{i,j,k});
                    tomatos[i][j][k] = isTomato;
                }
            }
        }

        while(!que.isEmpty()){

            //printTomato(0);

            nx = que.peek()[0];
            ny = que.peek()[1];
            nz = que.peek()[2];
            que.poll();
            for(int i=0;i<6;i++){
                mx = nx+dx[i];
                my = ny+dy[i];
                mz = nz+dz[i];
                if(validPosition(mx,my,mz) && canMove(mx,my,mz)){
                    que.add(new int[]{mx,my,mz});
                    tomatos[mx][my][mz] = tomatos[nx][ny][nz]+1;
                }
            }
        }

        System.out.println(checkYoungTomato());

    }

    public static int checkYoungTomato(){
        int max=0;
        for(int i=0;i<H;i++){
            for(int j=0; j<N;j++){
                for(int k=0;k<M;k++){
                    if(tomatos[i][j][k] == 0){
                        return -1;
                    }
                    max = Math.max(tomatos[i][j][k],max);
                }
            }
        }
        return max-1;
    }

    public static boolean validPosition(int nx,int ny,int nz){
        return 0<=nx && nx<H && 0<=ny && ny<N && 0<=nz && nz<M;
    }

    public static boolean canMove(int nx,int ny,int nz){
        return tomatos[nx][ny][nz] == 0;
    }

//    public static void printTomato(int depth){
//        System.out.println("------depth : "+depth+" ---------");
//        for(int i=0;i<H;i++){
//            System.out.println("H"+(i+1));
//            for(int j=0; j<N;j++){
//                for(int k=0;k<M;k++){
//                    System.out.print(tomatos[i][j][k]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
//    }
}
