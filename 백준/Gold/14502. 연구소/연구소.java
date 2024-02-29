
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N,M,min;
	private static int[][] map;
	private static ArrayList<int[]> virus;
	private static Queue<int[]> que;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		que = new LinkedList<>();
		virus = new ArrayList<>();
		int safety = 0;
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == 2)
					virus.add(new int[] {i,j});
				else if(map[i][j] != 1)
					safety++;
			}
		}
		
		min = M*N;
		
		DFS(0,0,0);
		
		// 안전지대의 크기에서 놓는 3개의 벽을 뺴고 바이러스가 퍼지는 최솟값을 빼면 최대 안전지대
		System.out.println(safety - min - 3);
	}
	
	private static void DFS(int line,int y,int count) {
		// 벽을 3개 세웠다면 바이러스를 퍼뜨린다
		if(count == 3) {
//			int n=BFS();
//			if(n < min) {
//				//System.out.println(n+"개의 바이러스로 갱신");
//				//printMap();
//			}
			min = Math.min(min,BFS());
			return;
		}
		
		// 현재 라인에서 남은 벽을 세울 위치를 찾는다
		for(int j=y;j<M;j++) {
			if(map[line][j] == 0) {
				map[line][j] = 8;
				DFS(line,j+1,count+1);
				map[line][j] = 0;
			}
		}
		
		// 다음 라인부터는 첫 행부터 센다
		for(int i=line+1;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					map[i][j] = 8;
					DFS(i,j+1,count+1);
					map[i][j] = 0;
				}
			}
		}
		
	}
	
	private static void printMap() {
		for(int[] line:map) {
			for(int num:line) {
				System.out.print(num+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	
	private static int BFS() {
		que.clear();
		int count = 0;
		boolean[][] visit = new boolean[N][M];
		
		// 0이 바이러스로 변하는 경우에 대해서만 계산
		for(int[] pos:virus) {
			que.add(pos);
			visit[pos[0]][pos[1]] = true;
		}
		
		while(!que.isEmpty()) {
			int x = que.peek()[0];
			int y = que.peek()[1];
			que.poll();
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || N <= nx || ny < 0 || M <= ny) continue;
				if(!visit[nx][ny] && map[nx][ny] == 0) {
					visit[nx][ny] = true;
					que.add(new int[] {nx,ny});
					count++;
				}
			}
		}
		
		return count;
		
	}

}
