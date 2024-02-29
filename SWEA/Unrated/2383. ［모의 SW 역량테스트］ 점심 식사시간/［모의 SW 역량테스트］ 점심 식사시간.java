
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int N,totalHuman;
	private static ArrayList<int[]> humanDots;
	private static ArrayList<int[]> stairDots;
	private static int[] first,second;
	private static int firstLen,secondLen,min;
	private static int[][] stair;
	private static boolean[] visit;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk;
		humanDots = new ArrayList<>();
		stairDots = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			firstLen = 0;
			secondLen = 0;
			humanDots.clear();
			stairDots.clear();
			
			for(int i=0;i<N;i++) {
				stk = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					int n = Integer.parseInt(stk.nextToken());
					
					if(n == 1)
						humanDots.add(new int[] {i,j});
					// 계단 길이를 알아놓는다
					if(n != 1 && n != 0) {
						stairDots.add(new int[] {i,j});
						if(firstLen == 0)
							firstLen = n;
						else
							secondLen = n;
					}
					
				}
			}
			
			// 사람과 계단의 거리를 구한다
			totalHuman = humanDots.size();
			stair = new int[2][humanDots.size()];
			
			for(int i=0;i<2;i++) {
				int stairX = stairDots.get(i)[0];
				int stairY = stairDots.get(i)[1];
				for(int j=0;j<humanDots.size();j++) {
					int humanX = humanDots.get(j)[0];
					int humanY = humanDots.get(j)[1];
					stair[i][j] = Math.abs(stairX - humanX) + Math.abs(stairY - humanY);
				}
			}
			
			// 각 사람이 두 계단으로 내려가는 모든 경우에 대해 계산
			visit = new boolean[totalHuman];
			min = Integer.MAX_VALUE;
			set(0);
			bw.write("#"+tc+" "+(min+1)+"\n");
		}
		bw.flush();
		bw.close();
		
	}
	
	private static void set(int depth) {
		if(depth == totalHuman) {

			first = new int[totalHuman];
			second = new int[totalHuman];
			// 어떤 사람이 어떤 계단으로 내려갈지 결정
			for(int i=0;i<totalHuman;i++) {
				if(visit[i])
					first[i] = stair[0][i];
				else
					second[i] = stair[1][i];
			}

			// 탈출시킨다
			int a= exit();
			if(a == 8) {
			}
			min = Math.min(min,a);
			return;
		}
		visit[depth] = true;
		set(depth+1);
		visit[depth] = false;
		set(depth+1);
		
	}

	private static int exit() {
		int exitCount = 0,time=0;
		
		int[] firstStair = new int[firstLen];
		int[] secondStair = new int[secondLen];
		
		while(true) {
			time++;
            
			// 계단의 사람들을 한명씩 내리고, 탈출한 사람이 있다면 탈출 수를 키운다
			
			exitCount+=downStair(firstStair);
			exitCount+=downStair(secondStair);
			
			

			if(exitCount == totalHuman)
				break;
			
			// 계단으로 가는중인 사람들을 움직인다
			for(int i=0;i<totalHuman;i++) {
				if(first[i] == 1 && canDown(firstStair)) {
						first[i] = 0;
						firstStair[0]++;
				}
				else if(first[i] > 1)
					first[i]--;
				
				if(second[i] == 1 && canDown(secondStair)) {
					second[i] = 0;
					secondStair[0]++;
				}
				else if(second[i] > 1)
					second[i]--;
			}
		}
		
		return time;
		
		
	}
	
	private static boolean canDown(int[] stair) {
		int sum = 0,len = stair.length;
		// 한명씩 이동
		for(int i=0;i<len;i++) {
			sum += stair[i];
			if(sum == 3)
				return false;
		}
		
		return true;
	}

	
	
	private static int downStair(int[] stair) {
		int len = stair.length;
		// 마지막 사람은 탈출 가능
		int isExit = stair[len-1];
		
		// 한명씩 이동
		for(int i=len-1;0<i;i--) {
			stair[i] = stair[i-1]; 
		}
		
		stair[0] = 0;
		
		return isExit;
	}
}
