package hw1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class University {
	
	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		Scanner scan = new Scanner(System.in);
		int choice = 0;  // This is used for the choice of the user in the switch
		int counter = 1; // This counter is used to check how many times the user enters invalid options

		while(choice != 5) {
			System.out.println("1. Add Employee");
			System.out.println("2. Delete Employee");
			System.out.println("3. List All");
			System.out.println("4. Raise Salary");
			System.out.println("5. Exit");
			System.out.println("---------------------------------------");
			System.out.print("Enter your Choice: ");
			
			// Trying the following block for any possible input mismatch exceptions
			try {
			choice =  scan.nextInt();
			System.out.println();
			
			switch(choice) {
			
			
			case 1:
				counter = 1; // Counter is reset to 1 because we entered the case, which means the option is valid.
				
				// Getting the information of the employee
				System.out.print("Enter the name of the employee: ");
				String name = scan.next();
				System.out.print("Enter the Date of recruitment of the employee: ");
				String dateofrec = scan.next();
				System.out.print("Enter the Salary of the employee: ");
				double salary = scan.nextDouble();
				System.out.println("Is this employee a Staff or Faculty ?");
				System.out.print("Choose an option: \n1. Staff\n2. Faculty\n");
				
				// Checking for the validity of the choice of the type of the employee
				System.out.print("Choice: ");
				int choice2 = scan.nextInt();
				while(choice2 < 1 || choice2 > 2) {
					System.out.print("Invalid choice. Enter again: ");
					choice2 = scan.nextInt();
				}
				
				// Creating appropriate employee type
				if(choice2==1) {
					Staff staff = new Staff(name, dateofrec, salary);
					employees.add(staff);
					System.out.println();
					System.out.println("Staff Added");
					System.out.println();
					break;
				}
				
				else {
					Faculty faculty = new Faculty(name, dateofrec, salary);
					employees.add(faculty);
					System.out.println();
					System.out.println("Faculty Added");
					System.out.println();
					break;
				}
				
			case 2:
				counter = 1;
				System.out.println("Enter the name of the employee you want to delete");
				String namee = scan.next();
				String delete;
				boolean found = false; // this is used so that when we exit the for loop so we don't print out Employee not found
				
				
				// Looping through the array of employees
				for(int i = 0; i<employees.size(); i++) {
					if(employees.get(i).getname().equalsIgnoreCase(namee)) {
						found = true; // Setting the boolean value to true because we found it
						
						// Asking the user to confirm the delete
						System.out.println();
						System.out.println("The follwing employee is about to get deleted: \n" + employees.get(i).toString() + "\nAre you sure you want to delete him (y/n) ?\n");
						System.out.print("Choice: ");
						delete = scan.next();
						if(delete.equalsIgnoreCase("y")) {
							employees.remove(i);
						System.out.println();	
						System.out.println("Employee Deleted");
						System.out.println();
						}
						
						else {
							found = true;
							System.out.println();
							System.out.println("Employee Not deleted");
							System.out.println();
					}
				}
			}
				
				// Since the employee was not found , the following if statement will be executed
				if(found == false) {
				System.out.println();
				System.out.println("Employee Not Found!");
				System.out.println();
				}
				break;
				
			case 3:
				counter = 1;
				System.out.println("Number of Employees: " + Employee.getSize());
				System.out.println();
				System.out.println("All Faculty:");
				System.out.println("---------------------------");
				
				// Printing Out all the employees of type Faculty
				for(int j = 0; j<employees.size(); j++) {
					// Making sure the employee is of type faculty
					if(employees.get(j) instanceof Faculty) {
						System.out.print(employees.get(j).toString());
						System.out.println();
					}
				}
				
				// Printing out all the employees of type staff
				System.out.println();
				System.out.println("All Staff: ");
				System.out.println("---------------------------");
				for(int d = 0; d<employees.size(); d++) {
					if(employees.get(d) instanceof Staff) {
						System.out.print(employees.get(d).toString());
						System.out.println();
					}
				}
				System.out.println();
				break;
				
			case 4:
				counter = 1;
				System.out.println("Enter the name of the employee you want to raise the salary for");
				String name2 = scan.next();
				boolean found2 = false; // Boolean used in order not to print employee not found
				
				// Looping through the array and searching for the employee
				for(int z = 0; z<employees.size(); z++) {
					if(employees.get(z).getname().equalsIgnoreCase(name2)) {
						found2 = true;
						System.out.println();
						System.out.println("The following employee is about to get a raise:\n" + employees.get(z).toString() + "\nEnter the percentage you want to raise for the salary: ");
						double percentage = scan.nextDouble();
						employees.get(z).raiseSalary(percentage);
						System.out.println("Salary raised by " + percentage + " %");
					}
				}
				
				if(found2==false) {
				System.out.println();
				System.out.println("Employee Not Found");
				System.out.println();
				}
				break;
				
			case 5:
				System.out.println("Ending Program ...");
				break;
				
			default:
				// Increasing counter if the user enters an invalid INTEGER
				counter++;
				System.out.println("You entered an invalid value");
				System.out.println();
				
				// If the counter passes 5, the program will end
				if(counter>5) {
					System.out.println("You entered an invalid option 5 times consecutivly");
					System.out.println("Ending program ...");
					System.exit(0);
				}
		   }
		}
		
		// Catching the Input Mismatch Exception
			
		catch(InputMismatchException e) {
			counter++; // Counter is raised because the input is wrong
			
			// If the counter is above 5, the program is terminated
			if(counter>5) {
				System.out.println();
				System.out.println("You entered an invalid option 5 times consecutivly");
				System.out.println("Ending program ...");
				System.exit(0);
			}
			
			// Displaying an invalid input
			System.out.println();
			System.out.println("You entered an invalid value, 1");
			System.out.println();
			
			choice=0;  // Choice is reset to 0
			scan.next();
		}
		
	}
  }
}