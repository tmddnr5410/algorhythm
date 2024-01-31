import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1;tc<T+1;tc++) {
		
		int N = Integer.parseInt(br.readLine());
		int d = 0;
		int cnt=0;
		int[][] map = new int[N][N];
		int nx=0,ny=0;
		
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[nx][ny] = ++cnt;
				if(nx+dx[d] < 0 || N <= nx+dx[d] || ny+dy[d] < 0 || N <= ny+dy[d] || map[nx+dx[d]][ny+dy[d]] != 0)
					d = (d+1)%4;
				nx = nx+dx[d];
				ny = ny+dy[d];
			}
		}

		System.out.printf("#%d\n",tc);
		for(int x=0;x<N;x++) {
			for(int y=0;y<N;y++) {
				System.out.printf("%d ",map[x][y]);
			}
			System.out.println();
		}
	
		}
		
	}

}
