import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static int[][] sdoque;
    private static BufferedWriter bw;
    private static boolean isSolve;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sdoque = new int[9][9];

        for(int i=0;i<9;i++){
            String line = br.readLine();
            for(int j=0;j<9;j++){
                sdoque[i][j] = line.charAt(j) - '0';
            }
        }

        // 첫번째 칸부터 스도쿠를 풀어나간다.
        solve(0,0);

        bw.flush();
        bw.close();
    }

    // x,y 좌표의 스도쿠를 풀이하는 함수
    private static void solve(int x,int y) throws IOException {
        // 답을 구했다면 DFS탈출
        if(isSolve)
            return;

        // 현재 행을 다 채웠으면 다음 행으로 이동
        if(y ==  9&& x == 8){  // 열까지 다넣은 경우 스도쿠를 해결한것. 현재 스도쿠를 기록
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    bw.write(sdoque[i][j]+"");
                }
                bw.write("\n");
            }
            isSolve = true;
        }

        if(y==9){
            solve(x + 1, 0);
            return;
        }

        // 스도쿠가 이미 채워진경우 다음 스도쿠를 확인
        if(sdoque[x][y] != 0){
            solve(x,y+1);
            return;
        }

        // 현재 자리를 1~9번까지 대입하며 스도쿠를 채운다
        for(int num=1;num<=9;num++){
            if(isValid(x,y,num)){ // 빈칸에 num이 들어갈수 있는지 확인후 넣는다
                sdoque[x][y] = num;
                solve(x,y+1);
                sdoque[x][y] = 0;
            }
        }


    }

    // 행, 열, 사각형 안에 이미 num을 넣었는지 확인한다.
    private static boolean isValid(int x,int y,int num){
        // 행과 열방향을 확인
        for(int i=0;i<9;i++){
            if(sdoque[x][i] == num || sdoque[i][y] == num)
                return false;
        }

        //    x   y
        //  (0~2)(0~2) (0~2)(3~5)  (0~2)(6~8)
        //  (3~5)(0~2) (3~5)(3~5)  (3~5)(6~8)
        //  (6~8)(0~2) (6~8)(3~5)  (6~8)(6~8)
        //  x,y을 3으로 나눈 정수 몫을 구하고 3을 곱하면 해당 정사각형의 시작점 확인 가능
        int squareX = (x/3)*3;
        int squareY = (y/3)*3;

        // 정사각형 범위를 확인
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(sdoque[squareX+i][squareY+j] == num)
                    return false;
            }
        }
        return true;
    }

}
