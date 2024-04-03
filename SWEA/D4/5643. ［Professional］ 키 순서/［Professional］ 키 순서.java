import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

		public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			int[][] small = new int[N][N];
			int[][] big = new int[N][N];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(i == j) continue;
					small[i][j] = 1001;
					big[i][j] = 1001;
				}
			}
			
			
			// 키가 작은 순서를 알수있는 그래프와
			// 키가 큰 순위를 알수있는 그래프를 만듬
			for(int i=0;i<M;i++) {
				StringTokenizer stk = new StringTokenizer(br.readLine()," ");
				
				int smaller = Integer.parseInt(stk.nextToken())-1;
				int bigger = Integer.parseInt(stk.nextToken())-1;
				
				small[smaller][bigger] = 1;
				big[bigger][smaller] = 1;
			}
			
			
			// 각 사람이 누군가와 경로가 있다는것은
			// 그 누군가와 키가 작거나 / 큰 정보를 안다는것
			// 플로이드 워셜을 통해 경로를 업데이트 한다.
			for(int k=0;k<N;k++) {
				for(int i=0;i<N;i++) {
					if(k == i) continue;
					for(int j=0;j<N;j++) {
						if(i == j || k == j) continue;
						small[i][j] = Math.min(small[i][j], small[i][k]+small[k][j]);
						big[i][j] = Math.min(big[i][j], big[i][k]+big[k][j]);
					}
				}
			}
			
			boolean know;
			int answer = 0;
			
			// 어떤 사람이 다른 한사람이라도 크고작음을 판단할수 없다면 순위를 알수 없는것.
			for(int i=0;i<N;i++) {
				know = true;
				for(int j=0;j<N;j++) {
					if(i == j) continue;
					if(small[i][j] == 1001 && big[i][j] == 1001) {
						know = false;
						break;
					}
				}
				if(know)
					answer++;
			}
			
			bw.write("#"+tc+" "+answer+"\n");
			
		}
		
		bw.flush();
		bw.close();
	}

}
