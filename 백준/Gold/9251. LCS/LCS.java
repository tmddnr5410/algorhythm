import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String temp;

        if(s1.length() < s2.length()){
            temp = s2;
            s2 = s1;
            s1 = temp;
        }

        int[][] DP = new int[s2.length()+1][s1.length()+1];
        for(int j=0;j<s2.length();j++){
            for(int i=0;i<s1.length();i++){
                // 값이 같다면 새로운 수열을 저장하기 위해 대각선 위쪽에서 1을 더함
               if(s1.charAt(i) == s2.charAt(j)) {
                    DP[j+1][i+1] = DP[j][i]+1;
               }
               // 값이 다르다면 현재 문자를 포함시킨 수열 VS 이전문자까지의 최대 수열으로 갱신
               else{
                   DP[j+1][i+1] = Math.max(DP[j+1][i] , DP[j][i+1]);
               }
            }
        }

        System.out.print(DP[s2.length()][s1.length()]);
    }
}
