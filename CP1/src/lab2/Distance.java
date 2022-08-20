package lab2;

import java.util.Scanner;

public class Distance {
	
	public static void main(String[] args) {
		
		int x1;
		int x2;
		int y1;
		int y2;
		double distance;
		
		Scanner scan = new Scanner(System.in);
		
		// Get the coordinates of the first point
		System.out.println("For the first point of coordinates (X1;Y1). Enter X1");
		x1 = scan.nextInt();
		
		System.out.println("For the first point of coordinates (X1;Y1). Enter Y1");
		y1 = scan.nextInt();
		
		// Get the coordinates of the second point
				System.out.println("For the second point of coordinates (X2;Y2). Enter X2");
				x2 = scan.nextInt();
				
				System.out.println("For the second point of coordinates (X1;Y1). Enter Y2");
				y2 = scan.nextInt();
				
		// Computing distance
				distance = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
				System.out.println();
				
		// Display Results
				System.out.println("The Distance between these two points is " + distance);
		
	}

}
