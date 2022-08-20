package lab2;

public class test2 {
	
	public static String insertChar(String str, char c, int k) {
		String part1, part2, ans;
		part1 = str.substring(0, k);
		part2 = str.substring(k);
		
		ans = part1 + c + part2;
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(insertChar("alain khalife", 'S', 5));
		
	}

}
