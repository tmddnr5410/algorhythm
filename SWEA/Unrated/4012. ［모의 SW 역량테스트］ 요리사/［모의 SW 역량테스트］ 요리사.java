import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	private static int N,R,cR,synSum;
	private static int[][] foods;
	private static Stack<Integer> foodIdx,synIdx;
	private static ArrayList<Integer> scores;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<T+1;tc++) {
			// N 받기
			N = Integer.parseInt(br.readLine());
			R = N/2;
	
			foodIdx = new Stack<>();
			synIdx = new Stack<>();
			scores = new ArrayList<>();
			
			foods = new int[N][N];
			for(int i=0;i<N;i++) {
				stk = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					foods[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			scores.clear();
			foodIdx.clear();
			chooseFood(0,0);
			
			bw.write("#"+tc+" "+minScore()+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	// 조합의 순서에 의해 첫번째 조합과 마지막 조합이 짝지어져 두개의 요리가 완성됨
	// 짝지어진 두 요리의 차가 가장 작은것을 최솟값으로 선정
	public static int minScore() {
		int len = scores.size();
		int half = len/2;
		int min = Integer.MAX_VALUE;
		int x,y;
		for(int i=0;i<half;i++) {
			x= scores.get(i);
			y= scores.get(len-1-i);
			min = Math.min(min, Math.abs(x-y));
		}
		return min;
		
	}
	
	// 모든 요리중에서 재료를 N/2개씩 선택
	public static void chooseFood(int depth,int now) {
		if(depth == R) {
			cook();
			return;
		}
		
		for(int i=now;i<N;i++) {
			foodIdx.add(i);
			chooseFood(depth+1,i+1);
			foodIdx.pop();
		}
	}
	
	// 현재 선택된 재료들에서, 2개씩 뽑아 시너지를 계산
	public static void synergy(int depth,int now) {
		if(depth == 2) {
			synSum += getSynergy(synIdx.get(0),synIdx.get(1));
			return;
		}
		
		for(int i=now;i<R;i++) {
			synIdx.add(foodIdx.get(i));
			synergy(depth+1,i+1);
			synIdx.pop();
		}
	}
	
	// 요리의 시너지를 계산한후 scores 배열에 저장
	public static void cook() {
		synSum=0;
		synIdx.clear();
		synergy(0,0);
		scores.add(synSum);
	}
	
	public static int getSynergy(int x,int y) {
		return foods[x][y] + foods[y][x];
	}
	
}