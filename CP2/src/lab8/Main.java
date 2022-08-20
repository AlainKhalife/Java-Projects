package lab8;

public class Main {
	
	private Stack s1;
	private Stack s2;
	
	public Main() {
		s1 = new Stack();
		s2 = new Stack();
	}
	
	public int evaluate(String s) {
		for(int i =0; i<s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				int j = i;
				String number = "";
				while(Character.isDigit(s.charAt(j))) {
				number += s.charAt(j);
				j++;
				}
				s1.push(Integer.parseInt(number));
				i=j-1;
			}
			
			else if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='*' || s.charAt(i)=='/')
				s2.push(s.charAt(i));
			
			else if(s.charAt(i)==')') {
				int result;
				int nbr1 = (int) s1.pop();
				int nbr2 = (int) s1.pop();
				char operator = (char) s2.pop();
				
				if(operator == '+')
					result = nbr1+nbr2;
				
				else if(operator == '*')
					result = nbr1*nbr2;
				
				else if(operator == '-')
					result = nbr2-nbr1;
				
				else
					result = nbr2/nbr1;
				
				s1.push(result);
			}
		}
		
		return (int) s1.peek();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		System.out.println(m.evaluate("(((110+5)*(6-4))-((9*2)+(4-1)))"));
	}

}
