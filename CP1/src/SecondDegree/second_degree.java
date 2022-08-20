package SecondDegree;

import java.util.Scanner;

public class second_degree {

	    public static void main(String[] args) {
	        Scanner scan = new Scanner (System.in);
	        double root1, root2;
	        
	        System.out.println("This program is designed to solve a second degree equation");
	        
	        System.out.println("Enter a");
	        int A = scan.nextInt();
	        
	        System.out.println("Enter b");
	        int B = scan.nextInt();
	        
	        System.out.println("Enter c");
	        int C = scan.nextInt();
	        
	        double determinant = B * B - 4 * A * C;
	        
	        // condition for real and different roots
	        if(determinant > 0) {
	            root1 = (-B + Math.sqrt(determinant)) / (2 * A);
	            root2 = (-B - Math.sqrt(determinant)) / (2 * A);
	            System.out.format("root1 = %.2f and root2 = %.2f", root1 , root2);
	        }
	        // Condition for real and equal roots
	        else if(determinant == 0) {
	            root1 = root2 = -B / (2 * A);
	            System.out.format("root1 = root2 = %.2f;", root1);
	        }
	        // If roots are not real
	        else {
	            double realPart = -B / (2 *A);
	            double imaginaryPart = Math.sqrt(-determinant) / (2 * A);
	            System.out.format("root1 = %.2f+%.2fi and root2 = %.2f-%.2fi", realPart, imaginaryPart, realPart, imaginaryPart);
	        }
	    }
	}
