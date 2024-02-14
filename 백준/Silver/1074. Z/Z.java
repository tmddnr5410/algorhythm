import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(stk.nextToken());
		int r = Integer.parseInt(stk.nextToken());
		int c = Integer.parseInt(stk.nextToken());
		System.out.println(conquer(N,r,c,0));
	}
	
	public static int conquer(int N,int r,int c,int now) {
		if(N==1)
			return now+(2*r)+(1*c);
		
		int size = (int) Math.pow(4, N-1);
		int sectionSize = (int) Math.pow(2, N);
		int half = sectionSize/2;
		
		int sectionX = r/half;
		int sectionY = c/half;
		
		// 4개의 큰 영역으로 나눈후 
		if(sectionX == 0 && sectionY == 0)
			return conquer(N-1,r,c,now);
		else if(sectionX == 0 && sectionY == 1)
			return conquer(N-1,r,c-half,now+size);
		else if(sectionX == 1 && sectionY == 0)
			return conquer(N-1,r-half,c,now+size*2);
		else
			return conquer(N-1,r-half,c-half,now+size*3);
	}

}
