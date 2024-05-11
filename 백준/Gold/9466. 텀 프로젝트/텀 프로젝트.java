import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
    private static int[] map;
    private static int[] visit;
    private static Stack<Integer> stack;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stack = new Stack<>();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=T;tc++) {
            int N = Integer.parseInt(br.readLine());

            map = new int[N];
            visit = new int[N];

            StringTokenizer stk = new StringTokenizer(br.readLine()," ");
            for(int i=0;i<N;i++) 
                map[i] = Integer.parseInt(stk.nextToken()) - 1;
            
            int teamTotal = N;

            for(int i=0;i<N;i++) {
                if(visit[i] != 0) continue;
                
                int seq = i+1;  // i 라는 증가하는 정수를 이용해 현재 순회를 몇번했는지 체크 학생 번호와 관계없음
 
                // i번에 순회를 시작한다. 팀이 생긴다면 팀원의 숫자를 구한다
                int cnt = DFS(i, seq);
                
                // 결성된 팀원을 전체숫자에서 뺀 나머지는 팀을 이루지 못한 팀원수
                teamTotal -= cnt;
            }

            bw.write(teamTotal+"\n");

        }
        bw.flush();
        bw.close();
    }
    public static int DFS(int now,int seq) {
        // 이번 순회에서 같은 seq번호를 만나면 사이클이 생긴것
        if(visit[now] == seq) {
            int count = 0;
            
            // 자신의 번호가 나올때까지 스택에서 뺴내어 총 팀원을 구한다
            while(!stack.isEmpty() && stack.pop() != now)
                count++;
            
            stack.clear();
            
            return count+1;
        }

        // 사이클이 없는 경우 이미 방문한 정점에 도착했다면 사이클이 없는것
        if(visit[now] != 0)
            return 0;

        // 아직 방문하지 않은 정점을 방문
        stack.push(now);
        visit[now] = seq;
        return DFS(map[now],seq);

    }
}

