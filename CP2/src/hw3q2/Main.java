package hw3q2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	// Check if you can split an array into two groups with equal sums
	public static boolean equalSum(int[] a, int index ,int sum1, int sum2) {
		
		if(index >= a.length)
	        return sum1 == sum2;
	            
	    if(equalSum(a, index+1, sum1 + a[index], sum2))
	        return true;
	                      
	    if(equalSum(a, index+1, sum1, sum2 + a[index]))
	        return true;
	                                
	    return false;
		
	} 
			
	// Remove duplicate characters in a string
	public static String removeDuplicates(String s) {
		if ( s.length() <= 1 ) 
			return s;
	    if( s.substring(1,2).equals(s.substring(0,1)) ) 
	    	return removeDuplicates(s.substring(1));
	    else 
	    	return s.substring(0,1) + removeDuplicates(s.substring(1));
	}
	
	// Fibonacci sequence nth term finder
	public static int fibonacci(int n) {
		if(n<0)
			return -99;
		
		if(n==0)
			return 0;
		
		else if(n==1)
			return 1;
		
		else
			return fibonacci(n-1) + fibonacci(n-2);
		
	}
	
	
	// Check if you can split an array into two groups with equal sums without choosing an adjacent number in the array
	public static boolean equalSumNoAdjacent(int[] a, int i, int index) {
		if(index >= a.length)
	        return i == 0;
	          
	    if(equalSumNoAdjacent(a, i - a[index], index+2))
	        return true;
	                    
	    if(equalSumNoAdjacent(a, i, index+1))
	        return true;
	                              
	    return false;
	}
	
	public static void menu() {
		try {
		int choice = 0;
		while(choice!=5) {
		System.out.println("1. Equal Sum\n2. Remove Duplicates\n3. Fibonacci\n4. Sums but not adjacent\n5. Exit");
		System.out.println("------------------------------------------------------------------");
		System.out.print("Choice: ");
		Scanner scan = new Scanner(System.in);
		choice = scan.nextInt();
			try {
			switch(choice) {
			case 1:
				System.out.print("How many numbers do you want to add: ");
				int length = scan.nextInt();
				int[] array = new int[length]; 
				System.out.println("Enter the numbers: ");
				for(int i =0; i<length; i++) {
					array[i] = scan.nextInt();
				}
				
				boolean check = equalSum(array, 0 , 0 ,0);
				System.out.println("The result is: " + check +"\n");
				break;
				
			case 2:
				System.out.println("Enter a String: ");
				String s = scan.next();
				String ans;
				ans = removeDuplicates(s);
				System.out.println("\nResult: " + ans + "\n");
				break;
				
			case 3:
				System.out.print("Enter the n'th Fibonacci number location you want to find: ");
				int n = scan.nextInt();
				while(n<0) {
					System.out.println("Number must be positive, enter again: ");
					n = scan.nextInt();
				}
				int f = fibonacci(n);
				System.out.println("The " + n + "'th fibonnaci number is " + f + "\n");
				break;
				
			case 4:
				System.out.print("How many numbers do you want to add: ");
				int length2 = scan.nextInt();
				System.out.println("What is the number that you want to check the sum for: ");
				int nbr = scan.nextInt();
				int[] array2 = new int[length2]; 
				System.out.println("Enter the numbers in the array: ");
				for(int i =0; i<length2; i++) {
					array2[i] = scan.nextInt();
				}
				
				boolean check2 = equalSumNoAdjacent(array2, nbr , 0);
				System.out.println("The result is: " + check2 + "\n");
				break;
				
			case 5:
				System.out.println("Ended Program ...");
				System.exit(0);
				break;
				
				default:
					System.out.println("\nInvalid Choice, try again\n");
			}
		}
			
			catch(InputMismatchException e) {
				System.out.println("\nInvalid Choice, try again\n");
				scan.next();
			}
			catch(NegativeArraySizeException e) {
				System.out.println("\nInvalid Choice, try again\n");
				menu();
			}
	  }
		
	}
		
		catch(InputMismatchException e) {
			System.out.println("\nInvalid option, try again\n");
			menu();
		}
		
}
	
	// Method Main
	public static void main(String[] args) {
		menu();
	}

}
