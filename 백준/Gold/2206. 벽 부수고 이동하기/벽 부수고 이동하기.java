import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int[][] map;
	private static int N,M;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		Queue<int[]> que = new LinkedList<int[]>();
		N = Integer.parseInt(stk.nextToken());		
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		int [][][] visit = new int[2][N][M]; // 벽을 언제 부수냐에 따라 경로가 달라져 부수고 이동하는 경우에 대한 새로운 맵이 존재해야함
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				if(line.charAt(j) == '1') {
					map[i][j] = 1;
				}
			}
		}
		
		que.add(new int[] {0,0,0});
		visit[0][0][0] = 1;
		
		int dist = -1;
		while(!que.isEmpty()) {
			int x= que.peek()[0];
			int y= que.peek()[1];
			int smash= que.peek()[2];

			que.poll();
			
			// 끝에 도달하면, 최단거리를 발견한것으로 탐색 종료
			if(x == N-1 && y == M-1) {
				dist = visit[smash][x][y];
				break;
			}
			
			// 각방향으로 진출 시도
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < 0 || N <= nx || ny<0 || M <= ny) continue; // 갈수 없는곳은 건너뜀
				
				// 그냥 지나갈수 있는경우, 현재 벽을 부순 횟수의 visit배열을 갱신
				if(map[nx][ny] == 0 && visit[smash][nx][ny] == 0) {
					visit[smash][nx][ny] = visit[smash][x][y] + 1;
					que.add(new int[] {nx,ny,smash});
				}
				
				// 벽이 있는경우 부수는 경우를 시도
				if(map[nx][ny] == 1 && smash == 0 && visit[1][nx][ny] == 0) {
						visit[1][nx][ny] = visit[0][x][y] + 1;
						que.add(new int[] {nx,ny,1});
				}
			}
			
		}
		System.out.println(dist);
	}

}
