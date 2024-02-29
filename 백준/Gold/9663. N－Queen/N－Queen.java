import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[][] map;
	private static int N,ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		ans=0;
		
		DFS(1,0);
		System.out.println(ans);
	}
	
	public static void DFS(int queenCount,int line) {			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			// 현재 놓을 퀸이 N+1 번째 라는것 -> N개의 퀸을 이미 놓았다는것
			if(queenCount == N+1) {
				ans++;
				return;
			}

			if(line >= N)
				return;
			
			for(int j=0;j<N;j++) {
				if(map[line][j] == 0) {
					check(line,j,queenCount,true);
					DFS(queenCount+1,line+1);  // 한줄에는 퀸이 하나밖에 못드감
					check(line,j,queenCount,false);
				}
			}
		
	}
	
	public static void check(int x,int y,int checkCnt,boolean isCheck) {
		int unCheck = 0;
		int check = checkCnt;
		
		if(!isCheck) {
			unCheck = checkCnt;
			check = 0;
		}
		
		for(int i=0;i<N;i++) {
				if(0 <= x-i && map[x-i][y] == unCheck)
					map[x-i][y] = check;
				
				if(x+i < N && map[x+i][y] == unCheck)
					map[x+i][y] = check;
				
				if(0<= y-i && map[x][y-i] == unCheck)
					map[x][y-i] = check;
				
				if(y+i < N && map[x][y+i] == unCheck)
					map[x][y+i] = check;
				
				if(0<= x-i && 0<= y-i && map[x-i][y-i] == unCheck)
					map[x-i][y-i] = check;
				
				if(x+i < N && y+i < N && map[x+i][y+i] == unCheck)
					map[x+i][y+i] = check;
				
				if(0<= x-i && y+i < N && map[x-i][y+i] == unCheck)
					map[x-i][y+i] = check;
				
				if(x+i < N && 0 <= y-i && map[x+i][y-i] == unCheck)
					map[x+i][y-i] = check;
		}
	}
	
}
