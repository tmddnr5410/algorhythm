import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		int N = Integer.parseInt(br.readLine());
		
		// 맨마지막 날짜에 대해 비교하기 위해 N+2로 설정
		int[] DP = new int[N+2];
		int[][] works = new int[N+2][2];
		
		for(int i=1;i<N+1;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			works[i][0] = Integer.parseInt(stk.nextToken());
			works[i][1] = Integer.parseInt(stk.nextToken());
		}
		
		// 해당 날짜에서 소모할 일과 급여를 저장하는 변수
		int day,pay;
		
		
		for(int now=N;1<=now;now--) {
			day = works[now][0];
			pay = works[now][1];
			
			// 해당 날짜에서 일을했을때 퇴사일을 넘어가게 되는 경우 선택하지 않음
			if(N < now+day-1) {
				DP[now] = DP[now+1];
				continue;
			}
			
			// 오늘 상담을 했을때 상담을 못하는 일동안 받는 pay보다 더 받을때 DP테이블을 갱신
			DP[now] = Math.max(DP[now+day] + pay, DP[now+1]);
			
		}
		
		System.out.println(DP[1]);
			
	}

}
