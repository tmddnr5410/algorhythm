import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int R = 0;
	private static final int G = 1;
	private static final int B = 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		int N = Integer.parseInt(br.readLine());
		
		int[][] RGB = new int[N][3];
		int[][] DP = new int[N][3];
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			RGB[i][R] = Integer.parseInt(stk.nextToken());
			RGB[i][G] = Integer.parseInt(stk.nextToken());
			RGB[i][B] = Integer.parseInt(stk.nextToken());
		}
		
		DP[0][R] = RGB[0][R];
		DP[0][G] = RGB[0][G];
		DP[0][B] = RGB[0][B];
		
		for(int i=1;i<N;i++) {
			// N번째 집의 색과 겹치지 않는 최소비용을 더해간다.
			RGB[i][R] = RGB[i][R] + Math.min(RGB[i-1][G],RGB[i-1][B]);
			RGB[i][G] = RGB[i][G] + Math.min(RGB[i-1][R],RGB[i-1][B]);
			RGB[i][B] = RGB[i][B] + Math.min(RGB[i-1][R],RGB[i-1][G]);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=0;i<3;i++)
			min = Math.min(min,RGB[N-1][i]);
		
		System.out.println(min);
	}

}
