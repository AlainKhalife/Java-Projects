package Exam;

import java.util.ArrayList;
import java.util.Scanner;

public class BookApplication {
	
	public static void main(String[] args){
		
		ArrayList<Books> collection = new ArrayList<Books>();
		Scanner scan = new Scanner(System.in);
		int choice;
		double libraryProfit = 0;
		String another = "y";
		
		do {
			
		// Prompting on the screen
		System.out.println("--------------------------------------------");
		System.out.println("Choose an option");
		System.out.println("1. Add Book");
		System.out.println("2. Sell Book");
		System.out.println("3. Delete Book");
		System.out.println("4. Show Books");
		System.out.println("5. Show Current Profit");
		System.out.println("6. Exit");
		System.out.println("--------------------------------------------");
		System.out.print("Your Choice: ");
		
		choice = scan.nextInt();
		
		// The Options
		
			switch(choice){
			
			case 1:
				System.out.print("Enter the name of the Book: ");
				scan.nextLine();
				String bbname = scan.nextLine();
				
				System.out.print("Enter the name of the author of the Book: ");
				String bbauthor = scan.nextLine();
				
				System.out.print("Enter the publish date of the Book: ");
				String bbpublish = scan.nextLine();
				
				System.out.print("Enter the price of the Book: ");
				double bbprice = scan.nextDouble();
				
				System.out.print("Enter the number of copies available of the Book: ");
				int bbavailable = scan.nextInt();
				
				Books books = new Books(bbname, bbauthor, bbpublish, bbprice, bbavailable);
				collection.add(books);
				
				
				System.out.println("\nBook Collection Added" );
				break;
				
			case 2:
				System.out.println("Enter the name of the Book you want to sell");
				String bookname = scan.next();
				
				// Checking For Availability
				boolean found2 = false;
				for(int i = 0; i<collection.size(); i++){
					if(bookname.equalsIgnoreCase(collection.get(i).getname())){
						found2 = true;
						int avcopy = collection.get(i).removecopy();
						if(avcopy>0){
						libraryProfit += collection.get(i).getprice();	
						System.out.println("The following book " + collection.get(i).getname() + " has been sold");
						System.out.println(collection.get(i));
						}
						
						else
							collection.remove(collection.get(i));
					}
				}	
				
	              if(found2 == false)
				  System.out.println("\nBook Not Found");
				
				break;
				
			case 3:
				System.out.println("Enter the name of the Book you want to delete");
				String bookname2 = scan.next();
				boolean found = false;
				for(int j = 0; j<collection.size(); j++){
					if(bookname2.equalsIgnoreCase(collection.get(j).getname())){
						found = true;
						System.out.println("The following book " + collection.get(j).getname() + " has been deleted");
						collection.remove(j);
					}
				}
				
				if(found == false)
				System.out.println("\nBook Not Found");
				
				break;
				
			case 4:
				if(collection.size()>0) {
				System.out.println("Here are the books:\n");
				
				for(int d = 0; d<collection.size(); d++){
					System.out.println(collection.get(d));
					System.out.println();
				}
				}
				
				else
					System.out.println("\nNo books available\n");
				break;
				
			case 5:
				System.out.println("The profit of this library is " + libraryProfit + " $");
				break;
				
			case 6:
				System.out.println("\nThank You");
				System.exit(0);
				
			default:
				System.out.println("Invalid Option");
			}
			
			System.out.print("Do you want to rerun the Program ? (y/n): ");
			another = scan.next();
			System.out.println();
		
		}
		
		while(another.equalsIgnoreCase("y"));
	}

}
