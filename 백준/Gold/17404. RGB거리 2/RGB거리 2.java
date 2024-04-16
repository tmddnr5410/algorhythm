
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] home = new int[N][3];
		int[][] DPR = new int[N][3];
		int[][] DPG = new int[N][3];
		int[][] DPB = new int[N][3];
		
		for(int i=0;i<N;i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<3;j++) {
				home[i][j] = Integer.parseInt(stk.nextToken());
				DPR[i][j] = 99999999;
				DPG[i][j] = 99999999;
				DPB[i][j] = 99999999;
			}
		}
		
		DPR[0][0] = home[0][0];
		DPG[0][1] = home[0][1];
		DPB[0][2] = home[0][2];
		
		for(int i=1;i<N;i++) {
			DPR[i][0] = Math.min(DPR[i-1][1],DPR[i-1][2])+home[i][0];
			DPR[i][1] = Math.min(DPR[i-1][0],DPR[i-1][2])+home[i][1];
			DPR[i][2] = Math.min(DPR[i-1][1],DPR[i-1][0])+home[i][2];

			DPG[i][0] = Math.min(DPG[i-1][1],DPG[i-1][2])+home[i][0];
			DPG[i][1] = Math.min(DPG[i-1][0],DPG[i-1][2])+home[i][1];
			DPG[i][2] = Math.min(DPG[i-1][1],DPG[i-1][0])+home[i][2];
			
			DPB[i][0] = Math.min(DPB[i-1][1],DPB[i-1][2])+home[i][0];
			DPB[i][1] = Math.min(DPB[i-1][0],DPB[i-1][2])+home[i][1];
			DPB[i][2] = Math.min(DPB[i-1][1],DPB[i-1][0])+home[i][2];
		}

		int minR = Math.min(DPR[N-1][1],DPR[N-1][2]);
		int minG = Math.min(DPG[N-1][0],DPG[N-1][2]);
		int minB = Math.min(DPB[N-1][0],DPB[N-1][1]);
		
		int ans = Math.min(minR,Math.min(minG,minB));
		System.out.println(ans);
		
	}
}
