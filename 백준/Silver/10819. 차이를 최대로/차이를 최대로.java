import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	

	static List<Integer> list;
	static Stack<Integer> sequence;
	static boolean[] visit;
	static int N,max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			list.add(Integer.parseInt(stk.nextToken()));
		}
		
		visit = new boolean[N];
		sequence = new Stack<>();
		max=0;
		DFS(0);
		System.out.println(max);
        
	}
	
	public static void DFS(int depth) {
		if(depth == N)
			max = Math.max(max, calculateArr(sequence));
		
		for(int i=0; i<N;i++) {
			if(!visit[i]) {
				sequence.push(list.get(i));
				visit[i] = true;
				DFS(depth+1);
				visit[i] = false;
				sequence.pop();
			}
		}
	}
	
	public static int calculateArr(List<Integer> arr) {
		int sum = 0;
		for(int i=0;i<arr.size()-1;i++) {
			sum += abs(arr.get(i) - arr.get(i+1));
		}
		return sum;
	}
	
	public static int abs(int a) {
		return Math.abs(a);
	}
}
