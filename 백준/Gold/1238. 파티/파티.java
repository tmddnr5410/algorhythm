import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int X = Integer.parseInt(stk.nextToken());
        map = new int[N + 1][N + 1];

        int start, end, dist;

        // 각 map의 거리를 최대치로 갱신
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i != j)
                    map[i][j] = Integer.MAX_VALUE;
            }
        }

        // 입력부
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(stk.nextToken());
            end = Integer.parseInt(stk.nextToken());
            dist = Integer.parseInt(stk.nextToken());
            map[start][end] = dist;
        }
        
        // 플로이드 워셜 알고리즘을 통해 각 정점으로의 최단거리 계산
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                if (i != k) {
                    for (int j = 1; j < N + 1; j++) {
                        if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE)
                            map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

        int max = 0;
        // 각 정점에서 2로의 최단거리 + 2에서 각정점으로의 최단거리중 최댓값을 계산
        for (int i = 1; i < N + 1; i++)
            max = Math.max(max, map[i][X] + map[X][i]);

        System.out.println(max);

    }
}