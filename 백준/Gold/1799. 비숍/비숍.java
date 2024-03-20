import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static boolean[] L;
	private static boolean[] R;
	private static int[][] map;
	private static int N,max,totalBishop;
	private static int[] dx = {1,-1,1,-1};
	private static int[] dy = {1,1,-1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		L = new boolean[2*N];
		R = new boolean[2*N];
		totalBishop = 0;
		
		for(int i=0;i<N;i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++)
				map[i][j] = Integer.parseInt(stk.nextToken());
		}

		int answer = 0;
		
		// 흑과백으로 이루어진 체스판에서,
		// 흑에놓는 비숍은 흑으로만 움직이고 백에놓는 비숍은 백으로만 움직일수있다.
		
		// 흰색만을 고려한 최대 비숍수
		max = 0;
		DFS(0,0,0,0);
		answer += max;
		
		// 검은색 칸만을 고려한 최대 비숍수
		max = 0;
		DFS(0,1,0,1);
		answer += max;
		
		System.out.println(answer);
	}

	public static void DFS(int x,int y,int bishop,int start) {
		if(x == N-1 && y >= N) {
			max = Math.max(max,bishop);
			return;
		}
		
		if(y >= N) {
			if(start == 1)
				DFS(x+1,0,bishop,0);
			else
				DFS(x+1,1,bishop,1);
			return;
		}


		int idxL=N-x+y;
		int idxR=2*N-1-x-y;
		
		// 비숍을 놓을수 있는경우 왼쪽 대각선과 오른쪽 대각선을 체크.
		// 왼쪽 대각선과 오른쪽 대각선 모두 체크 되어있지 않아야 비숍을 놓을수 있음.
		if(map[x][y] == 1 && !L[idxL] && !R[idxR]) {
			L[idxL] = true;
			R[idxR] = true;
			DFS(x,y+2,bishop+1,start);
			L[idxL] = false;
			R[idxR] = false;
		}
		DFS(x,y+2,bishop,start);
		
	}
}
