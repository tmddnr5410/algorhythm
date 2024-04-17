import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        HashMap<String,Integer> index = new HashMap<>();
        ArrayList<String> sequence = new ArrayList<>();
        ArrayList<String> cipher = new ArrayList<>();

        for(int tc = 0;tc<T;tc++){
            index.clear();
            sequence.clear();
            cipher.clear();

            int N = Integer.parseInt(br.readLine());
            stk = new StringTokenizer(br.readLine()," ");
            for(int i=0;i<N;i++)
                sequence.add(stk.nextToken());


            stk = new StringTokenizer(br.readLine()," ");
            for(int i=0;i<N;i++)
                index.put(stk.nextToken(), i);

            stk = new StringTokenizer(br.readLine()," ");
            for(int i=0;i<N;i++)
                cipher.add(stk.nextToken());

            for(int i=0;i<N;i++)
                bw.write(cipher.get(index.get(sequence.get(i))) + " ");
            
            bw.write("\n");
        }
        bw.flush();


    }
}
