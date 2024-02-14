import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int max=-1,R,C;
	static int[] dx = {-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		map = new int[R][C];
		String s;
		for(int i=0;i<R;i++) {
			s = br.readLine();
			for(int j=0;j<C;j++) {
				if(s.charAt(j)=='x')
					map[i][j] = -1;
			}
		}
		
		int ans= 0;
		for(int i=0;i<R;i++) {
			ans += DFS(i,0);
		}
		
		System.out.println(ans);
	}
	
	public static int DFS(int row,int col) {
		if(col == C-1) {
			return 1;
		}
		// 아직 모든 행에대해 파이프를 놓아보지 않닸다면 파이프 놓기
				for(int i=0;i<3;i++) {
					int nx = row + dx[i];
					if(0<= nx && nx < R && map[nx][col+1] == 0) {
						map[nx][col+1] = 1;
						if(DFS(nx,col+1) == 1)
							return 1;
					}
				}
			
		return 0;
		
		
			
	}
}
