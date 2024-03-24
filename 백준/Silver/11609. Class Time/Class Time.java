import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Name implements Comparable<Name>{
    String first;
    String second;

    public Name(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Name o) {
        if(second.compareTo(o.second) == 0) {
            return first.compareTo(o.first);
        }
        return second.compareTo(o.second);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Name> names = new ArrayList<>();

        for(int i=0;i<N;i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine()," ");
            names.add(new Name(stk.nextToken(),stk.nextToken()));
        }

        Collections.sort(names);

        for(Name name:names)
            bw.write(name.first+" "+name.second+"\n");

        bw.flush();
        bw.close();
    }
}
