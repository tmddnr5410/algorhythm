import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


class Dot{
    private int x;
    private int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {
    private static int[][] sdoque;
    private static boolean isSolved;
    private static Stack<Dot> stack;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        stack = new Stack<>();
        StringTokenizer stk ;

        sdoque = new int[9][9];

        // 입력부
        for(int i=0;i<9;i++){
            stk = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<9;j++){
                sdoque[i][j] = Integer.parseInt(stk.nextToken());
                if(sdoque[i][j] == 0)
                    stack.add(new Dot(i,j));
            }
        }

        // 출력부
        Dot d = stack.pop();
        DFS(d.getX(),d.getY());
    }

    public static void DFS(int nx,int ny) throws IOException {
        boolean[] nums = new boolean[10];



        for(int i=0;i<9;i++){
            if(!nums[sdoque[nx][i]])
                nums[sdoque[nx][i]] = true;
            if(!nums[sdoque[i][ny]])
                nums[sdoque[i][ny]] = true;
        }

        int pos = (nx/3)*3+ny/3;
        
        int x = (nx / 3) * 3; // value가 속한 3x3의 행의 첫 위치
        int y = (ny / 3) * 3; // value가 속한 3x3의 열의 첫 위치

        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (!nums[sdoque[i][j]])
                    nums[sdoque[i][j]] =  true;
            }
        }



        for(int k=1;k<10;k++){
            if(!nums[k]) {
                sdoque[nx][ny] = k;

                if(stack.isEmpty()){
                    for(int i=0;i<9;i++){
                        for(int j=0;j<9;j++){
                            bw.write(sdoque[i][j]+" ");
                        }
                        bw.write("\n");
                    }

                    bw.flush();
                    bw.close();
                    isSolved = true;
                    return;
                }
                    x = stack.peek().getX();
                    y = stack.peek().getY();
                    stack.pop();
                    DFS(x, y);
                    if(isSolved)
                        return;
                    stack.push(new Dot(x, y));
                    sdoque[nx][ny] = 0;

            }
        }

    }

}
