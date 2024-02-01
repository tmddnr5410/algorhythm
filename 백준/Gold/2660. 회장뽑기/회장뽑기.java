import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
	
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				if(i!=j)
					map[i][j] = 51;
			}
		}
		
		int start=0,end;
		while(true) {
			stk = new StringTokenizer(br.readLine()," ");
			start = Integer.parseInt(stk.nextToken());
			end = Integer.parseInt(stk.nextToken());
			if(start == -1)
				break;
			map[start][end] = 1;
			map[end][start] = 1;
			
		}
	
	
		for(int k=1;k<N+1;k++) {
			for(int i=1;i<N+1;i++) {
					for(int j=1;j<N+1;j++) {
							// 더 짧게갈수 있는 거리가 있으면 갱신
							map[i][j] = Math.min(map[i][k] + map[k][j],map[i][j]);
					}
				}
			}
		
		
		
		int min = 51,cnt=0;
		int [] scores=new int[N+1];
		
		for(int i=1;i<N+1;i++) {
			// 해당 회원의 점수를 구하고
			for(int j=1;j<N+1;j++) {
				if(map[i][j] != 51)
					scores[i] = Math.max(map[i][j], scores[i]);
			}
			
			// score가 0 인 경우 => 거리가 51(경로없음)인 경로밖에 없어서 점수 초기화가 안된경우
			if(scores[i] != 0) {
				// 점수가 더 낮은 회원이 나타나면 새로운 후보로 하거나
				if(min > scores[i]) {
					min = scores[i];
					cnt=1;
				}
				else if(min == scores[i]){
					cnt++;
				}
			}
			
		}
		
		System.out.printf("%d %d \n",min,cnt);
		for(int i=1;i<N+1;i++) {
			if(scores[i] == min)
				System.out.printf("%d ",i);
		}
	}

}
