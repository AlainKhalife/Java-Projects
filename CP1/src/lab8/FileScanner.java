package lab8;

import java.util.Scanner;              // Needed to use Scanner for input
import java.io.File;                   // Needed to use File
import java.io.FileNotFoundException;  // Needed for file operation
   
public class FileScanner { 
   public static void main(String[] args) 
         throws FileNotFoundException {  // Needed for file operation
  
 // Declare the int variable
	   int integer;
 //Declare the floating point number
	   double doublenbr;
 //Declare the string variable
	   String str;
 //Declare a variable to hold the sum
	   double sum;
  
// Create a file object that takes "input.txt" as input
	   File file1 = new File("E:\\Alain Khalife\\eclipse-workspace\\lab8\\in.txt");
// Setup a Scanner to read from the text file that you created
	   Scanner scan = new Scanner(System.in);
// use nextInt() to read the integer
	   System.out.println("Enter the integer");
	   integer  = scan.nextInt();
// use nextDouble() to read double
	   System.out.println("Enter the double");
	   doublenbr = scan.nextDouble();
// use next() to read String
	   System.out.println("Enter a string");
	   scan.nextLine();
	   str = scan.nextLine();
   
 // Display the output as specified at the beginning of the question
	   sum = integer + doublenbr;
	   System.out.println("The Integer read is " + integer);
	   System.out.println("The floating point number read is " + doublenbr);
	   System.out.println("The String read is \"" + str + "\"");
	   System.out.println("Hi! " + str + ", the sum of " + integer + " and " + doublenbr + " is " + sum);
	   
      
   }
}
