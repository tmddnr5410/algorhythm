import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] DP = new int[10001];
		int[] DP2 = new int[10001];
		DP[1] = 1;
		DP[2] = 2;
		DP[3] = 3;

		DP2[1] = 0;
		DP2[2] = 1;
		DP2[3] = 1;

		for(int i=4;i<=10000;i++){
			DP2[i] = DP2[i-2];
			if((i-3) % 3 == 0)
				DP2[i]++;
			
			DP[i] = DP[i-1]+DP2[i];
		}

		for(int i=0;i<N;i++) {
			bw.write(DP[Integer.parseInt(br.readLine())]+"\n");
		}
		bw.flush();
		bw.close();
	}

}
