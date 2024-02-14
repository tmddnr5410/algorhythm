import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int[][] map;
    static int N;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            String s = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }

        divide(0,0,N);
        bw.flush();
        bw.close();
    }

    public static void divide(int x,int y,int size) throws IOException {
        if(size == 1) {
            bw.write(map[x][y]+"");
            return;
        }

        int half = size/2;
        int temp = map[x][y];

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(temp != map[x+i][y+j]) {
                    bw.write("(");
                    divide(x,y,half);
                    divide(x,y+half,half);
                    divide(x+half,y,half);
                    divide(x+half,y+half,half);
                    bw.write(")");
                    return;
                }
            }
        }

        bw.write(temp+"");
    }

}
