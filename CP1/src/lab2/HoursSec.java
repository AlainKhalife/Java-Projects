package lab2;

import java.util.Scanner;

public class HoursSec {
	
	public static void main (String[] args) {
		
		int seconds;
		
		Scanner scan = new Scanner(System.in);
		
		// Enter the value in seconds
		System.out.println("Enter the Value in seconds");
		seconds = scan.nextInt();
		
		// Conversion process
		int hours = seconds /3600;
		int seconds1 = seconds - (hours*3600);
		int minutes = seconds1 / 60;
		int seconds2 = seconds1 - (minutes*60);
		
		
		// Results
        System.out.println("Hours :" + hours );
        System.out.println("Minutes :" + minutes );
        System.out.println("Seconds :" + seconds2 );
	}
	
}