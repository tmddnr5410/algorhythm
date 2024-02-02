import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(int tc =1;tc<11;tc++) {
			br.readLine();
			char[] brackets = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			char now;
			int answer=1;
			for(char bracket: brackets) {
				if(bracket == '(' || bracket == '{' || bracket == '[' || bracket == '<')
					stack.push(bracket);
				else{
					now = stack.pop();
					if(now == '(' && bracket != ')') {
						answer = 0;
						break;
					}
					if(now == '[' && bracket != ']') {
						answer = 0;
						break;
					}
					if(now == '{' && bracket != '}') {
						answer = 0;
						break;
					}
					if(now == '<' && bracket != '>') {
						answer = 0;
						break;
					}
				}
				
			}
			System.out.printf("#%d %d\n",tc,answer);
		}
	
	}
}
