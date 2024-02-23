
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Stuff{
	private int weight;
	private int value;
	public int getWeight() {
		return weight;
	}
	public int getValue() {
		return value;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Stuff(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
	
}

public class Main {
	private static int N,K;
	private static int max = 0;
	private static Stuff[] stuffs;
	private static Stuff[][] DP;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		stuffs = new Stuff[N+1];
		DP = new Stuff[N+1][K+1];

		
		stuffs[0] = new Stuff(0, 0); // 1번의 물건비교를 위한 물건을 채움
		for(int i=1;i<=N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			stuffs[i] = new Stuff(Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()));
		}
		
		for(int i=0;i<=K;i++) {
			DP[0][i] = new Stuff(0, 0); 
		}
		
		for(int i=0;i<=K;i++) {
			// i 번 무게를 만들수있는 최대 합 구하기
			for(int j=1;j<=N;j++) {
				int jWeight = stuffs[j].getWeight();
				int jValue = stuffs[j].getValue();
				DP[j][i] = new Stuff(DP[j-1][i].getWeight(),DP[j-1][i].getValue()); 
				if(i-jWeight >= 0 && DP[j-1][i-jWeight].getValue() + jValue > DP[j-1][i].getValue()) {
					DP[j][i].setValue(DP[j-1][i-jWeight].getValue() + jValue);
					DP[j][i].setWeight(DP[j-1][i-jWeight].getWeight() + jWeight);
				}
			}
		}
		
		
		System.out.println(DP[N][K].getValue());
		
	}
}
