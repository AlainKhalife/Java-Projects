package lab6;

import java.util.Scanner;

public class Grade_finder {
	
	public static void main(String[] args)
	{
		System.out.println("Grade Computing Application");
		System.out.println("By Alain Khalife");
		System.out.println("---------------------------");
		int T1 = gradefinderm.readValid("Test 1");
		int T2 = gradefinderm.readValid("Test 2");
		int T3 = gradefinderm.readValid("Test 3");
		int fin = gradefinderm.readValid("Final");
		
		double Besttwo = gradefinderm.bestTwo(T1, T2, T3);
		double avg = Besttwo + fin*0.4;
		char LG = gradefinderm.letterGrade(avg);
		
		System.out.println("Your average is " + avg);
		System.out.println("Your letter grade is " + LG);
		System.out.println("");
		System.out.println("Thank You");
	}

}
