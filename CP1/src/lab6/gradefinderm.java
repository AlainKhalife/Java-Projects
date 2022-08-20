package lab6;

import java.util.Scanner;

public class gradefinderm {
	
		static Scanner scan = new Scanner(System.in);
		
		public static int readValid(String Message){
			System.out.println("Enter the grade of " + Message);
			int Grade = scan.nextInt();
			while(Grade<0 || Grade>100){
				System.out.println("Invalid Enter Again");
				Grade = scan.nextInt();
			}
			return Grade;
		}
		
		public static char letterGrade(double Avx){
			char letter;
			if (Avx>=90)
				letter ='A';
			
			else if(Avx>=80)
				letter = 'B';
			
			else if (Avx>=70)
				letter ='C';
			
			else if (Avx>=60)
				letter ='D';
			
			else
				letter ='F';
			return letter;
			
		}
		
		public static double bestTwo(double t1, double t2, double t3){
			double BestTwo;
			if (t1<t2 && t1<t3)
			BestTwo = (t2+t3)*0.3;
			
			else if (t2>t3)
				BestTwo = (t1+t2)*0.3;
			
			else BestTwo = (t1+t2)*0.3;
			
			return BestTwo;
			
			
		}
		
	}

