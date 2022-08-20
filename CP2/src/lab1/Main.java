package lab1;
import java.util.*;
public class Main {

	public void menu() {
		Scanner scan = new Scanner(System.in);
		Country current_country = new Country("Lebanon");
		int choice =0;

		while(choice != 4) {
			System.out.println("Select:");
			System.out.println("1. to create a person");
			System.out.println("2. to kill a person");
			System.out.println("3. to get the population of earth");
			System.out.println("4. to exit");

			try {
				choice = scan.nextInt();
				switch(choice) {
				case 1:
					current_country.createPerson();
					break;
				case 2:
					current_country.kill();
					break;
				case 3:
					current_country.print();
					break;
				case 4:
					System.out.println("system will exit");
					break;
				default:
					System.out.println("you entered an invalid value");
				}

			}
			catch(InputMismatchException e) {
				System.out.println("you entered an invalid value");
				choice=0;
				scan.next();
			}
		}
	}
	public static void main(String[] args) {
		Main m= new Main();
		m.menu();
	}
}
