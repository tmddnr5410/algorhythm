import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Node{
	int left;
	int right;
	
	public Node(int left, int right) {
		this.left = left;
		this.right = right;
	}

	
}

public class Main {
	private static BufferedWriter bw;
	private static Node[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		tree = new Node[N];
		
		// A~Z까지 트리의 번호를 붙여 관리
		for(int i=0;i<N;i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			int now = stk.nextToken().charAt(0) - 'A';
			int left = stk.nextToken().charAt(0) - 'A';
			int right = stk.nextToken().charAt(0) - 'A';
			
			if(left < 0 || 26 < left)
				left = -1;
			
			if(right < 0 || 26 < right)
				right = -1;
			
			tree[now] = new Node(left,right);
		}
		
		preOrder(0);
		bw.write("\n");
		midOrder(0);
		bw.write("\n");
		postOrder(0);
		
		bw.flush();
		bw.close();
	}

	public static void preOrder(int now) throws IOException {
		if(now == -1)
			return;
		
		visit(now);
		preOrder(tree[now].left);
		preOrder(tree[now].right);
	}	

	public static void midOrder(int now) throws IOException {
		if(now == -1)
			return;
		
		midOrder(tree[now].left);
		visit(now);
		midOrder(tree[now].right);
	}
	
	public static void postOrder(int now) throws IOException {
		if(now == -1)
			return;
		
		postOrder(tree[now].left);
		postOrder(tree[now].right);
		visit(now);
	}
	public static void visit(int now) throws IOException {
		bw.write((char)(now+'A'));
	}
}
