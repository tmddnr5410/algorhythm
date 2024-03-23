import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


class Shark{
    int speed;
    int dir;
    int size;
    int x;
    int y;

    public Shark(int x, int y,int speed, int dir, int size) {
        this.speed = speed;
        this.dir = dir;
        this.size = size;
        this.x = x;
        this.y = y;
    }
}

public class Main {
    private static int R,C,M;
    private static int[][] map;
    private static ArrayList<Shark> sharks;
    private static Set<Integer> deadSharks;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[R][C];
        sharks = new ArrayList<>();
        deadSharks = new HashSet<>();

        for(int i=0;i<M;i++) {
            stk = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());

            map[r-1][c-1] = z;
            sharks.add(new Shark(r-1,c-1,s,d,z));
        }

        int answer = 0;

        for(int i=0;i<C;i++) {
            // 낚시왕이 잡아야할 상어를 잡는다.
            int deadShark = 0;

            for(int j=0;j<R;j++){
                if(map[j][i] != 0){
                    deadShark = map[j][i];
                    map[j][i] = 0;
                    answer += deadShark;
                    break;
                }
            }

            // 잡은 상어를 죽음표시한다.
            deadSharks.add(deadShark);

            moveShark();
        }

        System.out.print(answer);

    }

    public static void moveShark(){
        // 상어가 움직인 자리에 움직이지 않은 상어가 있어 먹힐수 있기 때문에
        // 움직인 상어의 정보를 새로운 공간에 저장한다.
        int[][] nextMap = new int[R][C];

        for(Shark now:sharks){
            // 0. 죽은상어로 표시한 상어는 건너뛴다.
            if(deadSharks.contains(now.size)) continue;

            // 상어가 움직일 남은거리, 총길이, 속도를 구한다. 속도는 길이 * 2 이상부터 동일한 위치를 반복하기 떄문에 나머지연산을 해준다.
            int remain = remainDist(now);
            int line = totalDist(now.dir);
            int speed = (now.speed)%(line*2);

            int d = 0;

            // 남은거리가 속도보다 작은경우 거리만큼 움직여준다.
            if(speed <= remain){
                if(now.dir == 2)
                    d = now.x + speed;
                else if (now.dir == 3)
                    d = now.y + speed;
                else if(now.dir == 1)
                    d = now.x - speed;
                else
                    d = now.y - speed;
            }
            else if(speed <= remain+line){  // 남은거리 + 총거리보다 작거나 같은경우, 남은거리를 간후 끝에서부터 다시 움직인것.
                // 끝까지 갔다가 돌아오기 때문에 방향이 바뀐다.
                if(now.dir%2 == 0)
                    now.dir--;
                else
                    now.dir++;

                if(now.dir == 2 || now.dir == 3) {
                    d = speed - remain;
                }
                else {
                    d = line - (speed - remain);
                }

            }
            else if(remain+line < speed){ // 남은 거리+총 길이 보다 속도가 큰경우 왕복 후 상어가 더 이동한다.
                if(now.dir == 2 || now.dir == 3)
                    d = speed - (remain+line);
                else
                    d = line - (speed - (remain+line));
            }

            // 구한 위치를 x / y 값에 맞게 넣어준다.
            if(now.dir == 1 || now.dir == 2)
                now.x = d;
            else
                now.y = d;


            // 상어가 해당자리로 움직이고, 먹거나 먹힌다.
            if(nextMap[now.x][now.y] == 0)
                nextMap[now.x][now.y] = now.size;
            else {
                int smallShark = now.size;

                // 현재 움직인 상어가 더 크면, 움직인 상어가 죽는다.
                if (nextMap[now.x][now.y] < now.size) {
                    smallShark = nextMap[now.x][now.y];
                    nextMap[now.x][now.y] = now.size;
                }

                deadSharks.add(smallShark);
            }
        }

        // 맵을 다음 맵으로 바꾼다.
        map = nextMap;
    }


    public static int remainDist(Shark shark){
        if(shark.dir == 1)// 방향이 위
            return shark.x;

        if(shark.dir == 2)// 방향이 아래인경우
            return (R-1) - shark.x;

        if(shark.dir == 3)// 방향이 오른쪽인경우
            return (C-1) - shark.y;

        //if(shark.dir == 4) // 방향이 왼쪽
        return shark.y;
    }

    public static int totalDist(int direction){
        if(direction == 1 || direction == 2)// 방향이 세로
            return R-1;
        else    // 방향이 가로
            return C-1;

    }

}