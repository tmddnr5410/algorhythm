import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static int[] canPrime = {1,3,7,9};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		backTracking("2",1);
		backTracking("3",1);
		backTracking("5",1);
		backTracking("7",1);
	}
	public static void backTracking(String now,int depth) {
		if(!isPrime(Integer.parseInt(now)))
			return;
		
		if(depth == N) {
			System.out.println(now);
			return;
		}
		
		for(int p:canPrime) {
			backTracking(now+p,depth+1);
		}
		
	}
	
	public static boolean isPrime(int num) {
		for(int i=2;i<Math.sqrt(num);i++) {
			if(num%i == 0) 
				return false;
		}
		return true;
	}
}
