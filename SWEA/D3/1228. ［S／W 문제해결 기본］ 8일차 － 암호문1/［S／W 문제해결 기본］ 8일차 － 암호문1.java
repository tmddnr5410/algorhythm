import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		ArrayList<Integer> ciper = new ArrayList<>();
		
		for(int tc = 1;tc<=10;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			ciper.clear();
			stk = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++)
				ciper.add(Integer.parseInt(stk.nextToken()));
	
			int M = Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				String cmd = stk.nextToken();
				int start = Integer.parseInt(stk.nextToken());
				int size = Integer.parseInt(stk.nextToken());
				for(int k=0;k<size;k++) {
					ciper.add(start++, Integer.parseInt(stk.nextToken()));
				}
			}
			
			bw.write("#"+tc);
			for(int i=0;i<10;i++) {
				bw.write(" "+ciper.get(i));
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}
	
}
