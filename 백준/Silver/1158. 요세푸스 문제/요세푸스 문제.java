import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		ArrayList<Integer> yosep = new ArrayList<>();
		ArrayList<String> si = new ArrayList<>();
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken())-1;

		for(int i=1;i<=N;i++)
			yosep.add(i);
			
		int idx = 0,L=N;
		while(!yosep.isEmpty()) {
			idx = (idx+K)%L;
			
			si.add(Integer.toString(yosep.remove(idx)));
			
			L = yosep.size();
		}

		System.out.print("<");
		System.out.print(String.join(", ", si));
		System.out.print(">");
	}
}
