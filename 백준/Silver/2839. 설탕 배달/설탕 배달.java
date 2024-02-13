import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int ans=-1;
		int cnt = N / 5;
		int sub = N % 5;
		
		for(int i=cnt; 0<=i; i--) {
			sub = N - i*5;
			if(sub%3 == 0) {
				ans = i + sub/3;
				break;
			}
		}
		
		System.out.println(ans);
	}

}
