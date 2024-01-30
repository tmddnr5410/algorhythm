import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static boolean[] buttons;
	private static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		buttons = new boolean[N+1];
		stk = new StringTokenizer(br.readLine()," ");
		
		for(int i=1;i<N+1;i++) {
			if(stk.nextToken().equals("1"))
				buttons[i] = true;
		}

		int M = Integer.parseInt(br.readLine());
		
		for(int j=0;j<M;j++) {
			stk = new StringTokenizer(br.readLine()," ");
			
			if(Integer.parseInt(stk.nextToken()) == 1)
				boySwitch(Integer.parseInt(stk.nextToken()));
			else
				girlSwitch(Integer.parseInt(stk.nextToken()));
		}

		for(int k=1;k<N+1;k++) {
			if(buttons[k])
				System.out.print("1 ");
			else
				System.out.print("0 ");
			if(k%20 == 0)
				System.out.println();
		}

		
	}
	
	public static void boySwitch(int idx) {
		if(idx==0) {
			if(buttons[0])
				buttons[0] = false;
			else
				buttons[0]= true;
			return;
		}
		for(int i=1;i*idx<N+1;i++) {
			if(buttons[i*idx])
				buttons[i*idx] = false;
			else
				buttons[i*idx] = true;
		}
	}

	public static void girlSwitch(int idx) {
		if(buttons[idx])
			buttons[idx] = false;
		else
			buttons[idx]= true;
		
		int start = idx-1;
		int end = idx+1;
		
		while(0 < start && end < N+1) {
			if(buttons[start] != buttons[end])
				break;
			else if(buttons[start] == buttons[end]) {
				if(buttons[start]) {
					buttons[start] = false;
					buttons[end] = false;
					}
				else {
					buttons[start] = true;
					buttons[end] = true;
				}
			}
			start--;
			end++;
		}
	}

	
}
