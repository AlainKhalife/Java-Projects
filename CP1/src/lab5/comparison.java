package lab5;

import java.util.Scanner;

public class comparison {
	
	public static void main (String[] args) {
		
		int num1;
		int num2;
		int num3;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 3 numbers seperated by a space :");
		
		num1 = scan.nextInt();
		num2 = scan.nextInt();
		num3 = scan.nextInt();
		
		if (num1 < num2  && num1 < num3)
			System.out.println("Smallest is " + num1);
		
		else if(num2 < num3)
			System.out.println("Smallest number is " + num2);
		
		else System.out.println("Smallest number is " + num3);
		
		
	}
	

}
