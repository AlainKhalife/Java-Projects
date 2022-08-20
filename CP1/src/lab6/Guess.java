package lab6;

import java.util.Scanner;
import java.util.Random;
public class Guess
{
public static void main(String[] args)
{
int numToGuess; //Number the user tries to guess
int guess; //The user's guess
Scanner scan = new Scanner(System.in);
Random generator = new Random();

int tooLow = 0;
int tooHigh = 0;

numToGuess = generator.nextInt(10)+1;    //randomly generate the number to guess

System.out.print("Guess a number between 1 and 10 "); //print message asking user to enter a guess
guess = scan.nextInt(); //read in guess

      while (guess != numToGuess ) //keep going as long as the guess is wrong
       {
		
	
		if (guess < numToGuess) {
			System.out.println("Your guess is too low, Try Again"); //print message saying guess is wrong
			guess = scan.nextInt(); //get another guess from the user
			tooLow++;
			
		}
		
		else if (guess > numToGuess) {
			System.out.println("Your Guess is too high, Try Again");
			guess = scan.nextInt();
			tooHigh++;
		}
		
		
		}
		

System.out.println("Congratulations, Your guess is true"); //print message saying guess is right
System.out.println("Number of too high guesses: " + tooHigh);
System.out.println("Number of too low guesses: " + tooLow);

	}

}

