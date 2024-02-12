import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk= new StringTokenizer(br.readLine()," ");
        Queue<int[]> que = new LinkedList<>();

        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());

        // 방문과 거리를 확인할 배열. 가지않은 위치는 큰값으로 지정해 거리를 찾으면 갱신하도록
        int[] visited = new int[100001];

        for(int i=0;i<100001;i++){
            visited[i] = Integer.MAX_VALUE;
        }

        int ans = 100001;
        visited[N] = 0;

        int[] now;
        int idx,dist;

        que.add(new int[] {N,0});

        while(!que.isEmpty()){
            now = que.poll();
            idx = now[0];
            dist = now[1];

            // 최단거리를 찾으면 break
            if(idx == K){
                ans = dist;
                break;
            }

            // 갈수있는 위치들에 대해 확인후 큐에 추가
            // 이떄 새로운 최소거리가 존재할경우 갱신
            
            if(idx*2 <= 100000 && visited[idx] < visited[idx*2]){
                que.add(new int[] {idx*2,dist});
                visited[idx*2] = dist;
            }

            if(idx+1 <= 100000 && visited[idx] < visited[idx+1]){
                que.add(new int[] {idx+1,dist+1});
                visited[idx+1] = dist+1;
            }

            if(0 <= idx-1 && visited[idx] < visited[idx-1]){
                que.add(new int[] {idx-1,dist+1});
                visited[idx-1] = dist+1;
            }

        }

        // 남아있는 큐에서 더 적은 거리가 남아있다면 최소거리 갱신
        while(!que.isEmpty()){
            now = que.poll();
            idx = now[0];
            dist = now[1];

            if(idx == K){
                ans = Math.min(ans,dist);
            }
        }

        System.out.print(ans);

    }
}
