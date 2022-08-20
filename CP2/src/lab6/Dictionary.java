package lab6;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Dictionary {
	WordLL words;
	DefinitionLL definitions;
	public Dictionary() {
		words=new WordLL();
		definitions=new DefinitionLL();
	}

	public void menu() {
		Scanner scan = new Scanner(System.in);
		int choice=0;
		while(choice != 9) {
			System.out.println("Enter 1. to add");
			System.out.println("Enter 2. to insert at particular index");
			System.out.println("Enter 3. to sort");
			System.out.println("Enter 4. to lookup");
			System.out.println("Enter 5. to delete word");
			System.out.println("Enter 6. to add definition");
			System.out.println("Enter 7. to delete definition");
			System.out.println("Enter 8. to display");
			System.out.println("Enter 9. to exit");

			try {
				choice=scan.nextInt();
			}catch(InputMismatchException e) {
				choice =0;
				scan.nextLine();
				System.out.println("Invalid input");
				continue;
			}
			switch (choice) {
			case 1:
				add();
				break;
			case 2:
				insert();
				break;
			case 3:
				sort();
				break;
			case 4:
				lookup();
				break;
			case 5:
				deleteWord();
				break;
			case 6:
				addDefinition();
				break;
			case 7:
				deleteDefinition();
				break;
			case 8:
				display();
				break;
			case 9:
				System.out.println("System will exit");
				break;
			default:
				System.out.println("Invaid input");
			}
		}
	}
	public void add() {
		Scanner scan_add= new Scanner(System.in);
		System.out.println("Enter the word you would like to add");
		String word_to_add=scan_add.nextLine();
		Vector <String> definition_to_add= new Vector<>();
		String choice="y";
		while(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
			System.out.println("Enter the definition of "+word_to_add);
			definition_to_add.add(scan_add.nextLine());
			System.out.println("Would you like to add a new definition?");
			choice=scan_add.nextLine();
		}
		words.insert(new WordNode(word_to_add), 0);
		definitions.insert(new DefinitionNode(definition_to_add), 0);

	}
	public void insert() {
		int loc=0;
		Scanner scan_add= new Scanner(System.in);
		System.out.println("Enter the word you would like to add");
		String word_to_add=scan_add.nextLine();
		Vector <String> definition_to_add= new Vector<>();
		String choice="y";
		while(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
			System.out.println("ENter the definition of "+word_to_add);
			definition_to_add.add(scan_add.nextLine());
			System.out.println("Would you like to add a new definition?");
			choice=scan_add.nextLine();
		}
		System.out.println("Enter the location where you want to insert "+word_to_add);
		try {
			loc=scan_add.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid input, adding at 0");
			scan_add.nextLine();
			loc=0;
		}
		words.insert(new WordNode(word_to_add), loc);
		definitions.insert(new DefinitionNode(definition_to_add), loc);
	}
	public void sort() {
		WordLL temp_words = new WordLL();
		DefinitionLL temp_definitions = new DefinitionLL();

		while(words.getHeader().getNext()!=null) {
			WordNode max = words.getHeader().getNext();
			WordNode current=words.getHeader().getNext().getNext();
			while(current!=null) {
				if(current.getInfo().compareTo(max.getInfo())>0) {
					max=current;
				}
				current=current.getNext();
			}
			temp_words.insert(new WordNode(max.getInfo()), 0);
			temp_definitions.insert(new DefinitionNode(definitions.delete(words.delete(max.getInfo()))), 0);

		}
		words=temp_words;
		definitions=temp_definitions;
	}

	public void lookup() {
		Scanner scan_lookup = new Scanner(System.in);
		System.out.println("Enter the word you are looking for");
		String search = scan_lookup.nextLine();
		WordNode w = words.getHeader().getNext();
		DefinitionNode d = definitions.getHeader().getNext();
		while(w!=null) {
			if(w.getInfo().equalsIgnoreCase(search)) {
				System.out.println(w);
				System.out.println(d);
				return;
			}
			w=w.getNext();
			d=d.getNext();
		}
		System.out.println("Word not found");
	}
	
	public void deleteWord() {
		System.out.println("Enter the word to delete");
		Scanner scan_delete = new Scanner(System.in);
		String to_delete=scan_delete.nextLine();
		if(definitions.delete(words.delete(to_delete))==null)
			System.out.println("Word not found");
		else
			System.out.println("deleted");
	}

	public void addDefinition() {
		Scanner scan_definition = new Scanner(System.in);
		System.out.println("Enter the word you want to add a definition to");
		String word_to_search = scan_definition.nextLine();
		if(words.getHeader().getNext()==null) {
			System.out.println("Dictionary is empty");
			return;
		}
		boolean found = false;
		WordNode iterator=words.getHeader().getNext();
		DefinitionNode d_iterator=definitions.getHeader().getNext();
		while(iterator != null && !found) {
			if(iterator.getInfo().equals(word_to_search)) {
				found = true;
				break;
			}
			d_iterator=d_iterator.getNext();
			iterator=iterator.getNext();
		}
		if(!found) {
			System.out.println("word not found");
			return;
		}
		System.out.println(iterator);
		System.out.println(d_iterator);
		System.out.println("Enter the definition you would like to add");
		String definition_to_add= scan_definition.nextLine();
		d_iterator.addDefinition(definition_to_add);
	}


	public void deleteDefinition() {

		if(words.getHeader().getNext() == null) {
			System.out.println("disctionary empty");
			return;
		}
		Scanner scan_remove = new Scanner(System.in);
		System.out.println("Enter the world you want to remove a definition from");
		String word_to_search = scan_remove.nextLine();

		boolean found = false;
		WordNode iterator=words.getHeader().getNext();
		DefinitionNode d_iterator=definitions.getHeader().getNext();
		while(iterator != null && !found) {
			if(iterator.getInfo().equals(word_to_search)) {
				found = true;
				break;
			}
			d_iterator=d_iterator.getNext();
			iterator=iterator.getNext();
		}
		if(!found) {
			System.out.println("word not found");
			return;
		}	
		System.out.println(iterator);
		System.out.println(d_iterator);
		System.out.println("Enter the definition you would like to delete");
		String definition_to_remove= scan_remove.nextLine();

		if(d_iterator.removeDefinition(definition_to_remove))
			System.out.println("definition removed");
		else System.out.println("definition not removed");
	}
	public void display() {
		if(words.getHeader().getNext() == null) {
			System.out.println("Dictionary empty");
			return;
		}
		Scanner scan_display=new Scanner(System.in);
		System.out.println("Enter the starting character of the range");
		String start=scan_display.nextLine();
		System.out.println("Enter the limiting character of the range");
		String end=scan_display.nextLine();
		if(start.length()>1 || end.length()>1) {
			System.out.println("invalid input");
			return;
		}
		WordNode word_iterator =  words.getHeader().getNext();
		DefinitionNode definition_iterator=definitions.getHeader().getNext();
		while(word_iterator!=null ) {
			if(word_iterator.getInfo().charAt(0)>=start.charAt(0) && word_iterator.getInfo().charAt(0)<= end.charAt(0)) {
			System.out.print(word_iterator);
			System.out.println(definition_iterator);
			System.out.println("-------------------------------------------------");}
			word_iterator=word_iterator.getNext();
			definition_iterator=definition_iterator.getNext();
		}
	}
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.menu();
	}
}
