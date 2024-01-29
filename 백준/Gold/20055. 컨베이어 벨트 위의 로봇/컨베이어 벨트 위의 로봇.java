import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main {

    private static int[] belt;
    private static int N,K;
    private static boolean[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        N =Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        belt = new int[2*N];
        robot = new boolean[N];

        stk = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N*2;i++) {
            belt[i] = Integer.parseInt(stk.nextToken());
        }

        int cnt=0;


        while(isBeltHealthy()){
            beltRotate();
            robotRotate();
            robotMove();
            if(belt[0] != 0){
                robot[0] = true;
                belt[0]--;}
            cnt++;
        }

        System.out.println(cnt);
    }

    public static void beltRotate() {
        int last = belt[2*N-1];
        for(int i=2*N-1;0<i;i--) {
            belt[i] = belt[i-1];
        }
        belt[0] = last;

        return;
    }

    public static void robotRotate() {
        for(int i=N-1;0<i;i--) {
            robot[i] = robot[i-1];
        }
        robot[0] = false;
        robot[N-1] = false;
    }

    public static void robotMove() {
        for(int i=N-1;0<i;i--) {
            if(robot[i-1] && !robot[i] && belt[i] > 0) {
                robot[i] = true;
                robot[i-1] = false;
                belt[i]--;
            }
            robot[N-1] = false;
        }
    }



    public static boolean isBeltHealthy() {
        int cnt=0;
        for(int n:belt) {
            if(n==0 && ++cnt >= K)
                return false;
        }
        return true;
    }

}