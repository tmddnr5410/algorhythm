import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Dot{
    private int x;
    private int y;
    public Dot(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    @Override
    public String toString() {
        return "Dot [x=" + x + ", y=" + y + "]";
    }
    public void setY(int y) {
        this.y = y;
    }

}

class AP{
    private int x;
    private int y;
    private int C;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getC() {
        return C;
    }
    public int getPower() {
        return power;
    }
    private int power;
    public AP(int x, int y, int c, int power) {
        super();
        this.x = x;
        this.y = y;
        C = c;
        this.power = power;
    }


}

public class Solution {

    private static int M,N;
    private static final int[] dx = {0,0,1,0,-1};
    private static final int[] dy = {0,-1,0,1,0};
    private static ArrayList<AP> APs;

    private static PriorityQueue<Integer> que;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        que = new PriorityQueue<>((a, b)-> b - a);
        ArrayList<Dot> A = new ArrayList<>();
        ArrayList<Dot> B = new ArrayList<>();
        APs = new ArrayList<>();

        for(int tc = 1;tc<=T;tc++) {
            // 입력부
            A.clear();
            B.clear();
            APs.clear();
            stk = new StringTokenizer(br.readLine()," ");
            M = Integer.parseInt(stk.nextToken());
            N = Integer.parseInt(stk.nextToken());

            int d;


            // A입력부
            stk = new StringTokenizer(br.readLine()," ");
            int x=1,y=1;
            for(int i=0;i<M;i++) {
                d = Integer.parseInt(stk.nextToken());
                A.add(new Dot(x,y));
                x+=dx[d];
                y+=dy[d];
            }
            A.add(new Dot(x,y));

            // B입력부
            stk = new StringTokenizer(br.readLine()," ");
            x=10;
            y=10;
            for(int i=0;i<M;i++) {
                d= Integer.parseInt(stk.nextToken());
                B.add(new Dot(x,y));
                x+=dx[d];
                y+=dy[d];
            }
            B.add(new Dot(x,y));

            // AP입력부
            int APx,APy,C,P;
            for(int i=0;i<N;i++) {
                stk = new StringTokenizer(br.readLine()," ");
                APx = Integer.parseInt(stk.nextToken());
                APy= Integer.parseInt(stk.nextToken());
                C= Integer.parseInt(stk.nextToken());
                P= Integer.parseInt(stk.nextToken());
                APs.add(new AP(APx, APy, C, P));
            }

            // 알고리즘
            // A,B의 각 위치에서 파워를 가장 많이 받는 합을 구해서 합함
            int ans=0;
            for(int i=0;i<M+1;i++) {
                ans += maxPower(A.get(i).getX(), A.get(i).getY(), B.get(i).getX(), B.get(i).getY());
            }
            bw.write("#"+tc+" "+ans+"\n");
        }
        bw.flush();
        bw.close();
    }


    private static int maxPower(int Ax,int Ay,int Bx,int By) {
        int maxA=0,maxB=0;
        que.clear();

        // 현재 A,B에서 A만 얻을수있는 AP의 최대 파워, B만 얻을수있는 AP의 최대 파워, A/B가 겹치는 AP의 모든파워를 구함
        for(AP ap:APs) {
            boolean inA = isCharge(Ax,Ay,ap);
            boolean inB = isCharge(Bx,By,ap);

            if(inA && inB) {
                que.add(ap.getPower());
            }
            else if(inA && !inB) {
                maxA = Math.max(maxA, ap.getPower());
            }
            else if(!inA && inB) {
                maxB = Math.max(maxB, ap.getPower());
            }

        }

        que.add(maxA);
        que.add(maxB);
        // A만 받을수있는 최대파워와 B만 받을수있는 최대파워, 그리고 둘이 동시에 받을수있는 최대파워중
        // 가장 큰 두개의 타워의 파워합이 해당 구간에서의 파워임
        return que.poll() + que.poll();
    }


    private static boolean isCharge(int x,int y,AP ap) {
        return (Math.abs(x-ap.getX()) + Math.abs(y-ap.getY())) <= ap.getC();
    }

}