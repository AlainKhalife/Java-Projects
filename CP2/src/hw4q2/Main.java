package hw4q2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	private DLLHeader list;
	
	public Main() {
		list = new DLLHeader();
	}
	
	public void menu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice != 7) {
			try {
			System.out.println("1. Make list\n2. Insert\n3. Delete\n4. Reverse\n5. Add\n6. Split\n7. Exit");
			System.out.println("--------------------------------------");
			System.out.print("Input your choice: ");
			choice = scan.nextInt();
			
			// Choice 7 will terminate the program even if no list has been created. Because this has nothing to do with the other options
			if(choice == 7) {
				System.out.println("\nEnding program ...\n");
				System.exit(0);
			}
			
			// Making sure that the user chooses option 1 first to create a list before proceeding to the other options
			else if(choice!=1 && list.size()==0) {
				System.out.println("\nBefore you proceed, you need to add some elements to the list. Select option 1 first\n");
			}
				
			
			else {
			
			switch(choice) {
			
			case 1:
				list = new DLLHeader(); // Each time the user inputs 1, a new list must be created
				int count = 1; // this is just show the element number
				System.out.println("\nYou are about to create a list that contains integers only\nTo stop enter any letter");
				while(count>0) {
					try {
					System.out.print("Enter element number " + count + ": ");
					list.add(scan.nextInt());
					count++;
					}
					catch(InputMismatchException e) {
						scan.next();
						break;
					}
				}
				
				if(list.size() == 0)
					System.out.println("\nYou didn't add any number to the list\n");
				
				else {
				System.out.println("\nNumbers have been added to the list");
				System.out.println("Here is the list: ");
				list.display();
				System.out.println("\n");
				}
				break;
				
			case 2:
				System.out.print("Enter the number you would like to add: ");
				int i = scan.nextInt();
				System.out.print("Enter the index at which you would like to insert: ");
				int index = scan.nextInt();
				boolean check = list.insert(i, index);
				
				if(check) {
					System.out.println("\nNumber added to the list\n");
					System.out.println("Here is the list:");
					list.display();
					System.out.println("\n");
				}
				else
					System.out.println("\nYour index is out of bound of the list\n");
				break;
				
			case 3:
				System.out.print("At what index whould you like to delete the element: ");
				int ind = scan.nextInt();
				boolean check2 = list.delete(ind);
				if(check2) {
					System.out.println("Number deleted");
					System.out.println("Here is the new list: ");
					list.display();
					System.out.println("\n");
				}
				
				else
					System.out.println("\nCould not delete the element, make sure of the index\n");
				break;
				
			case 4:
				list.reverse();
				System.out.println("All the elements in the list have been reversed");
				System.out.println("Here is the new list: ");
				list.display();
				System.out.println("\n");
				break;
				
			case 5:
				System.out.println("\nThe sum of all the integers in the list is: " + list.sum() + "\n");
				break;
				
			case 6:
				System.out.print("\nAt which index would you like to split the list: ");
				int indexx = scan.nextInt();
				
				if(indexx<0 || indexx>list.size())
					System.out.println("\nIndex is out of bound, cannot split\n");
				
				else {
				DLLHeader[] array = list.split(indexx);
				System.out.println("The list has been split into l1 and l2");
				System.out.print("L1: ");
				array[0].display();
				System.out.print("\nL2: ");
				array[1].display();
				System.out.println("\n");
				}
				break;
				
			case 7:
				System.out.println("\nEnded Program ...");
				System.exit(0);
				
				default:
					System.out.println("\nInvalid option, try again\n");
			}
		}
	  }
			catch(InputMismatchException e) {
				System.out.println("\nWrong input, try again\n");
				scan.next();
			}
		}
	}
	
	// Method main
	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}
	
	

}
