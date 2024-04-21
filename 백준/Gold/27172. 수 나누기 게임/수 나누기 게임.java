import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        
        boolean[] isExist = new boolean[1000001];
        int[] nums = new int[N];
        int[] score = new int[1000001];

        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(stk.nextToken());
            isExist[n] = true;
            nums[i] = n;
        }


        // 카드에 적힌 수가 가장 작은 플레이어부터 모든 사람과 대전한다
        // 점수가 작은 플레이어는 다음 모든 플레이어에 대해 이기거나, 무승부이기 때문에 점수가 확정된다
        for(int player:nums){
            for(int i=2;i<=1000000;i++){
                // i = 2 부터 1000000까지 곱하며 이길수 있는 카드를 가진 적들을 고려한다.
                int enemy = player*i;
                // 1000000이 넘는 카드는 존재하지 않으니 다음 플레이어로
                if(enemy > 1000000) break;
                // 해당 카드를 든 사람이 없으면 pass
                if(!isExist[enemy]) continue;
                
                // 해당카드를 가진 사람이 있다면 점수를 얻고, 그 카드를 든사람의 점수를 뺀다
                score[player]++;
                score[enemy]--;
            }
        }

        // 정렬하기 이전의 순서로 점수를 출력하기 위해 sequence 대로 점수를 출력한다.
        for(int player:nums)
            bw.write(score[player]+" ");
        bw.flush();
        bw.close();
    }
}
