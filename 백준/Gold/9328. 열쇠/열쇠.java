import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Door{
	int x;
	int y;
	boolean tried;
	
	public Door(int x, int y, boolean tried) {
		this.x = x;
		this.y = y;
		this.tried = tried;
	}
	
}

class Dot{
	int x;
	int y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

public class Main {
	private static char[][] map;
	private static int N,M,total,answer;
	private static boolean[][] visit;
	private static ArrayList<Door>[] doors;
	private static Queue<Dot> que;
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		doors = new ArrayList[26];
		que = new LinkedList<Dot>();
		
		for(int i=0;i<26;i++) 
			doors[i] = new ArrayList<>();
		
		for(int tc=0;tc<T;tc++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			map = new char[N+2][M+2];
			visit = new boolean[N+2][M+2];
			que.clear();
			answer = 0;
			initDoor();
			initMap();
			
			for(int i=1;i<N+1;i++) {
				String line = br.readLine();
				for(int j=1;j<M+1;j++) {
					map[i][j] = line.charAt(j-1);
					
					if(0 <= map[i][j]-'A' && map[i][j] - 'A' < 26) 
						doors[map[i][j]-'A'].add(new Door(i, j, false));
					
				}
			}
			
			char[] initKey = br.readLine().toCharArray();
			
			if(initKey[0] != '0') {
				for(char key:initKey) {
					getKey(key-'a');
				}
			}
			que.add(new Dot(0, 0));
			while(!que.isEmpty()) {
				Dot now = que.poll();
				//System.out.println();
				//printMap(now.x,now.y);
				for(int d=0;d<4;d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];
					if(nx < 0 || N+2 <= nx || ny < 0 || M+2 <= ny) continue;
					if(visit[nx][ny] || map[nx][ny] == '*') continue;
					
					if(map[nx][ny] == '.') {
						visit[nx][ny] = true;
						que.add(new Dot(nx, ny));
					} else if(map[nx][ny] == '$'){
						visit[nx][ny] = true;
						que.add(new Dot(nx, ny));
						map[nx][ny] = '.';
						answer++;
					}else if(0 <= map[nx][ny]-'A' && map[nx][ny] - 'A' < 26) {
						meetDoor(nx,ny,map[nx][ny]-'A');
					} else if(0 <= map[nx][ny]-'a' && map[nx][ny] - 'a' < 26) {
						getKey(map[nx][ny]-'a');
						visit[nx][ny] = true;
						que.add(new Dot(nx, ny));
						map[nx][ny] = '.';
					}
				}
			}
			
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
		
	}

	public static void printMap(int nx,int ny) {
		for(int i=0;i<N+2;i++) {
			for(int j=0;j<M+2;j++) {
				if(nx == i && ny == j) System.out.print("@");
				else System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void initDoor() {
		for(int i=0;i<26;i++) 
			doors[i].clear();
	}
	
	public static void initMap() {
		for(int i=0;i<N+2;i++) {
			for(int j=0;j<M+2;j++) {
				map[i][j] = '.';
			}
		}
	}
	

	public static void meetDoor(int x,int y,int d) {
		for(Door door:doors[d]) 
			if(door.x==x && door.y==y) // 이미 문을 열려고 시도함을 표시한다.
				door.tried = true;
		
	}
	
	public static void getKey(int key) {
		for(Door door:doors[key]) { // 해당 키로 열리는 문의 좌표들에 대해
			
			map[door.x][door.y] = '.'; // 문을 열고
			
			if(door.tried) { // 이미 문을 열려고 한적 있다면 다시 돌아갈수 없으니 방문한다.
				que.add(new Dot(door.x, door.y));
				visit[door.x][door.y] = true;
			}
			
		}
	}
}
