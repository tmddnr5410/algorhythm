import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for(int tc =1;tc<11;tc++) {
			String[] stk;
			int answer = 1;
			int N = Integer.parseInt(br.readLine());
			for(int i=0;i<N;i++) {
				if(answer == 1) {
				stk = br.readLine().split(" ");
				if(2 < stk.length && isInteger(stk[1]))
					answer = 0;
				else if(stk.length == 2 && !isInteger(stk[1]))
					answer = 0;
				}
				else {
					br.readLine();
				}
			}
			bw.write("#"+tc+" "+answer+"\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	
	
	public static boolean isInteger(String value) {
		int test;
		try {
			test = Integer.parseInt(value);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
		

}
