package lab7;

//****************************************************************
//Dates.java
//
//Determine whether a 2nd-millenium date entered by the user
//is valid          
//****************************************************************
import java.util.Scanner;

public class Dates
{
 public static void main(String[] args)
 {
	int month, day, year;   //date read in from user
	int daysInMonth;        //number of days in month read in 
	boolean monthValid = true, yearValid = true, dayValid = true;  //true if input from user is valid
	boolean leapYear;       //true if user's year is a leap year

   Scanner scan = new Scanner(System.in);

	//Get integer month, day, and year from user
    System.out.println("Enter the month, date and year seperated by a space");
    month = scan.nextInt();
    day = scan.nextInt();
    year = scan.nextInt();
    
  //User number of days in month to check to see if day is valid
    if(day < 1 || day > 31)
    	dayValid = false;
    

	//Check to see if month is valid
    while(month < 1 || month > 12)
    {
    	monthValid = false;
    }

	//Check to see if year is valid
    while(year < 1000 || year > 1999)
    {
    	yearValid = false;
    }

	//Determine whether it's a leap year
    if(year%400 == 0 || year%4 == 0 )
    	leapYear = true;
    
    else leapYear = false;

	//Determine number of days in month
    if(month%3 == 0)
    	daysInMonth = 31;
    else if(leapYear == true && month == 2)
    	daysInMonth = 29;
    else if(leapYear == false && month == 2)
    	daysInMonth = 28;
    else
    	daysInMonth = 30;


	//Determine whether date is valid and print appropriate message
    if(dayValid == true && monthValid == true && yearValid == true && leapYear == true ){
    	System.out.println("Date is Valid");
    	System.out.println("This is a leap year");
    }
    
    else if(dayValid == true && monthValid == true && yearValid == true && leapYear == false ){
    	System.out.println("Date is Valid");
    	System.out.println("This is not leap year");
    }
    
    else 
    	System.out.println("Date is invalid");

 }
}

