import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int L;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		ArrayList<int[]> traps = new ArrayList<>();
		N=Integer.parseInt(stk.nextToken());
		L= Integer.parseInt(stk.nextToken());
		
		int start,end,dist,lastPosition=-1;
		
		int boardCount=0,boardMargin=0;
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			start = Integer.parseInt(stk.nextToken());
			end = Integer.parseInt(stk.nextToken());
			traps.add(new int[] {start,end});
			
		}
		traps.sort(Comparator.comparing(a->a[0]));
		for(int[] trap:traps)
		{
			start = trap[0];
			end=trap[1];
			if(end<lastPosition) {
				continue;
			}
			
			if(start < lastPosition) {
				start = lastPosition;
			}

			dist = end-start;
			boardCount += dist/L;
			boardMargin = dist %L;
			
			if(dist % L != 0) {
				boardCount++;
				lastPosition = end + L - boardMargin;
			}
		}
		System.out.println(boardCount);
	}	

}
