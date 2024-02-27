
import java.io.*;
import java.util.*;

class Voca implements Comparable<Voca>{
    String value;
    Voca(String value){
        this.value = value;
    }
    
    public int compareTo(Voca obj){
        if(this.value.length() == obj.value.length()){
            return this.value.compareTo(obj.value);
        }
        else{
            return this.value.length() - obj.value.length();
        }
    }
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        int N = Integer.parseInt(br.readLine());
        ArrayList<Voca> arr = new ArrayList<>();
        
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            
            if(isContains(arr,line))
            	continue;
            
        	arr.add(new Voca(line));
        }
        
        Collections.sort(arr);
		
        for(int i=0;i<arr.size();i++)
            System.out.println(arr.get(i).value);
	}
	
	public static boolean isContains(ArrayList<Voca> arr,String line) {
		
		 for(Voca voca: arr) {
			 if(voca == null)
				 return false;
			 
			 if(voca.value.equals(line))
         		return true;
		 }
         return false;
	}
	
}
