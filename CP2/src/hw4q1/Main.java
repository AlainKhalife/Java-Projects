package hw4q1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private LinkedList initial_list;
	
	
    // Constructor
	public Main() {
		initial_list = new LinkedList();
	}

	// The menu method
	public void menu() {
		Scanner scan = new Scanner(System.in);
		boolean check = false;;
		while(check == false) {
			System.out.println("To start, you need to create an initial list\n");
			System.out.print("What is the size of this list: ");

			boolean check2 = false;		
			while(check2 == false) {
				int size;
				try {
					size = scan.nextInt();
					// making sure value is positive
					while(size<=0) {
						System.out.print("\nValue must be positive, Enter again: ");
						size = scan.nextInt();
					}

					for(int i = 0; i<size; i++) {
							System.out.print("Enter element at position " + i + " : ");
							String element = scan.next();
							initial_list.addString(element);
						}

					check2 = true;

				}
				catch(InputMismatchException e) {
					scan.next();
					System.out.print("Wrong size value, enter again: ");
				}
			}

			System.out.println("\nAll elements have been added\nThe linkedList initial_list has been created\nBelow is the menu\n");
			check = true; // Change the boolean value to true to exit the loop and proceed to the menu;
		}


		// Start of menu
		int choice = 0;
		while(choice!=6) {
			System.out.println("1. Includes\n2. Remove Duplicates\n3. Reverse\n4. Return Odd\n5. Make Doubly linked List\n6. Exit");
			System.out.println("-------------------------------------------------------");
			System.out.print("Input your choice: ");
			try {
				choice = scan.nextInt();

				switch(choice) {

				case 1:
					LinkedList l1 = new LinkedList();
					System.out.println("\nEnter the elements of a list l1 to compare it with initial list");
					System.out.println("When you are finished, enter done");

						int counter = 1; // This counter is to keep track of how many elements the user is putting in l1
						String s = "";
						boolean done = false;
						while(!s.equalsIgnoreCase("done")) {
							System.out.print("Enter element number " + counter + ": ");
							s = scan.next();

							if(s.equalsIgnoreCase("done")) { // If the user enters done that he doesn't want to add anymore elements to compare
								done = true;
								break;
							}

							l1.addString(s);
							counter++;
						}

						// Comparing the two lists l1 and initial list
						/*
						 * What I am doing to find if the elements in l1 are included in initial list is the following:
						 * I am looping through each element of l1 and comparing it with the elements of initial list.
						 * If I find an element that matches the element in initial list, I increment the variable how_many_found
						 * If how_many_found is equal to the number of elements of in l1, this means that all the elements in l1 have been found in initial list
						 */
						int how_many_found = 0;
						for(int j =0; j<l1.size(); j++) {
							String ans = (String) l1.get(j);
							for(int i =0; i<initial_list.size(); i++) {
								
								// Checking if the element initial_list is equal to the one in l1 
								if(((String)initial_list.get(i)).equals(ans)) {
									how_many_found++;
									break; // If found then i don't need to check anymore, I break and go to the next element in l1
								}
								
								else {
									try {
										/* What I am doing here is checking if the input of the user can be parsed into a double
										 * Suppose in my initial list I have the following [Monkey, Paris, 2.0, 36.2]
										 * If in l1 the user entered as parts of the elements 2 and not 2.0
										 * 2 == 2.0, but because I am comparing strings, this will not count as being equal
										 * So I am checking if the elements in l1 can be parsed into a double.
										 * If yes then I will increment the number of elements found and go to the next element, no need to check for the others
										 * If no , then an exception will be thrown which is NumberFormatException or ClassCastException that I will catch, ignore and continue to the next element
										 * No need to do this for the integers because they will be treated as a string
										*/
										if(Double.parseDouble(ans) == Double.parseDouble((String) initial_list.get(i))) {
											how_many_found++;
											break; // If found then I don't need to check anymore, I break and go to the next element in l1
										}
									}
									catch(NumberFormatException e) {
										continue;
									}
									catch(ClassCastException e) {
										continue;
									}
								}
							}	 
						}

						if(how_many_found == l1.size())
							System.out.println("\nTrue, All elements exist in the list\n");

						else
							System.out.println("\nFalse, not all elements exist in the list\n");

					break;
					
				case 2:
					LinkedList l2 = new LinkedList();
					System.out.print("What is the size of this list: ");
					try {
					int ssize = scan.nextInt();
						for(int i = 1; i<=ssize; i++) {
							System.out.print("Enter element number " + i + ": ");
							String s1 = scan.next();

							l2.addString(s1);
						}
					}
					catch(InputMismatchException e) {
						scan.next();
						System.out.println("\nWrong input\n");
						break;
					}
						// Calling method removeDup which will remove the duplicates from the list and print the new one
						l2.removeDup();
					
					break; // Case 2 end
					
				case 3:
					System.out.println("\nHere is initial list in reverse order:");
					System.out.println("Note that the original order of initial list has not been modified");
					System.out.println("The list in reverse order is:");
					initial_list.printReverse();  // calling method printReverse that will print all the elements of the list in reverse order 
					System.out.println("\n");
					break;
					
				case 4:
					System.out.println("\nThe elements at odd indices in initial list are the following: ");
					initial_list.returnOdd(initial_list);
					System.out.println("\n");
					break;
					
				case 5:
					DoublyLinkedList dlist = new DoublyLinkedList();
					for(int i = 0; i<initial_list.size(); i++) {
						dlist.add(initial_list.get(i));
					}
					System.out.println("\nAll the elements of initial list have been placed in a doubly linked list\n");
					System.out.println("Here are all the elements: ");
					dlist.displayAll();
					System.out.println("\n");
					break;
					
				case 6:
					System.out.println("\nEnded program...\n");
					System.exit(0);
					
					default:
						System.out.println("\nInvalid choice, Terminating the program\n");
						System.exit(0);
					
				}

			}


			catch(InputMismatchException e) {
				scan.next();
				System.out.println("\nWrong input, Terminating the program ...\n");
				System.exit(0);
			}
			catch(NullPointerException e) {
				System.out.println("\nAt least add an element\n");
			}
		}

	}


	// Method Main
	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}

}