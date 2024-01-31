import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static int N,M;
	private static Stack<Integer> stack = new Stack<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(stk.nextToken());
		M=Integer.parseInt(stk.nextToken());
		
		DFS(0,0);
	}

	private static void DFS(int count,int last) {
		if(count==M){
			for(int num:stack){
				System.out.printf("%d ",num);
			}
			System.out.println();
			return;
		}
		
		for(int i=last+1;i<N+1;i++) {
			stack.push(i);
			DFS(count+1,i);
			stack.pop();
		}
	}
}
