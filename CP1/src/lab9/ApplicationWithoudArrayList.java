package lab9;

import java.util.Scanner;

public class ApplicationWithoudArrayList {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		StudentCollection stdc = new StudentCollection(10);
		String another = "y";
		do{
		
		// Prompting on the screen
		System.out.println("1. Add a student");
		System.out.println("2. Delete a student");
		System.out.println("3. Compute fees for a student");
		System.out.println("4. Exit");
		System.out.println("----------------------------------------- \n");
		System.out.println("Enter Your Choice");
		
		// Different options
		int ans = scan.nextInt();
		switch(ans){
		
		case 1:
			System.out.print("Enter the name of the student: ");
			scan.nextLine();
			String name = scan.nextLine();
			
			System.out.print("Enter the Id of the student: ");
			int id = scan.nextInt();
			
			System.out.print("Enter the number of credits taken: ");
			int credits = scan.nextInt();
			
			Student std = new Student(id, name, credits);
			stdc.add(std);
			System.out.println("\nStudent Added");
			break;
		
		case 2:
			System.out.println("Enter the Id of the student you want to delete");
			int idd = scan.nextInt();
			int src = stdc.SearchId(idd);
			if(src<0)
				System.out.println("Student not found");
			
			else{
				String choice = "y";
				System.out.println("Are you sure you want to delete this student (y/n) \n" + stdc.Search(idd));
				choice = scan.next();
				if(choice.equalsIgnoreCase("y")){
				stdc.delete(idd);
				System.out.println("Student Deleted");
				System.out.println();
				}
				}
			break;
			
		case 3:
			int fee;
			System.out.println("Enter the Id of the student you want to calculate the fee for");
			int ids = scan.nextInt();
			
			//Calculating fees
            int credit = stdc.Search(ids).getCredits();
			fee = credit *400;
			System.out.println("\nStudent Info: \n" + stdc.Search(ids)+ "\n\nThe fee of this student is " + fee);
			break;
			
		case 4:
			System.out.println("Thank you for using this program");
			System.exit(0);
			break;
			
		default:
			System.out.println("Wrong Choice Number");
			}
		
		System.out.println();
		System.out.print("Do you want to rerun the program (y/n): ");
		another = scan.next();
		}
		
		while(another.equalsIgnoreCase("y"));
	}
}
