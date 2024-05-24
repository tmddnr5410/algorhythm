import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] line = br.readLine().toCharArray();
		
		int now = 0;
		
		int n = line.length;
		
		while(true) {
			if(now == n) {
				System.out.println("YES");
				break;
			}
			
			if(now+1 < n && line[now] == 'p' && line[now+1] == 'i')
				now = now+2;
			else if(now+1 < n && line[now] == 'k' && line[now+1] == 'a')
				now = now+2;
			else if(now+2 < n && line[now] == 'c' && line[now+1] == 'h' && line[now+2] == 'u')
				now = now+3;
			else {
				System.out.println("NO");
				break;
			}
		}
		
		
	}

}
