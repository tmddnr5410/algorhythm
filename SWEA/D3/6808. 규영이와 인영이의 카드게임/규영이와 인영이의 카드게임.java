import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution {
	private static final int N = 9;
	private static ArrayList<Integer> cards,myCards;
	private static Stack<Integer> stack;
	private static boolean[] visited;
	private static int win,lose;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		stack = new Stack<>();
		cards = new ArrayList<>();
		myCards = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			cards.clear();
			stack.clear();
			myCards.clear();
			visited = new boolean[N];
			win = 0;
			lose = 0;
			
			boolean[] other = new boolean[19];
			
			stk = new StringTokenizer(br.readLine()," ");
			
			int card;
			for(int i=0;i<N;i++) {
				card = Integer.parseInt(stk.nextToken()); 
				myCards.add(card);
				other[card] = true;
			}
			
			for(int i=1;i<19;i++) {
				if(!other[i])
					cards.add(i);
			}
			
			DFS(0,0,0);
			
			bw.write("#"+tc+" "+win+" "+lose+"\n");
		}
		bw.flush();
		bw.close();
	}

	public static void DFS(int depth,int myScore,int otherScore) {
		if(depth == N) {
			if(myScore > otherScore)
				win++;
			else if(myScore < otherScore)
				lose++;
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				
				int score = myCards.get(depth) + cards.get(i);
				
				if(myCards.get(depth) > cards.get(i))
					DFS(depth+1 ,myScore+score ,otherScore);
				else if(myCards.get(depth) <  cards.get(i))
					DFS(depth+1,myScore ,otherScore+score);
				
				
				visited[i] = false;
			}
		}
	}
}

