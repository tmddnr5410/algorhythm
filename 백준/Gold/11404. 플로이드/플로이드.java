import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int[][] graph;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        N = Integer.parseInt(br.readLine());
        graph = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i != j)
                    graph[i][j] = 10000000;
            }
        }
        
        int M =Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            stk = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            graph[start][end] = Math.min(graph[start][end], weight);
        }

        for(int i=1;i<=N;i++){ // i를 거쳐갈수 있는 모든 경로를 갱신
            for(int j=1;j<=N;j++){ // 모든 시작지점에 대해 매번 갱신
                for(int k=1;k<=N;k++){
                    graph[k][j] = Math.min(graph[k][j], graph[k][i]+graph[i][j]);
                }
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(graph[i][j] == 10000000)// 갈수 없는 경우 0 출력
                    bw.write(0+" ");
                else
                    bw.write(graph[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
