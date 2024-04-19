import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int[][] isPel;
	private static int[] nums;
	private static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		isPel = new int[N][N];
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0;i<N;i++) 
			nums[i] = Integer.parseInt(stk.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(stk.nextToken());
			int E = Integer.parseInt(stk.nextToken());
			
			if(pel(S-1,E-1) == 1)
				bw.write("1\n");
			else
				bw.write("0\n");
		
		}
		
		bw.flush();
		bw.close();
		
	}
	
	public static int pel(int start,int end) {
        // 구간이 넘어가면 펠린드롬
		if(start >= end)
			return 1;
		// 펠린드롬을 계산한적 있으면 계산결과 리턴
		if(isPel[start][end] != 0)
			return isPel[start][end];
		
        // 펠린드롬의 가능성이 있는 수는 다음 구간을 본다
		if(nums[start] == nums[end]) {
			isPel[start][end] = pel(start+1,end-1);
			return isPel[start][end];
		}
        // 펠림드롬이 될수없는수
		else{
			isPel[start][end] = 2;
			return 2;
		}
	}
    
}
