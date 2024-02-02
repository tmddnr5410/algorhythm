import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
	
		for(int tc=1;tc<11;tc++) {
		br.readLine();
		stk = new StringTokenizer(br.readLine()," ");
		int[] ciper = new int[8];
		
		int min = Integer.MAX_VALUE;
		
		for(int i=0;i<8;i++) {
			ciper[i] = Integer.parseInt(stk.nextToken());
			min = Math.min(ciper[i], min);
		}
		
		int round = (min / 15)-1;
		
		for(int i =0;i <8;i++)
			ciper[i] -=(round * 15);
		
		
		int idx=0,rule =1,check=0;
		while(true){
			if(idx == 8)
				idx=0;
			if(rule == 6)
				rule = 1;
		
			ciper[idx] -= rule;

			if(ciper[idx] <= 0) {
				ciper[idx] = 0;
				check = idx;
				break;
			}
			
			idx++;
			rule++;
			
		}
		
		System.out.printf("#%d ",tc);
		for(int i=0;i<8;i++) {
			System.out.printf("%d ",ciper[(++check)%8]);
		}
		System.out.println();
		}
	}

}
