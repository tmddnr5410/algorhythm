import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static char[][] map;
	private static boolean[][] visit;
	private static boolean[] visitAlpha = new boolean[26];
	private static int R,C;
	private static int max;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		map = new char[R][C];
		
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visit = new boolean[R][C];
		
		// 첫 방문하는 노드를 방문했다고 표시
		visitAlpha[map[0][0] - 'A'] = true;
		DFS(0,0,1);
		System.out.println(max);
	}

	private static void DFS(int x,int y,int dist) {
		int nx,ny,idx;
		boolean isMove = false;
		for(int d=0;d<4;d++) {
			nx = x+dx[d];
			ny = y+dy[d];
			if(0<=nx && nx<R && 0<=ny && ny<C) {
				idx = map[nx][ny] - 'A';
				if(!visit[nx][ny] && !visitAlpha[idx]) {
					isMove = true;
					// 알파벳에 대한 방문을 따로 처리
					visitAlpha[idx] = true;
					visit[nx][ny] = true;
					DFS(nx,ny,dist+1);
					visitAlpha[idx] = false;
					visit[nx][ny] = false;
				}
			}
		}
		
		// 움직이지 못한경우 현경로의 최대 거리이므로 최대거리 갱신
		if(!isMove)
			max = Math.max(dist, max);
		
	}
	
}
