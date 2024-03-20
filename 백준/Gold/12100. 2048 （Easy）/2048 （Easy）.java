import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int map[][];
	private static int N,max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		max=0;
		DFS(0);
		System.out.println(max);
	}

	public static void DFS(int depth) {
		if(depth == 5) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					max = Math.max(map[i][j],max);
			}
		}
			return;
		}
		
		int[][] nowMap = map;
        
		map = Right();
		DFS(depth+1);
		map = nowMap;

		map = Down();
		DFS(depth+1);
		map = nowMap;
		
		map = Up();
		DFS(depth+1);
		map = nowMap;
		
		map = Left();
		DFS(depth+1);
		map = nowMap;
	}
	
	public static int[][] Right(){
		int[][] nextMap = new int[N][N];
		
		for(int i=0;i<N;i++) {
			int box = 0;
			int stack = N-1;
			
			for(int j=N-1;0<=j;j--) {
				if(map[i][j] != 0) { // 0이 아닌 블럭에 대해
					if(box == 0) {  // 처음 만나는 box인 경우 저장
						box = map[i][j];
					}
					else {  // 앞에 box가 있었고
						if(map[i][j] == box) {  // 짝이 맞는경우 쌓인 끝부터 쌓는다.
							nextMap[i][stack--] = box*2;
							box = 0;
						}
						else { // 이전 box를 그대로 쌓고 짝을 찾기위해 box에 map[i][j]저장
							nextMap[i][stack--] = box;
							box = map[i][j];
						}
					}
				}
			}
			// 맨 마지막 박스가 빠지지 못한경우 빼준다.
			if(box != 0)
				nextMap[i][stack--] = box;
		}
		return nextMap;
	}
	
	public static int[][] Left(){
		int[][] nextMap = new int[N][N];
		
		for(int i=0;i<N;i++) {
			int box = 0;
			int stack = 0;
			
			for(int j=0;j<N;j++) {
				if(map[i][j] != 0) { // 0이 아닌 블럭에 대해
					if(box == 0) {  // 처음 만나는 box인 경우 저장
						box = map[i][j];
					}
					else {  // 앞에 box가 있었고
						if(map[i][j] == box) {  // 짝이 맞는경우 쌓인 끝부터 쌓는다.
							nextMap[i][stack++] = box*2;
							box = 0;
						}
						else { // 이전 box를 그대로 쌓고 짝을 찾기위해 box에 map[i][j]저장
							nextMap[i][stack++] = box;
							box = map[i][j];
						}
					}
				}
			}
			// 맨 마지막 박스가 빠지지 못한경우 빼준다.
			if(box != 0)
				nextMap[i][stack++] = box;
		}
		return nextMap;
	}
	
	public static int[][] Down(){
		int[][] nextMap = new int[N][N];
		
		for(int i=0;i<N;i++) {
			int box = 0;
			int stack = N-1;
			
			for(int j=N-1;0<=j;j--) {
				if(map[j][i] != 0) { // 0이 아닌 블럭에 대해
					if(box == 0) {  // 처음 만나는 box인 경우 저장
						box = map[j][i];
					}
					else {  // 앞에 box가 있었고
						if(map[j][i] == box) {  // 짝이 맞는경우 쌓인 끝부터 쌓는다.
							nextMap[stack--][i] = box*2;
							box = 0;
						}
						else { // 이전 box를 그대로 쌓고 짝을 찾기위해 box에 map[i][j]저장
							nextMap[stack--][i] = box;
							box = map[j][i];
						}
					}
				}
			}
			// 맨 마지막 박스가 빠지지 못한경우 빼준다.
			if(box != 0)
				nextMap[stack--][i] = box;
		}
		return nextMap;
	}
	
	public static int[][] Up(){
		int[][] nextMap = new int[N][N];
		
		for(int i=0;i<N;i++) {
			int box = 0;
			int stack = 0;
			
			for(int j=0;j<N;j++) {
				if(map[j][i] != 0) { // 0이 아닌 블럭에 대해
					if(box == 0) {  // 처음 만나는 box인 경우 저장
						box = map[j][i];
					}
					else {  // 앞에 box가 있었고
						if(map[j][i] == box) {  // 짝이 맞는경우 쌓인 끝부터 쌓는다.
							nextMap[stack++][i] = box*2;
							box = 0;
						}
						else { // 이전 box를 그대로 쌓고 짝을 찾기위해 box에 map[i][j]저장
							nextMap[stack++][i] = box;
							box = map[j][i];
						}
					}
				}
			}
			// 맨 마지막 박스가 빠지지 못한경우 빼준다.
			if(box != 0)
				nextMap[stack++][i] = box;
		}
		return nextMap;
	}
	
}
