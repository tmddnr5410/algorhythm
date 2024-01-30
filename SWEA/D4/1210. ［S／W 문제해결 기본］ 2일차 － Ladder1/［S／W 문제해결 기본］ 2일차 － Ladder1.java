import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution{
	
	static int[] dx = {0,0,-1};
	static int[] dy = {1,-1,0};
	
	 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	 static StringTokenizer st;
	 static int cnt;
	 static int[][] map = new int[100][100];
	 static final int RIGHT = 0;
	 static final int LEFT = 1;
	 static final int UP = 2;
	    
	    
	 public static void main(String[] args) throws IOException {
    	int nx,ny,x=0,y=0;
    	int direction = UP;
    	
    	
    	for(int tc=1; tc<11; tc++) {    // TC 10개 고정
    		// 입력
    		cnt = Integer.parseInt(br.readLine());    // TC 번호
    		
    		for(int j=0; j<100; j++) {
    			st = new StringTokenizer(br.readLine());
    			for(int k=0; k<100; k++) {
    				map[j][k] = Integer.parseInt(st.nextToken());
    				if(map[j][k] == 2) {
    					x=j;
    					y=k;
    				}
    			}
    		}
    		
    		while(x != 0) {
    			if(direction == RIGHT || direction == LEFT) {
    				nx = x+dx[UP];
    				if(0 <= nx && map[nx][y] == 1)
    					direction = UP;
    			}
    			
    			else if(direction == UP) {
    				for(int k=0;k<2;k++) {
    					ny = y+dy[k];
    					if(0<=ny && ny < 100 && map[x][ny] == 1)
            				direction = k;
    				}
    			}
    			x = x+dx[direction];
    			y = y+dy[direction];
    		}

    		bw.write("#"+tc+" "+y+"\n");
    		
    		}
    		
    		
    		
	 	bw.flush();
    	}

	}
