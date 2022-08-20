package lab1;

import java.util.Random;
import java.util.Scanner;

public class guessing_game {
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		int numberToGuess = rand.nextInt(10) + 1;
		int numberOfTries = 0;
		int numberOfTriesLeft = 5;
		Scanner input = new Scanner (System.in);
		int guess;
		boolean win = false;
		
		while (win == false) {
			System.out.println("Guess a number between 1 and 10");
			System.out.println("You Have " + numberOfTriesLeft-- + " tries");
			guess = input.nextInt();
			numberOfTries++;
			
			if (guess == numberToGuess) {
				win = true;
			}
			
			else if (guess < numberToGuess) {
				System.out.println("Your guess is too low");
			}
			
			else if (guess > numberToGuess) {
				System.out.println("Your Guess is too high");
			}
			
			if (numberOfTries >= 5) {
				System.out.println("You Loose !");
				System.out.println("The number was " + numberToGuess);
				System.out.println("It took you " + numberOfTries + " tries");
				System.exit(0);
			}
			
		}
		
		System.out.println("You Win !");
		System.out.println("The number was " + numberToGuess);
		System.out.println("It took you " + numberOfTries + " tries");
		
	}
}