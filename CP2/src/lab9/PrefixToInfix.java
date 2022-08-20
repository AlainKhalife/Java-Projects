package lab9;

import java.util.Stack;

public class PrefixToInfix {
	
	public static String preToInfix(String s){

        Stack<String> stack = new Stack<String>();
        for (int i = s.length()-1; i >=0 ; i--) {
            char c = s.charAt(i);

            if(c=='+' || c=='-' || c=='/' || c=='*'){
                String s1 = stack.pop();
                String s2 = stack.pop();
                String temp = s1+c+s2;
                stack.push(temp);
            }
	    else{
                stack.push(c+"");
            }
        }

        String ans=stack.pop();

        return ans;
    }
	
	public static void main(String[] args) {
		String s = "+AB";
		System.out.println(preToInfix(s));
	}

}
