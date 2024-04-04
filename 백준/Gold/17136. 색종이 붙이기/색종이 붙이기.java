import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final int N = 10;
	private static int[][] paper;
	private static int[] remain;
	private static int min;
	private static Stack<int[]> stack;
	public static void main(String[] args) throws IOException {				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		paper = new int[N][N];
		remain = new int[5];
		
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		for(int i=0;i<5;i++)
			remain[i] = 5;
		
		min = 30;
		stack = new Stack<>();
		
		DFS(0,0,0);
		
		if(min == 30)
			min=-1;
		
		System.out.println(min);
		
	}
	
	private static void DFS(int x,int y,int paperCount) {
		
		if(x == N-1 && y == N) {
			if(isBlind()) 
				min = Math.min(paperCount,min);
			return;
		}
		
		if(y == N) {
			DFS(x+1,0,paperCount);
			return;
		}
		
		if(paper[x][y] == 0) {
			DFS(x,y+1,paperCount);
			return;
		}
		
		// 이미 놓은 종이보다 더 많이 놓았다면
		if(paperCount > min)
			return;
	
		
		// 각 자리에 종이를 놓는다.
		for(int size = 0;size<5;size++) {
			if(0 < remain[size] && canPut(x,y,size+1)) {
				usePaper(x,y,size+1);
				remain[size]--;
				DFS(x,y+1,paperCount+1);
				removePaper(x, y, size+1);
				remain[size]++;
			}
		}
		
	}
	private static int[][] copyPaper() {
		int [][] arr = new int[N][N];
		for(int i=0;i<N;i++) 
			for(int j=0;j<N;j++) 
				arr[i][j] = paper[i][j];
		return arr;
	}
	
	private static void printPaper(int[][] arr) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) 
				System.out.print(arr[i][j]);
		System.out.println();
		}
	}
	
	private static void usePaper(int x,int y,int size) {
		for(int i=0;i<size;i++) 
			for(int j=0;j<size;j++) 
				paper[x+i][y+j] = 0;
			
		
	}
	
	private static void removePaper(int x,int y,int size) {
		for(int i=0;i<size;i++) 
			for(int j=0;j<size;j++) 
				paper[x+i][y+j] = 1;
			
		
	}
	
	private static boolean isBlind() {
		for(int i=0;i<N;i++) 
			for(int j=0;j<N;j++) 
				if(paper[i][j] == 1)
					return false;
			
		
		return true;
	}
	
	private static boolean canPut(int x,int y,int size) {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(x+i >= N || y+j >= N) return false;
				if(paper[x+i][y+j] == 0) return false;
			}
		}
		return true;
	}
}
