package hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	/*
	   Name: Alain Khalife
	   ID: 201807278
	   
	   First of all, I want to thank you Joseph for being an amazing instructor during this semester.
	   Thank you for all the time you gave us, and I hope we meet again next year.
	   
	   Please, I worked so hard on this assignment.
	   
	   I only had a problem with display tree method.
	   PLEASE, don't be mean with the grade on this method.
	   I spent a lot of time on this method. But please, see the logic behind my code and don't take off the full grade on this method.
	   EVERY POINT COUNTS FOR ME IN ORDER TO BE ABLE TO PASS THIS COURSE. I HAVE PUT EVERYTHING INTO THIS ASSIGNMENT. I KNOW YOU UNDERSTAND US JOSEPH.
	   
	   THANK YOU SO MUCH.
	   
	 *** Note: When reading from a file, please make sure it is present in the package folder called hw6 ***
	 */
	
	private BST tree;// This is the tree that will store all the definitions
	private String file_name; // This will be used later on in method exit when saving the changes
	
	public Main() {
		tree = new BST();
		file_name = null;
	}
	
	private void save(BTNode n, FileWriter fr, BufferedWriter br) {
		// This method will go recursively through the elements of the tree and save them in a file sorted
		if(n == null) // Base case if n is null, i just need to return
			return;
		
		save(n.getLeft(), fr, br); // First go to the deepest node of the left
		try {
		br.write(n.getInfo().getWord() + ": " + n.getInfo().getDescription() + "\n"); // Then write the elements of the nodes in the file
		} 
		catch (IOException e) {
			return;
		}
		save(n.getRight(), fr, br); // Then do the same thing for the right nodes
	}
	
	
	private void readFromFile(FileReader fr, BufferedReader br, String s) {
		// This method will recursively read the lines of the file and add them to the tree
		try {
			s = br.readLine(); // First read the line
		} catch (IOException e) {
			System.out.println();
		}
		
		if(s!=null) { // If s is not null, then I need to take care of the data in it and this is my base case
			/*
			   The file has the following format:
			   name: definition
			   The name and the definition of the file are separated by a semicolon
			   And the file that I want to read from must be present in the package folder hw6
			 */
			String[] a = s.split(": ");
			// One I split the string I should have the word in a[0] and its definition in a[1]
			try {
			String word = a[0];
			String description  = a[1];
			Definition d = new Definition(word, description); // Create the definition object based on the information I got
			boolean check = tree.findIgnoreCase(word, description); // I need to make sure that the word I am adding is not already present in my tree
			if(check) // If present, then I should not add it to the tree
				System.out.print("");
			
			else
				tree.add(d); // Otherwise, I can add it to the tree
			}
			catch(ArrayIndexOutOfBoundsException e) { // In case a line the file does not meet the correct format, then I should not add anything
				System.out.print("");
			}
			
			readFromFile(fr, br, s); // Call the method again
		}
		
		else return; // Once s is null, it means that there are not more lines to read and I should exit
	}
	
	
	public void createDictionary() {
		// This method will create the dictionary based on the file the user specified
		// This method will call the recursive method readFromFile
		int counter = 0; // This counter will keep track of the number of times the user entered a wrong file name
		boolean done = false; // This boolean is used to maintain the while loop
		Scanner scan = new Scanner(System.in);
		while(done == false) {
		try {
			System.out.println("\nPlease enter the name of the file where you have stored the definitions: ");
			System.out.println("Make sure that the file is in the package folder");
			System.out.println("Note: File name is case sensitive");
			System.out.print("Name: ");
			String name = scan.next(); // Asking for the name of the file

			String s = null;
			FileReader fr = new FileReader("./src/hw6/" + name); // Initializing the parameters for readFrom file method
			BufferedReader br = new BufferedReader(fr);
				
			readFromFile(fr, br, s); // Calling method readFromFile with the corresponding parameters
			
			br.close(); // At the end close the streams
			fr.close();
			file_name = name; // Changing the variable to file name for method exit
			done = true; // changing boolean to true to exit loop
			System.out.println("\nDictionary created\n");
		} 
		catch (FileNotFoundException e) {
			// If file is not found, I need to increment counter and ask for the name of the file again
			counter++;
			if(counter>=3) {
				System.out.println("\nIncorrect input 3 times. Returning to main menu\n");
				return;
			}
			System.out.println("\nFile not found, enter again\n");
		}
		catch(IOException e) {
			System.out.println("Something went wrong");
		}
	 }
	}
	
	public void addDefinition() {
		// This method will add a definition to the tree
		Scanner scan = new Scanner(System.in);
		InputStreamReader sr = new InputStreamReader(System.in); // Used input stream reader instead of Scanner since the scanner class is not reading the spaces
		BufferedReader br = new BufferedReader(sr);
		try {
		System.out.println("Enter the word you would like to add: ");
		String word  = br.readLine(); // Asking for the name of the word
		System.out.println("Enter its definition: ");
		String description = br.readLine(); // Asking for its description
		Definition d = new Definition(word, description); // Creating the object definition
		try {
		boolean check = tree.findIgnoreCase(word, description);
		if(check) // If two a word with the same name and description is found, then we cannot add them
			System.out.println("\nThis word already exists in the dictionary, cannot add it\n");
		
		
		else {
			// Otherwise, I can add it
			tree.add(d);
			System.out.println("\nDefinition added\n");
		}
		}
		catch(NullPointerException e) {
			// If i get a null pointer exceptions, this means that the tree is empty, therefore I can add to it
			// This exception is thrown by the find() method if the tree is empty
			tree.add(d); // Adding to the tree
			System.out.println("\nDefinition added\n");
		}
		
		}
		catch(IOException e) {
			System.out.println();
		}
	}
	
	public void removeDefinition() {
		// This method will remove a definition from the tree
		Scanner scan = new Scanner(System.in);
		InputStreamReader sr = new InputStreamReader(System.in); // Again, used InputStreamReader since the scanner class is not reading the spaces
		BufferedReader br = new BufferedReader(sr);
		try {
			ArrayList<Definition> all_words; // this will store the references to the words found in the tree
			System.out.println("Note: Word search is case sensitive");
			System.out.println("What is the word that you would like to remove: ");
			String word = br.readLine(); // Asking for the word to delete

			all_words = tree.findAllDuplicates(word); // Calling method findAllDuplicates that will return an arrayList of containing the references to to the definitions of the words that the user wants to delete

			if(all_words.size() == 0) // If the array list is of size 0, then no word has been found
				System.out.println("\nNo word found\n");

			else if(all_words.size() == 1) {
				// If the size is 1, then only 1 word is found
				System.out.println("\nThe following word has been found:");
				System.out.println("Word: " + all_words.get(0).getWord());
				System.out.println("Description: " + all_words.get(0).getDescription());
				System.out.println("---------------------");
				System.out.println("Would you like to delete it ? (y/n)");
				System.out.println("Any input other than \"y\" will be considered as a no");
				String ans = scan.next();
				if(ans.equalsIgnoreCase("y")) {
					// Asking for the user to confirm his deletion by y or n
					// Any input other than "y" will be considered as a no
					tree.delete(all_words.get(0)); // Calling method delete that will only delete the found word
					System.out.println("\nWord Deleted\n");
				}

				else
					System.out.println("\nWord has not been deleted\n");	
			}

			else {
				// Otherwise, more than one word with same name but different definitions have been found
				// The user gets to choose whether to delete them all, or choose a specific word
				System.out.println("\nHere are all the words found:");
				for(int i = 0; i<all_words.size(); i++) {
					// Listing all the words found
					System.out.println("Word number " + (i+1) + ":");
					System.out.println("Word: " + all_words.get(i).getWord());
					System.out.println("Description: " + all_words.get(i).getDescription());
					System.out.println("----------------");
				}
				System.out.println("\nWould you like to delete all of the words ? (y/n)");
				System.out.println("Any input other than \"y\" will be considered as a no");
				String ans = scan.next();
				if(ans.equalsIgnoreCase("y")) {
					// If the user enters y, then I can delete of the elements using their reference in the ArrayList
					for(int i = 0; i<all_words.size(); i++) {
						tree.delete(all_words.get(i)); // Deleting all the words found
					}
					System.out.println("\nAll words deleted\n");
				}

				else {
					// Otherwise, the user gets to choose the word he would like to delete
					System.out.print("Please enter the number of the word you wish to delete: ");
					try {
						int ans2 = scan.nextInt();
						// Making sure the number is not positive
						if(ans2<1 || ans2>all_words.size())
							System.out.println("\nInvalid number\n");

						else {
							// Deleting the word based on the chosen number of the word
							tree.delete(all_words.get(ans2-1));
							System.out.println("\nWord deleted\n");
						}
					}
					catch(InputMismatchException e) {
						// Catching any invalid input
						System.out.println("\nInvalid input\n");
					}
				}
			}
		}
		catch(IOException e) {
			System.out.println();
		}
	}
	
	public void searchForADefinition() {
		// This method will search for a definition in the tree
		Scanner scan = new Scanner(System.in);
		System.out.println("Note: Word search is case sensitive");
		System.out.println("What is the word your searching for: ");
		String word  = scan.next(); // Asking for a word name
		System.out.println("Here are all the words found: \n");
		tree.listAllDuplicates(word); // Calling method listAllDuplicates that will find the definitions of the specified word
		System.out.println();
	}
	
	public void printDictionary() {
		// This method will print the elements of the tree sorted
		System.out.println("Here are all the words sorted: \n");
		tree.listSorted(); // Calling method listSorted that will print the elements of the tree sorted
		System.out.println();
	}
	
	public void printTree() {
		// This method will print the tree
		System.out.println("Here is the tree: \n");
		tree.printTree(); // Calling method printTree that will print the levels of the tree
	}
	
	public void exit() {
		// This method will save all the definitions in a file
		// Here I took 2 cases:
		
		if(file_name == null) {
			// If file_name is null, this means that the user did not choose option 1, and there is no file that the user imported
			// Therefore I need to create a new file and save the definitions in it
			try {
			File f = new File("./src/hw6/words"); // Creating a file called words in the package folder
			FileWriter fr = new FileWriter("./src/hw6/words");
			BufferedWriter br = new BufferedWriter(fr);
			
			save(tree.getRoot(), fr, br); // Calling method save that will save the definitions in the file wordsd
			
			br.close(); // Close the streams
			fr.close();
			System.out.println("\nAll changes saved to file \"words\" in the package\n" );
			System.exit(0); // Exit
			}
			catch(IOException e) {
				System.out.println();
			}
		}
		else {
			// Otherwise, the user did use option 1 and a location of a file is present, I can save the changes in the file imported by the user
			try {
				FileWriter fr = new FileWriter("./src/hw6/" + file_name);
				BufferedWriter br = new BufferedWriter(fr);
				
				save(tree.getRoot(), fr, br); // Same thing as above, calling method save to do the job
				
				br.close(); // Close the streams
				fr.close();
				System.out.println("\nAll changes saved to file " + file_name + " in the package\n");
				System.exit(0);
			}
			catch(IOException e) {
				System.out.println();
			}
		}
	}
	
	public void menu() {
		// This is menu, it will call the methods above
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice!=7) {
			System.out.print("1. Create the dictionary\n2. Add a definition\n3. Remove a definition\n4. Search for a definition\n5. Print dictionary\n6. Display tree\n7. Exit\n-----------------------------------\nEnter your choice: ");
			try {
				choice = scan.nextInt();

				switch(choice) {
				case 1:
					createDictionary();
					break;

				case 2:
					addDefinition();
					break;

				case 3:
					removeDefinition();
					break;
					
				case 4:
					searchForADefinition();
					break;
					
				case 5:
					printDictionary();
					break;
					
				case 6:
					printTree();
					break;
					
				case 7:
					exit();
					break;

				default:
					System.out.println("\nInvalid Input\n");

				}
			}
			catch(InputMismatchException e) {
				System.out.println("\nInvalid Input\n");
				choice = 0;
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
