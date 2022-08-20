package lab2;

//*******************************************************************
//Average.java
//Read three integers from the user and print their average
//*******************************************************************

import java.util.Scanner;
public class Average
{
public static void main(String[] args)
{
 int val1, val2, val3;
 double average;
	 Scanner scan = new Scanner(System.in) ;

 // get three values from user
 
	 System.out.println("Enter the first value ");
	 val1 = scan.nextInt();
	 
	 System.out.println("Enter the second value ");
	 val2 = scan.nextInt();
	 
	 System.out.println("Enter the third Value ");
	 val3 = scan.nextInt();


 //compute the average

   average = ((val1 + val2 + val3)/3);

 //print the average

   System.out.println("The avarage is " + average);

}
}
