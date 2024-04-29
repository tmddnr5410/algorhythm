import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot{
	int x;
	int y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}

public class Main {
	private static int N,M;
	private static boolean[][] visit;
	private static int[][] map;
	private static int[][] check;
	
	private static Queue<Dot> que;
	
	private static ArrayList<Integer> checkCount;
	private static int checkIdx;
	
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");

		que = new LinkedList<Dot>();
		checkCount = new ArrayList<>();
		checkIdx = 0; // 맵은 0이 default기 때문에 1부터 시작
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];
		check = new int[N][M];
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		// check에서 0 으로 나타나는 자리는 벽임을 표시
		checkIdx++;
		checkCount.add(0);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0 && !visit[i][j]) {
					int count = BFS(i,j);
					checkIdx++;
					checkCount.add(count);
				}
			}
		}
		//printMap();
		
		HashSet<Integer> set = new HashSet<>();
		for(int k=0;k<N;k++) {
			for(int j=0;j<M;j++) {
				set.clear();
				if(map[k][j] == 1 && !visit[k][j]) {
					for(int i=0;i<4;i++) {
						int nx = k+dx[i];
						int ny = j+dy[i];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
						if(map[nx][ny] == 1) continue;
						if(set.contains(check[nx][ny])) continue;
						
						set.add(check[nx][ny]);
						map[k][j] += checkCount.get(check[nx][ny]);
					}
				}
				bw.write((map[k][j]%10)+"");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void printMap() {
		System.out.println("check출력           맵출력");
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(check[i][j]);
			}
			System.out.print("          ");
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]);
			}
			
			System.out.println();
		}
		System.out.println();
		
	}
	
	public static int BFS(int startX,int startY) {
		que.clear();

		que.add(new Dot(startX, startY));
		visit[startX][startY] = true;
		check[startX][startY] = checkIdx;
		int count = 0;
		
		while(!que.isEmpty()) {
			Dot now = que.poll();
			count++;
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(map[nx][ny] == 1 || visit[nx][ny]) continue;
				
				que.add(new Dot(nx, ny));
				visit[nx][ny] = true;
				check[nx][ny] = checkIdx;
			}
		}
		
		return count;
		
	}

}
