package lab7;

import java.util.Scanner;

public class Roll {
	
	public static void main(String[] args){
		
		String another = "y";
		
		MDie die1 = new MDie();
		RDie die2 = new RDie();
		int[] diecount1 = new int[7];
		int[] diecount2 = new int[7];
		Scanner scan = new Scanner(System.in);
		
		while(another.equalsIgnoreCase("y")){
		
		System.out.println("Enter a number between 6000 and 12000");
		
		int N = scan.nextInt();
		
		// Count occurences of MDie (die1)
		for(int i=0; i<N; i++){
			die1.flip();
			diecount1[die1.get()]++;
		}
		
		// Count occurences of RDie(die2)
		for(int j=0; j<N; j++){
			die2.flip();
			diecount2[die2.get()]++;
		
	}

	    // Output Results
		for(int c=1; c<=6; c++)
		{
			System.out.println("Occurence of number " + c + " for the first die are: " + diecount1[c]);
		}
		
		System.out.println();
		
		for(int d=1; d<=6; d++)
		{
			System.out.println("Occurence of number " + d + " for the second die are: " + diecount2[d]);
		}
		
		System.out.println();
		System.out.println("Do you want to run the test again ?");
		another = scan.next();
	
		}
		
  }
}	
