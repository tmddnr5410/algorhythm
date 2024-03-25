import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N;
        int count=0;
        while(true){
            ArrayList<String> musics = new ArrayList<>();
            N=Integer.parseInt(br.readLine());

            if(N==0) break;

            for(int i=0;i<N;i++)
                musics.add(br.readLine());

            Collections.sort(musics);

            bw.write((++count)+"\n");

            for(String music:musics)
                bw.write(music+"\n");
        }

        bw.flush();
        bw.close();

    }
}
