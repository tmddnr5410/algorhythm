import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<int[]> numbers;
	static int N;

	// 입력받은 수들로 가능한 부분집합 구성 : 경우의 수의 비트마스킹 활용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		N = Integer.parseInt(br.readLine());
		numbers = new ArrayList<>();
		
		for (int i = 0; i < N; ++i) {
			stk = new StringTokenizer(br.readLine()," ");
			numbers.add(new int[] {Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken())});
		}
		
		// 결과가 고정인 경우에 대한 처리
		if(N == 1)
			System.out.println(Math.abs(numbers.get(0)[0]-numbers.get(0)[1]));
		else
			System.out.println(materialSubset(N));

	}

	private static int materialSubset(int n) {
		int cnt = 1 << n;
		int sour = 1;
		int bitter = 0;
		int min = Integer.MAX_VALUE;
		
		// 재료의 각자리수를 0 과 1로 구분함
		for (int i = 1; i < cnt; ++i) {
			sour = 1;
			bitter = 0;
			for (int position = 0; position < N; ++position) {
				// 각 인덱스가 1인경우 -> 재료를 사용하는 경우
				if((i & 1 << position) != 0 ) {
					sour *=numbers.get(position)[0];
					bitter += numbers.get(position)[1];
				}
			}
			min = Math.min(Math.abs(sour-bitter), min);
		}
		return min;
	}

}
