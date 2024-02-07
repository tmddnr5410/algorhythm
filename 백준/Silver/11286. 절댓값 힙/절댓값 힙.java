
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class abs implements Comparable<abs>{
	private int num;

	public int getN() {
		return num;
	}

	public abs(int num) {
		super();
		this.num = num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "abs [num=" + num + "]";
	}

	@Override
	public int compareTo(abs o) {
		int absThis = Math.abs(this.num);
		int absOther= Math.abs(o.num);
		
		//절대값이 같으면 음수를 기준으로
		if(absThis == absOther) {
			if(this.num <o.num)
				return -1;
			else
				return 1;
		}
		
		else if(absThis < absOther)
			return -1;
		
		// else if(absThis > absOther)
		return 1;
	}
	
}
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<abs> que = new PriorityQueue<>();
		
		int command,now=0;
		for(int i=0; i<N;i++) {
			command = Integer.parseInt(br.readLine());
			if(command == 0) {
				now = 0;
				if(!que.isEmpty())
					now = que.poll().getN();
				System.out.println(now);
			}
			else
				que.add(new abs(command));
		}
		
		
		
	}

}
