import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[100][100];
		
		int x,y,nx,ny,cnt=0;
		
		for(int t=0;t<N;t++) {
			stk = new StringTokenizer(br.readLine()," ");
			x=Integer.parseInt(stk.nextToken());
			y=Integer.parseInt(stk.nextToken());
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					nx = x+i;
					ny = y+j;
					if(map[nx][ny] != 1) {
						map[nx][ny] = 1;
						cnt++;
					}
					
				}
			}
		}
		
		System.out.println(cnt);
	}

}
