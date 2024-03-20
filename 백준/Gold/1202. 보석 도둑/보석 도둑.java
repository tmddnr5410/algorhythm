import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Gem implements Comparable<Gem>{
    int weight;
    int price;

    public Gem(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int compareTo(Gem obj){
        if(this.weight < obj.weight)
            return -1;
        else if (this.weight > obj.weight)
            return 1;
        return 0;
    }
}

class PriceGem implements Comparable<PriceGem>{
    int weight;
    int price;


    public PriceGem(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int compareTo(PriceGem obj){
        if(this.price < obj.price)
            return 1;
        else if(this.price > obj.price)
            return -1;
        return 0;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());

        // 가장 비싼 보석이 먼저인 큐
        PriorityQueue<PriceGem> gemPQue = new PriorityQueue<>();
        // 가장 무거운 보석이 먼저인 리스트
        ArrayList<Gem> remainGems = new ArrayList<>();
        // 가장 작은 가방 순서대로 정렬할 리스트
        ArrayList<Integer> packs = new ArrayList<>();

        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(stk.nextToken());
            int price = Integer.parseInt(stk.nextToken());
            remainGems.add(new Gem(weight,price));
        }
        
        for(int i=0;i<K;i++){
            packs.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(remainGems);
        Collections.sort(packs);

        long sum = 0;
        int remainIdx = 0;

        // 현재 담을수 있는 가방에 대해 가치가 가장 큰 보석을 담는다.
        // 처음에는 보석의 무게대로 정렬해 현재 가방에 대해 넣을수 있는 보석 후보를 모두 우선순위 큐에 넣는다.
        // 우선순위큐의 보석은 보석의 값대로 정렬해 현재 넣을수 있는 보석중 가장 비싼 보석을 꺼낼수 있다.
        // 이때 가방이 무게순으로 정렬되어 있기 때문에
        // 우선순위에 있는 모든 보석은 다음 가방에도 넣을수 있음.
        
        for(int pack:packs){
            // 현재 가방에 넣을수 있는 모든 보석을 큐에 넣는다.
            while(remainIdx < N && remainGems.get(remainIdx).weight <= pack){
                int weight = remainGems.get(remainIdx).weight;
                int price = remainGems.get(remainIdx).price;

                gemPQue.add(new PriceGem(weight,price));

                remainIdx++;
            }

            // 큐에서 가장 비싼 보석을 뺀다.
            if(!gemPQue.isEmpty()) {
                sum += gemPQue.poll().price;
            }
        }

        System.out.print(sum);
    }
}
