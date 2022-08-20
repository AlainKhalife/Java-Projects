package hw3q3;

/* This is a Program that manages a Store.
 * The Store can store 3 types of products: Books, Games and Electronics.
 * An Interface called Product has been created and implemented in the class of each Product.
 * This way I can point to any object of type Product using this interface.
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {

	private ArrayList<Product> collection;
	private double profit;
	private int products; // This is used in order to keep track of the number of products I have in my store

	public Store() {
		// Constructor of the class Store
		collection = new ArrayList<Product>();
		profit = 0;
		products = 0;
	}

	public void menu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		try {
			while(choice!=6) {
				System.out.println("1. Add a Product");
				System.out.println("2. Sell a product");
				System.out.println("3. Show a category of products");
				System.out.println("4. Show all Products");
				System.out.println("5. Show profit");
				System.out.println("6. Exit");
				System.out.println("----------------------------------------------------------------");
				System.out.print("Choice: ");
				choice = scan.nextInt();

				switch(choice) {

				case 1:
					try {
						int pchoice;
						System.out.print("Enter the name of the product: ");
						String name = scan.next();
						System.out.print("Enter the price of the product: ");
						double price = scan.nextDouble();
						System.out.println("Choose the category of the product: ");
						System.out.println("1. Book\n2. Game\n3. Electronic");
						System.out.print("Choice: ");
						pchoice = scan.nextInt();

						switch(pchoice) {
						case 1:
							Book book = new Book(name, price);
							collection.add(book);
							products++;
							System.out.println("\nBook Added\n");
							break;

						case 2:
							Game game = new Game(name, price);
							collection.add(game);
							products++;
							System.out.println("\nGame Added\n");
							break;

						case 3:
							Electronic electronic = new Electronic(name, price);
							collection.add(electronic);
							products++;
							System.out.println("\nElectronic Added\n");
							break;

						default:
							System.out.println("\nInvalid Choice\n");
						}
					}
					catch(InputMismatchException e) {
						System.out.println("\nInvalid choice\n");
					}
					break;

				case 2:
					/* Case 2 asks the user of the name of the product that he wants to sell( Case sensitive )
					 * If the product is found, the user is asked to confirm his sell.
					 * If more than one product have exactly the same name, the information of each product will be prompted in front of the user
					 * and him to confirm the sell on each of these products.
					 */
					System.out.print("Enter the name of the product: ");
					String pname = scan.next();
					String conf = "z";
					for(int i = 0; i<collection.size(); i++) {
						if(collection.get(i).getName().equals(pname)) {
							System.out.println("\nIs this the product you want to sell ?");
							System.out.println(collection.get(i).toString());
							System.out.println("Choice (y or n): ");
							conf = scan.next();
							if(conf.equalsIgnoreCase("y")) {
								System.out.println("\nProduct sold\n");
								profit += collection.get(i).getPrice();
								break;
							}
						}
					}

					if(!conf.equalsIgnoreCase("y"))
						System.out.println("\nNo product has been sold\n");

					break;

				case 3:
					// This case displays a specific category of products in store
					System.out.println("\nSelect a category:\n1. Books\n2. Games\n3. Electronics");
					System.out.print("Choice: ");
					int cchoice = scan.nextInt();

					switch(cchoice) {

					case 1:
						// This case displays the books in store
						if(Book.getNbrOfBooks() == 0)
							System.out.println("\nNo Books exist\n");

						else {
							System.out.println("\nHere are all the Books:");
							for(int j = 0; j<collection.size(); j++) {
								if(collection.get(j).getType().equals("Book"))
									System.out.println(collection.get(j).toString());
							}
						}
						break;

					case 2:
						// This case displays the games in store
						if(Game.getNbrOfGames() == 0)
							System.out.println("\nNo Games exist\n");

						else {
							System.out.println("\nHere are all the Games:");
							for(int j = 0; j<collection.size(); j++) {
								if(collection.get(j).getType().equals("Game"))
									System.out.println(collection.get(j).toString());
							}
						}
						break;

					case 3:
						// This case displays the electronics in store
						if(Electronic.getNbrOfElectronics()==0)
							System.out.println("\nNo electronics exist\n");

						else {
							System.out.println("\nHere are all the electronics: ");
							for(int j = 0; j<collection.size(); j++) {
								if(collection.get(j).getType().equals("Electronic"))
									System.out.println(collection.get(j).toString());
							}
						}
						break;

					default:
						System.out.println("\nWrong choice\n");

					}
					break;

				case 4:
					// This Case Displays all the products in store
					if(products == 0)
						System.out.println("\nNo Products Exist\n");
					
					else {
					System.out.println("Here are all the products: ");
					for(int i = 0; i<collection.size(); i++)
						System.out.println(collection.get(i).toString());
					System.out.println();
					}
					break;

				case 5:
					System.out.println("\nYour profit is: " + profit + "$\n");
					break;

				case 6:
					System.out.println("\nEnded Program ...\n");
					System.exit(0);

				default:
					System.out.println("\nInvalid Choice, Enter again\n");

				}
			}
		}
		catch(InputMismatchException e) {
			System.out.println("\nInvalid Choice, try again\n");
			choice = 0;
			menu();
		}
	}
	
	// Method Main
	public static void main(String[] args) {
		Store s = new Store();
		s.menu();
	}
}
