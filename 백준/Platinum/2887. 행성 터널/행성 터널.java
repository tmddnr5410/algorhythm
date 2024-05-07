import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Colony implements Comparable<Colony>{
	Integer id;
	Integer p;
	

	public Colony(Integer id, Integer p) {
		this.id = id;
		this.p = p;
	}


	@Override
	public int compareTo(Colony o) {
		return p.compareTo(o.p);
	}


	@Override
	public String toString() {
		return "[id=" + id + ", p=" + p + "]";
	}
}

class Edge implements Comparable<Edge>{
	Integer start;
	Integer end;
	Integer weight;


	public Edge(Integer start, Integer end, Integer weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}


	@Override
	public int compareTo(Edge o) {
		return weight.compareTo(o.weight);
	}

}



public class Main{
	private static final int X=0,Y=1,Z=2;
	
	private static int[] parents;
	private static ArrayList<Colony>[] colonys;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Edge> pQue = new PriorityQueue<>();
		
		parents = new int[N+1];
		
		colonys = new ArrayList[3];
		colonys[X] = new ArrayList<>();
		colonys[Y] = new ArrayList<>();
		colonys[Z] = new ArrayList<>();

		
		
		for(int i=1;i<=N;i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			int z = Integer.parseInt(stk.nextToken());
			
			colonys[X].add(new Colony(i, x));
			colonys[Y].add(new Colony(i, y));
			colonys[Z].add(new Colony(i, z));
			parents[i] = i;
		}
		
		
		int count = 0;
		long total = 0;
		
		for(int i=0;i<3;i++) 
			Collections.sort(colonys[i]);
		

		for(int i=0;i<N-1;i++) {
			for(int c=0;c<3;c++) {
				Colony start = colonys[c].get(i);
				Colony end = colonys[c].get(i+1);
				
				int weight = Math.abs(start.p-end.p);
				
				pQue.add(new Edge(start.id, end.id, weight));
			}
		}
		
		while(!pQue.isEmpty()) {
			// 총 정점갯수 - 1 개 만큼 이었으면 최소 연결 완료
			if(count == N-1)
				break;
			
			Edge now = pQue.poll();
			
			
			// 사이클이 생기는 간선은 추가하지 않는다.
			if(!union(now.start,now.end)) continue;
			
			count++;
			total += now.weight;
			
		}
		
		
		System.out.println(total);
	}
	
	// 부모 배열을 탐색해 어떤 정점의 부모를 탐색
	public static int findParents(int now) {
		if(parents[now] == now)
			return now;
		return parents[now] = findParents(parents[now]);
	}

	// 현재 간선을 이을수 있다면 true
	public static boolean union(int x,int y) {
		int xParents = findParents(x);
		int yParents = findParents(y);
		
		if(xParents == yParents)
			return false;
		
		parents[yParents] = xParents;
			
		return true;
	}
	
	
}
