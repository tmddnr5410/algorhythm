import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp = br.readLine();
		StringTokenizer st = new StringTokenizer(temp);
		int N = Integer.parseInt(st.nextToken()); // 표 크기
		int M =  Integer.parseInt(st.nextToken()); // 합 범위 갯수
		int [][] mat = new int[N][N];
		int [][] prefixSum = new int[N+1][N+1];
		for (int i =0; i< N; i++) {
			temp = br.readLine();
			st = new StringTokenizer(temp);
			for(int j = 0; j< N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 && j == 0) {
					prefixSum[i+1][j+1] = mat[i][j];
				}else if (j == 0){
					prefixSum[i+1][j+1] = mat[i][j];
				}else {
					prefixSum[i+1][j+1] = prefixSum[i+1][j] + mat[i][j];
				}
			}
		}
		for (int test_case = 0 ; test_case <M ; test_case++) {
			temp = br.readLine();
			st = new StringTokenizer(temp);
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			int result = 0;
			for (int i = startX ; i <= endX; i++) {
				result += prefixSum[i][endY] - prefixSum[i][startY-1];
			}
			bw.write(result+"\n");
		}
		bw.flush();
	}

}
