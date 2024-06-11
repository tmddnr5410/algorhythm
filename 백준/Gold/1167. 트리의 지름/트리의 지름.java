import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Edge{
    int vertex;
    int weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {
    private static final int NO_VERTEX =-1;
    private static int V;
    private static ArrayList<Edge>[] graph;
    private static int max,maxVertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        V = Integer.parseInt(stk.nextToken());
        graph = new ArrayList[V+1];

        for(int i=1;i<=V;i++){
            stk = new StringTokenizer(br.readLine()," ");
            int now = Integer.parseInt(stk.nextToken());
            graph[now] = new ArrayList<Edge>();

            while(true){
                int vertex = Integer.parseInt(stk.nextToken());
                if(vertex == -1) break;
                int weight = Integer.parseInt(stk.nextToken());
                graph[now].add(new Edge(vertex,weight));
            }
        }

        max = 0;
        maxVertex = NO_VERTEX;

        // 1에서 시작해 어떤 점점에서 가장 거리가 먼 정점을 택한다.
        DFS(NO_VERTEX,1,0);

        // 가장 거리가 먼 정점에서 출발해 트리의 지름을 구한다.
        DFS(NO_VERTEX,maxVertex,0);

        System.out.println(max);

    }

    static void DFS(int pre,int now,int dist){
        // 끝단 정점에 도달시 최대 거리를 갱신
        if(graph[now].size() == 1 && max < dist) {
            max = dist;
            maxVertex = now;
            return;
        }

        for(Edge e:graph[now]){
            if(e.vertex == pre) continue;
            DFS(now,e.vertex,dist+e.weight);
        }
    }
}
