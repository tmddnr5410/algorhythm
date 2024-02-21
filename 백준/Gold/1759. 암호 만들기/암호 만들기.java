
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static int L,C;
	private static boolean[] visit;
	private static int[] alphabets;
	private static Stack<Integer> stack;
	private static BufferedWriter bw;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		stack = new Stack<>();
		L = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		visit = new boolean[C];
		alphabets = new int[C];
		stk = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<C;i++)
			alphabets[i]= stk.nextToken().charAt(0);

		Arrays.sort(alphabets);
		DFS(0, 0,0,0);
		bw.flush();
	}
	
	private static void DFS(int depth,int now,int mo,int ja) throws IOException {
		if(depth == L && mo >= 1 && ja >=2) {
			for(int num:stack)
				bw.write((char)num);
			bw.write("\n");
			return;
		}
		for(int i=now;i<C;i++) {
			stack.add(alphabets[i]);
			if(alphabets[i] == 'a' || alphabets[i] == 'e' || alphabets[i] == 'i'|| alphabets[i] == 'o'|| alphabets[i] == 'u')
				DFS(depth+1, i+1,mo+1,ja);
			else
				DFS(depth+1, i+1,mo,ja+1);
			stack.pop();
		}
	}

	
	
}
