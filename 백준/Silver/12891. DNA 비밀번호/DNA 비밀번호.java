import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N,M;
	private static char[] DNA;
	private static int A,C,G,T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		DNA = br.readLine().toCharArray();
		
		stk = new StringTokenizer(br.readLine()," ");
		A=Integer.parseInt(stk.nextToken());
		C=Integer.parseInt(stk.nextToken());
		G=Integer.parseInt(stk.nextToken());
		T=Integer.parseInt(stk.nextToken());
		
		int cntA,cntC,cntG,cntT;
		int answer=0;
		
		cntA=0;
		cntC=0;
		cntG=0;
		cntT=0;
		for(int i=0; i<M;i++) {
			if(DNA[i] == 'A')
				cntA++;
			else if(DNA[i] == 'C')
				cntC++;
			else if(DNA[i] == 'G')
				cntG++;
			else if(DNA[i] == 'T')
				cntT++;
		}
		if(A<=cntA && C<=cntC && G<=cntG && T<=cntT)
				answer++;
		
		int pre =0;
		for(int j=M;j<N;j++,pre++) {
			if(DNA[pre] == 'A')
				cntA--;
			else if(DNA[pre] == 'C')
				cntC--;
			else if(DNA[pre] == 'G')
				cntG--;
			else if(DNA[pre] == 'T')
				cntT--;
			
			if(DNA[j] == 'A')
				cntA++;
			else if(DNA[j] == 'C')
				cntC++;
			else if(DNA[j] == 'G')
				cntG++;
			else if(DNA[j] == 'T')
				cntT++;
			

			if(A<=cntA && C<=cntC && G<=cntG && T<=cntT)
					answer++;
		}
		
			
			
		System.out.println(answer);
	}

}
