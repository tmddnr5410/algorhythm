import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N,M;
	private static ArrayList<Integer>[] graph;
	private static boolean[] visit;
	private static boolean find;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		graph = new ArrayList[N];
		visit = new boolean[N];
		for(int i=0; i<N; i++)
			graph[i]= new ArrayList<>(); 
		
		for(int i=0; i<M;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			graph[start].add(end); 
			graph[end].add(start); 
		}
		
		find = false;
		for(int i=0; i<N;i++) {
			DFS(i, 0);
			if(find)
				break;
		}
		
		if(find)
			System.out.println(1);
		else
			System.out.println(0);
	}
	
	private static void DFS(int now,int depth) {
		if(find)
			return;
		
		if(depth == 5) {
			find = true;
			return;
		}
		
		for(int next:graph[now]) {
			if(!visit[next]) {
				visit[next] = true;
				DFS(next, depth+1);
				visit[next] = false; 
			}
		}
	}
	

}
