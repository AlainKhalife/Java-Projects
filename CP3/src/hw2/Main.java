package hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	/*
	 	Hi again Manuella :)
	 	Name: Alain Khalife
	 	ID: 201807278
	 	
	 	All the running time of all the methods below have been explained in class Heap.
	 	Thank you so much for all your help
	 */
	
	private Heap print_jobs = new Heap();
	
	private void readFile() {
		/*
		 Proper File Format is the name followed by a coma and a space (, ) then the length 
		 */
		try {
			FileReader fr = new FileReader("./src/hw2/owners.txt"); // Change this if you want to change the file path. But make sure to have the proper format
			BufferedReader br  = new BufferedReader(fr);
			String s = null;
			while((s=br.readLine())!=null) {
				try {
				String[] owners = s.split(", ");
				String name = owners[0];
				int length = Integer.parseInt(owners[1]);
				if(length<=0) // Making sure the length of job is positive
					continue;
				PrintJob job = new PrintJob(name, length);
				print_jobs.insert(job);
			}
				// Catching all possible errors that might occur
				catch(InputMismatchException e) {
					continue;
				}
				catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
				catch(NumberFormatException e) {
					continue;
				}
			}
			br.close();
			fr.close();
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("\nFile not found to read from\n");
		}
		catch(IOException e) {
			System.out.println("\nFile not found to read from\n");
		}
	}
	
	private void insert() {
		// Inserting jobs to my queue
		Scanner scan = new Scanner(System.in);
		String name;
		int length;
		System.out.print("Please enter your name: ");
		name = scan.next();
		try {
		System.out.print("\nEnter the length of the print job: ");
		length = scan.nextInt();
		while(length<=0) {
			System.out.print("\nError: Input must be a positive digit\nEnter again: ");
			length = scan.nextInt();
		}
		PrintJob job = new PrintJob(name, length);
		print_jobs.insert(job);
		System.out.println("\nJob inserted\n");
		}
		catch(InputMismatchException e) {
			System.out.println("Input must be a positive digit only\n");
		}
		
	}
	
	private void delete() {
		Scanner scan = new Scanner(System.in);
		String name;
		System.out.print("Enter the name of the owner: ");
		name = scan.next();
		
		boolean check = print_jobs.delete(name);
		if(check)
			System.out.println("Job deleted succesfully\n");
		
		else
			System.out.println("Owner not found, no job deleted\n");
	}
	
	private void showI() {
		// I do not want to tamper with my original heap since its storing the jobs from highest to lowest priority
		// Reordering the main job heap itself will change their order since higher length = lower priority.
		// So I used the heap temp and stored in it the array of the primary print_jobs and printed it in order. In order not to affect the original heap order
		
		System.out.println("\nBelow are the list of jobs in there increasing order\nBased on their length: (from lowest to highest length)\n");
		Heap temp = new Heap();
		for(int i=1; i<print_jobs.getsize(); i++) {
			// Copying elements to a temp heap object so I do not temper with my original Queue
			// --> O(n)
			temp.insert(print_jobs.getJobs()[i]);
		}
		temp.showI(temp); // O(n.log n)
	}
	
	private void showD() {
		// Same as ShowI() but in reverse
		System.out.println("\nBelow are the list of jobs in there decreasing order\nBased on their length: (from highest to lowest length)\n");
		Heap temp = new Heap();
		// I do want to tamper with my original heap. So I will do an in-place heapsort on my temp heap
		for(int i=1; i<print_jobs.getsize(); i++) {
			// Copying elements to a temp heap object so I do not temper with my original Queue
			// --> O(n)
			temp.insert(print_jobs.getJobs()[i]);
		}
		temp.showD(temp); // O(n.log n)
	}
	
	private void prioritize() {
		// This method will prioritize a job
		Scanner scan = new Scanner(System.in);
		String owner;
		System.out.print("\nEnter the name of the owner: ");
		owner = scan.next();
		
		boolean check = print_jobs.prioritize(owner);
		if(check)
			System.out.println("\nOwner Print job set to max priority\n");
		
		else
			System.out.println("\nOwner not found\n");
	}
	
	
	
	private void menu() {
		// The menu
		int choice=0;
		Scanner scan = new Scanner(System.in);
		while(choice!=6) {
			try {
			System.out.println("Enter your choice:");
			System.out.print("1. Insert\n2. Delete\n3. Prioritize\n4. ShowD\n5. ShowI\n6. Exit\n\nChoice: ");
			choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				insert();
				break;
				
			case 2:
				delete();
				break;
				
			case 3:
				prioritize();
				break;
				
			case 4:
				showD();
				break;
				
			case 5:
				showI();
				break;
				
			case 6:
				System.out.println("\nExiting...\n");
				break;
				
				default:
					System.out.println("\nWrong input choice\n");
					break;
			}
		 }
			catch(InputMismatchException e) {
				scan.next();
				System.out.println("\nWrong input\n");
			}
		}
		
	}
	
	// ------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		// Method main
		Main main = new Main();
		main.readFile(); // Read File First
		main.menu();
	}

}
