import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	
	static int[][] map,DP;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			// N이 0이 되면 종료 
			if(N == 0) break;
			
			map = new int[N][N];
			DP = new int[N][N];

			for(int i=0;i<N;i++) {
				StringTokenizer stk = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					DP[i][j] = 1126;
				}
			}

			DP[0][0] = map[0][0];
			
			DFS(0,0);
			
			bw.write("Problem "+(tc++)+": "+DP[N-1][N-1]+"\n");
		}
		bw.flush();
		bw.close();
	}

	public static void DFS(int x,int y) {
		if(x == N-1 && y== N-1) 
			return;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			// 이동할수 없는 위치로 방문하지 않는다.
			if(nx < 0 || N <= nx || ny < 0 || N <= ny ) continue;

			if(DP[x][y]+map[nx][ny] < DP[nx][ny]) { // 방문할 위치에 대해 최솟값을 갱신할수 없다면 방문하지 않는다.
				DP[nx][ny] = DP[x][y] + map[nx][ny];
				DFS(nx,ny);
			}
		}
		
	}

}
