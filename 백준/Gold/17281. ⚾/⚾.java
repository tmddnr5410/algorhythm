import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Base64;
import java.util.StringTokenizer;

public class Main {
	private static int N,maxScore=0;
	private static boolean[] visit;
	private static int[] order;
	private static int[][] result;
	private static int p=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		N = Integer.parseInt(br.readLine());
		
		result = new int[N][9];
		
		for(int inning=0;inning<N;inning++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int No=0;No<9;No++) {
				result[inning][No] = Integer.parseInt(stk.nextToken()); 
			}
		}
		
		order = new int[9];
		visit = new boolean[9];
		
		
		perm(0);
		System.out.println(maxScore);
	}
	
	private static void perm(int depth) {
        // 순열이 만들어지면 게임 ㄱㄱ
		if(depth == 9) {
			maxScore = Math.max(maxScore, play());
			return;
		}
		// 4번째 선수는 고정
		if(depth == 3) {
			order[depth] = 0; 
			perm(depth + 1);
		}
		// 나머지 순서는 그대로
		else {
			for(int i=1;i<9;i++) {
				if(visit[i]) continue;
				visit[i] = true;
				order[depth] = i; 
				perm(depth + 1);
				visit[i]= false; 
			}
		}
	}
	
	private static int play() {
		int now = 0;
		int base = 0;
		int score = 0;
		
		for(int inning = 0; inning < N;inning++) {
			int out = 0;
            // base = 000
			base = 0;
            
			while(out != 3) {
				int bat = result[inning][order[(now++)%9]];
				switch (bat) {
				case 0:
					out+=1;
					break;

				case 1:
                    // base = 1XX 이면 점수증가
					if(isHome(base))
						score++;
                    // base 를 왼쪽으로 한번밀고, 3자리로 유지
					base = (base<<1)&7;
                    // base = 0X1
					base = base+1;
					break;
					
				case 2:
					for(int i=0;i<2;i++) {
						if(isHome(base))
							score++;
						base = (base<<1)&7;
					}
                    // base = X10
					base = base+2;
					break;
					

				case 3:
					for(int i=0;i<3;i++) {
						if(isHome(base))
							score++;
						base = (base<<1)&7;
					}
                    // base = 100
					base = base+4;
					break;
					

				case 4:
					for(int i=0;i<4;i++) {
						if(isHome(base))
							score++;
						base = (base<<1)&7;
						base = base+1;
					}
                    // base = 000
					base = 0;
					break;
				}
			}
		}
		return score;
	}
	
	
	public static boolean isHome(int base) {
		return base>>2 == 1;
	}
	
}
