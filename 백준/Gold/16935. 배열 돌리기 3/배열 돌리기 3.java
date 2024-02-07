import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N,M,R,shape;
	private static int[][] mapL;
	private static int[][] mapC;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine()," ");

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		
		mapL = new int[N][M];
		mapC = new int[M][N];
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				mapL[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		shape=0;
		int cmd;
		stk = new StringTokenizer(br.readLine()," ");
		for(int r=0;r<R;r++) {
			cmd = Integer.parseInt(stk.nextToken());
			switch (cmd){
				case 1:
					upDown();
					break;
				case 2:
					leftRight();
					break;
				case 3:
					roundR();
					break;
				case 4:
					roundL();
					break;
				case 5:
					tonadoR();
					break;
				case 6:
					tonadoL();
					break;
			}
		}
		printMap();
	}
	
	public static void printMap() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] lines = mapL;
		if(shape == 1)
			lines = mapC;
		for(int[] line:lines) {
			for(int num:line) {
				bw.write(num+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
	
	public static void upDown() {
		int temp,half = N/2;
		if(shape == 0) {
		for(int idx = 0;idx<M;idx++) {
			for(int chg = 0;chg<half;chg++) {
				temp = mapL[N-1-chg][idx];
				mapL[N-1-chg][idx] = mapL[chg][idx];
				mapL[chg][idx] = temp;
			}
		}
		}
		else {
		for(int idx = 0;idx<M;idx++) {
			for(int chg = 0;chg<half;chg++) {
				temp = mapC[N-1-chg][idx];
				mapC[N-1-chg][idx] = mapC[chg][idx];
				mapC[chg][idx] = temp;
			}
		}
		}
	}

	
	public static void leftRight() {
		int temp,half = M/2;

		if(shape == 0) {
			for(int idx = 0;idx<N;idx++) {
				for(int chg = 0;chg<half;chg++) {
					temp = mapL[idx][M-1-chg];
					mapL[idx][M-1-chg] = mapL[idx][chg];
					mapL[idx][chg] = temp;
				}
			}
		}
		else {
			for(int idx = 0;idx<N;idx++) {
				for(int chg = 0;chg<half;chg++) {
					temp = mapC[idx][M-1-chg];
					mapC[idx][M-1-chg] = mapC[idx][chg];
					mapC[idx][chg] = temp;
				}
			}
		}
	}
	
	public static void roundR() {
		int[][] src=mapC,target=mapL;
		
		if(shape == 1) {
			src = mapL;
			target = mapC;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				src[j][N-(i+1)] = target[i][j];
			}
		}
		
		changeShape();
	}

	public static void roundL() {
		int[][] src=mapL,target=mapC;
		
		if(shape == 1) {
			src = mapC;
			target = mapL;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				target[M-(j+1)][i] = src[i][j];
			}
		}
		
		changeShape();
	}

	public static void tonadoR() {
		int halfN = N/2;
		int halfM = M/2;
		
		int[][] pre = new int[halfN][halfM];
		int[][] temp = new int[halfN][halfM];
		
		int[][] target=mapL;
				
		if(shape == 1) {
			target = mapC;
		}
		
		for(int i=0;i<halfN;i++) {
			for(int j=0;j<halfM;j++) {
				pre[i][j] = target[i][j];
			}
		}
		
		for(int i=0;i<halfN;i++) {
			for(int j=0;j<halfM;j++) {
				temp[i][j] = pre[i][j];
				pre[i][j] = target[i][j+halfM];
				target[i][j+halfM] = temp[i][j];
			}
		}
		

		for(int i=0;i<halfN;i++) {
			for(int j=halfM,p=0;j<M;j++,p++) {
				temp[i][p] = pre[i][p];
				pre[i][p] = target[i+halfN][j];
				target[i+halfN][j] = temp[i][p];
			}
		}
		

		for(int i=halfN,px=0;i<N;i++,px++) {
			for(int j=halfM,py=0;j<M;j++,py++) {
				temp[px][py] = pre[px][py];
				pre[px][py] = target[i][j-halfM];
				target[i][j-halfM] = temp[px][py];
			}
		}
		

		for(int i=halfN,px=0;i<N;i++,px++) {
			for(int j=0;j<halfM;j++) {
				temp[px][j] = pre[px][j];
				pre[px][j] = target[i-halfN][j];
				target[i-halfN][j] = temp[px][j];
			}
		}
		
	}
	

	public static void tonadoL() {
		int halfN = N/2;
		int halfM = M/2;
		
		int[][] pre = new int[halfN][halfM];
		int[][] temp = new int[halfN][halfM];
		
		int[][] target=mapL;
				
		if(shape == 1) {
			target = mapC;
		}
		
		for(int i=0;i<halfN;i++) {
			for(int j=0;j<halfM;j++) {
				pre[i][j] = target[i][j];
			}
		}
		
		for(int i=0;i<halfN;i++) {
			for(int j=0;j<halfM;j++) {
				temp[i][j] = pre[i][j];
				pre[i][j] = target[i+halfN][j];
				target[i+halfN][j] = temp[i][j];
			}
		}
		


		for(int i=halfN,px=0;i<N;i++,px++) {
			for(int j=0;j<halfM;j++) {
				temp[px][j] = pre[px][j];
				pre[px][j] = target[i][j+halfM];
				target[i][j+halfM] = temp[px][j];
			}
		}

		for(int i=halfN,px=0;i<N;i++,px++) {
			for(int j=halfM,py=0;j<M;j++,py++) {
				temp[px][py] = pre[px][py];
				pre[px][py] = target[i-halfN][j];
				target[i-halfN][j] = temp[px][py];
			}
		}
		

		for(int i=0;i<halfN;i++) {
			for(int j=halfM,py=0;j<M;j++,py++) {
				temp[i][py] = pre[i][py];
				pre[i][py] = target[i][j-halfM];
				target[i][j-halfM] = temp[i][py];
			}
		}
	}
	public static void changeShape() {
		int temp = N;
		N = M;
		M = temp;
		
		if(shape == 1)
			shape = 0;
		else
			shape = 1;
	}
	
}
