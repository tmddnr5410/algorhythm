import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

 class Station{
    int idx;
    int dist;
     public Station(int idx, int dist) {
         this.idx = idx;
         this.dist = dist;
     }
 }

public class Main {
    private static int N,K,M;
    private static boolean[] visitStation;
    private static boolean[] visitTube;
    private static ArrayList<Integer>[] stations;
    private static ArrayList<Integer>[] tubes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        visitTube = new boolean[M];
        tubes = new ArrayList[M];

        visitStation = new boolean[N];
        stations = new ArrayList[N];

        // i번 역이 지나가는 튜브번호들
        for(int i=0;i<N;i++)
            stations[i] = new ArrayList<Integer>();

        Queue<Station> que = new LinkedList<>();


        for(int i=0;i<M;i++) {
            stk = new StringTokenizer(br.readLine()," ");
            tubes[i] = new ArrayList<Integer>();
            for(int j=0;j<K;j++) {
                int now = Integer.parseInt(stk.nextToken()) - 1; // 역이 0부터 시작하도록 정규화
                tubes[i].add(now);
                stations[now].add(i);
            }
        }

        int min = 9999;
        que.add(new Station(0,1));

        visitStation[0] = true;
        while(!que.isEmpty()){
            // 큐에서 튜브 하나를 뺸다
            Station now = que.poll();

            // 현재 튜브가 끝 정점이 포함된 튜브라면 최소거리 갱신
            if(N-1 == now.idx){
                min = now.dist;
                break;
            }

            // 역에 연결된 튜브을 보며 방문하지 않은 튜브가 있는지 확인한다.
            for(int i=0;i<stations[now.idx].size();i++) {
            	// 이미 방문한 튜브는 더 방문할 필요가 없음
            	Integer tube = stations[now.idx].get(i);
            	if(visitTube[tube]) continue;
            	visitTube[tube] = true;
            	
            	// 각 튜브에 연결된 역들을 방문처리
            	for(int j=0;j<tubes[tube].size();j++) {
            		// 이미 방문한 역은 다시 방문할 필요가 없음
            		Integer station = tubes[tube].get(j);
            		if(visitStation[station]) continue;
            		visitStation[station] = true;
            		
            		// 다음 탐색을 위해 큐에 추가
            		que.add(new Station(station, now.dist+1));
            	}
            }
        }

        if(min == 9999)
        	min = -1;
        System.out.println(min);
    }
}