package hw3q1;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class RecursiveTOH {

	    public static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod) 
	    { 
	        if (n == 1) 
	        { 
	            System.out.println("Move disk 1 from rod " +  from_rod + " to rod " + to_rod); 
	            return; 
	        } 
	        towerOfHanoi(n-1, from_rod, aux_rod, to_rod); 
	        System.out.println("Move disk " + n + " from rod " +  from_rod + " to rod " + to_rod); 
	        towerOfHanoi(n-1, aux_rod, to_rod, from_rod);
	    } 
	      
	    
	    public static void main(String args[])
	    { 
	        Scanner scan = new Scanner(System.in);
	        System.out.print("Enter the number of disks: ");
	        int n = scan.nextInt();
	        Instant start = Instant.now(); // This is used to measure the execution time of the method towerOfHanoi
	        towerOfHanoi(n, 'A', 'C', 'B');  // A, B and C are names of rods )
	        Instant end = Instant.now();
	        long elapsedtime = Duration.between(start, end).toMillis();
	        System.out.println("\nExceution time in milliseconds is " + elapsedtime);
	    } 
	    
	    /* Execution time for the following number of disks
	     * For n = 3, Time: 0.01 milliseconds
	     * For n = 5, Time: 0.01 milliseconds
	     * For n = 10, Time: 14 milliseconds
	     * For n = 100 , 500 and 1000 , Time it took is above 2 hours
	     */
} 

