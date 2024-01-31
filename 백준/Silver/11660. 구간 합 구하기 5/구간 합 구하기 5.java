import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	int x1, y1, x2, y2;
	
	int[][] map = new int[N+1][N+1];
	for(int y = 1; y <= N; y++) {
		st = new StringTokenizer(br.readLine());
		for(int x = 1; x <= N; x++) {
			map[y][x] = Integer.parseInt(st.nextToken());
		}
	}
	
	for(int y = 1; y <= N; y++) {
		for(int x = 1; x <= N; x++) {
			map[y][x] += map[y][x-1];
		}
	}
	
	for(int y = 1; y <= N; y++) {
		for(int x = 1; x <= N; x++) {
			map[y][x] += map[y-1][x];
		}
	}


	for(int i = 0; i < M; i++) {
		st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		
		System.out.println(map[x2][y2] - map[x2][y1-1] - map[x1-1][y2] + map[x1-1][y1-1]);
	}

}
}
