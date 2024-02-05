import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main  {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<int[]> stack = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");
		
		int maxIdx= 0;
		int now=0;

		int pre = Integer.parseInt(stk.nextToken());
		int max = pre;
		sb.append("0").append(" ");
		stack.add(new int[] {max,maxIdx});
		
		
		for(int i=1;i<N;i++) {
			now = Integer.parseInt(stk.nextToken());
			
			//현재 닿을수있는 탑보다 더 큰 탑이 세워지는경우
			if(stack.peek()[0] < now) {
				// 닿을수 있는 탑이 나올때까지 스택에서 뻄
				while(!stack.isEmpty()) {
					max = stack.peek()[0];
					maxIdx = stack.peek()[1];
					if(now <= max)
						break;
					stack.pop();
				}
				
				// 스택이 비었다면 닿는 탑이 없기때문에 0으로 처리
				if(stack.isEmpty()) {
					maxIdx = 0;
				}
				// 스택에 추가
				stack.add(new int[] {now,i+1});
			}
			
			else {
				// 현재 탑의 레이저가 이전 탑에 닿는경우
				if(pre >= now) {
					// 현재탑을 스택에 추가후 해당 인덱스를 출력
					stack.add(new int[] {pre,i});
					maxIdx=i;
				}
				// 현재 탑의 레이저가 이전 탑에 안닿는 경우 가장 높은 탑에 닿게됨
				else if(pre < now){
					maxIdx = stack.peek()[1];
				}
			}
			
			pre = now;
			sb.append(maxIdx).append(" ");
		}
		
		System.out.print(sb); 
	
	}
}


/*
7
6 9 3 5 7 4 8
 * */
