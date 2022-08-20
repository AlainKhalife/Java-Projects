package lab2;

public class test {
	
	public static int factorial(int N) { 
		
		int ans = 1;
		
		if(N>0) {
		for(int i = 1; i<=N; i++)
		    ans = ans * i;
		    
		return ans;
	  }
		
		else
			return 1;
    }
	
	public static void main(String[] args) {
		int c = factorial(10);
		System.out.println(c);
	}
}
