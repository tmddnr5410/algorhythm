import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


class Colony {
	Integer id;
	Integer x;
	Integer y;
	Integer z;
	
	public Colony(Integer id, Integer x, Integer y, Integer z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
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


	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}

}



public class Main {
	private static final int X=0,Y=1,Z=2;
	
	private static int[] parents;
	private static ArrayList<Colony> colonys;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Edge> edgeList = new ArrayList<>();
		
		parents = new int[N];

		colonys = new ArrayList<>();

		
		for(int i=0;i<N;i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			int z = Integer.parseInt(stk.nextToken());
			
			colonys.add(new Colony(i, x, y, z));
			parents[i] = i;
		}
		
		
		long total = 0;
		

		Collections.sort(colonys, (p1,p2)-> p1.x - p2.x);
		for(int i=0;i<N-1;i++) {
				Colony start = colonys.get(i);
				Colony end = colonys.get(i+1);

				int weight = Math.abs(start.x-end.x);
				
				edgeList.add(new Edge(start.id, end.id, weight));
			
		}


		

		Collections.sort(colonys, (p1,p2)-> p1.y - p2.y);
		for(int i=0;i<N-1;i++) {
				Colony start = colonys.get(i);
				Colony end = colonys.get(i+1);
				
				int weight = Math.abs(start.y-end.y);
				
				edgeList.add(new Edge(start.id, end.id, weight));
			
		}


		
		

		Collections.sort(colonys, (p1,p2)-> p1.z - p2.z);
		for(int i=0;i<N-1;i++) {
				Colony start = colonys.get(i);
				Colony end = colonys.get(i+1);
				
				int weight = Math.abs(start.z-end.z);
				
				edgeList.add(new Edge(start.id, end.id, weight));
			
		}


		
		
		Collections.sort(edgeList);
		
		
		for (int i = 0; i < edgeList.size(); i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(j+"의 부모 : "+parents[j]+" | ");
//			}
//			System.out.println();
			Edge now = edgeList.get(i);
			// 사이클이 생기는 간선은 추가하지 않는다.
			if(!union(findParents(now.start),findParents(now.end))) continue;
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
		
		parents[y] = xParents;
		
		return true;
	}
	
	
}
