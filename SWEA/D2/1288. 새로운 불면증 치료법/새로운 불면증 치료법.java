
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,t;
        t= Integer.parseInt(br.readLine());
        for(int tc=0;tc<t;tc++) {
            N = Integer.parseInt(br.readLine());
            int check = 0, ans = (1 << 10) - 1;

            String sheep;
            int count = 0, i = 1;

            while (ans != check) {
                count += N;
                sheep = Integer.toString(count);
                for (int j = 0; j < sheep.length(); j++) {
                    check = check | (1 << (sheep.charAt(j) - '0'));
                }
            }

            System.out.printf("#%d %d\n",(tc+1),count);
        }
    }
}
