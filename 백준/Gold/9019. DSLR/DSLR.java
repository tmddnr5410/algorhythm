
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class history{
	private int num;
	private String road;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public history(int num, String road) {
		super();
		this.num = num;
		this.road = road;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	
}

public class Main {
	private static BufferedWriter bw;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		Queue<history> que = new LinkedList<history>();
		
		visited = new boolean[10001];
		int src,target;
		
		int testCase = Integer.parseInt(br.readLine());
		for(int t = 0;t<testCase;t++) {
			que.clear();
			clearVisit();
			
			stk = new StringTokenizer(br.readLine()," ");
			src = Integer.parseInt(stk.nextToken());
			target = Integer.parseInt(stk.nextToken());
			
			que.add(new history(src, ""));
			visited[src] = true;
			history now;
			
			int nowNum,toD,toS,toL,toR;
			String nowRoute;
			while(!que.isEmpty()) {
				now = que.poll();
				nowNum = now.getNum();
				nowRoute = now.getRoad();
				
				if(now.getNum() == target) {
					bw.write(nowRoute+"\n");
					break;
				}
				
				toD = D(nowNum);
				if(!visited[toD]) {
				que.add(new history(toD , nowRoute+"D"));
				visited[toD] = true;
				}
				

				toS = S(nowNum);
				if(!visited[toS]) {
				que.add(new history(toS , nowRoute+"S"));
				visited[toS] = true;
				}
				

				toL = L(nowNum);
				if(!visited[toL]) {
				que.add(new history(toL, nowRoute+"L"));
				visited[toL] = true;
				}
				

				toR = R(nowNum);
				if(!visited[toR]) {
				que.add(new history(toR, nowRoute+"R"));
				visited[toR] = true;
				}
				
			}
		}
		bw.flush();
		bw.close();
	}
	
	public static int D(int num) {
		return (num*2)%10000;
	}
	
	public static int S(int num) {
		if(num == 0)
			return 9999;
		return num-1;
	}

	public static int L(int num) {
		return (num%1000) * 10 + (num/1000);
	}
	

	public static int R(int num) {
		return (num%10) * 1000 + (num/10);
	}
	
	public static void clearVisit() {
		for(int i=0;i<10001;i++) {
			visited[i] = false;
		}
	}
	
}
