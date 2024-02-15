import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        int T = Integer.parseInt(br.readLine());
        char[] line;
        String answer;
        for(int i=0;i<T;i++){
            answer = "YES";
            stack.clear();
            line = br.readLine().toCharArray();
            for(char ch:line) {
                if (ch == '(')
                    stack.push(0);

                else if (ch == ')') {
                    if (stack.isEmpty()) {
                        answer="NO";
                        break;
                    }
                    stack.pop();
                }
            }
            if(!stack.isEmpty())
                answer="NO";
            bw.write(answer+"\n");
        }
        bw.flush();
        bw.close();
    }
}
