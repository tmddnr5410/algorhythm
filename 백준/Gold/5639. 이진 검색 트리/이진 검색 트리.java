import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Node{
	Node left;
	Node right;
	int key;
	
	public Node(int key) {
		this.key = key;
	}
	
	public void insert(int num) {
		if(num < this.key) {
			if(this.left == null)
				this.left = new Node(num);
			else 
				this.left.insert(num);
		}
		
		if(num >= this.key) {
			if(this.right == null)
				this.right = new Node(num);
			else 
				this.right.insert(num);
		}
	}
}

public class Main {
	private static BufferedWriter bw;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int key = Integer.parseInt(br.readLine());
		Node root = new Node(key);
		
		while(true) {
			String line = br.readLine();
			
			if(line == null)
				break;
			
			root.insert(Integer.parseInt(line));
		}
		preOrder(root);
		bw.flush();
		bw.close();
		
	}

	private static void preOrder(Node now) throws IOException {
		if(now.left != null) // 왼쪽 노드 먼저 방문
			preOrder(now.left);
		if(now.right != null) // 오른쪽 노드 방문
			preOrder(now.right);
		visit(now); // 모든 노드 방문후 자신을 방문
	}
	
	private static void visit(Node now) throws IOException {
		bw.write(now.key+"\n");
	}
	
}
