import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class food{
	int score;
	int cal;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}
	public food(int score, int cal) {
		super();
		this.score = score;
		this.cal = cal;
	}

}

public class Solution {
	private static int N,L,maxScore,maxCal;
	private static Stack<Integer> stack;
	private static ArrayList<food> foods;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		foods = new ArrayList<>();
		stack = new Stack<>();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			stk = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(stk.nextToken());
			L = Integer.parseInt(stk.nextToken());
			
			stack.clear();
			foods.clear();
			maxScore = -1;
			
			for(int i=0;i<N;i++) {
				stk = new StringTokenizer(br.readLine()," ");
				foods.add(new food(Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken())));
			}
			
			set(0,1);
			System.out.printf("#%d %d\n",tc,maxScore);
		}
	}

	
	public static void set(int depth,int now) {
		if(depth == N)
			return;
		
		for(int i=now;i<=N;i++) {
			stack.add(i-1);
			setMax();
			set(depth+1,i+1);
			stack.pop();
		}
	}
	
	public static void setMax() {
		int sumCal=0,sumScore=0;
		for(int idx:stack) {
			sumScore += foods.get(idx).getScore();
			sumCal += foods.get(idx).getCal();
			if(sumCal > L)
				return;
		}

		if(sumCal > L)
			return;
		
		maxScore = Math.max(maxScore, sumScore);
	}
	
}
