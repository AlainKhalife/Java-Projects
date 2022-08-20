package hw5q2;

public class Person {
	
	private ArrayQueue transactions_queue; // Each person has a queue of transactions
	private String name; // name of the person
	private int client_number; // his number
	private LinkedList accounts; // His list of accounts
	
	// Constructor
	public Person(String name, int client_number, LinkedList account) {
		this.name = name;
		this.client_number = client_number;
		accounts = account;
		transactions_queue = new ArrayQueue();
	}
	
	// Below are getters and setters for all the fields

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClient_number() {
		return client_number;
	}

	public void setClient_number(int client_number) {
		this.client_number = client_number;
	}

	public LinkedList getAccounts() {
		return accounts;
	}
	
	public void addTransactionToQueue(Transaction t) {
		transactions_queue.enqueue(t);
	}
	
	public void removeTrasactionFromQueue() {
		transactions_queue.dequeue();
	}
	
	public void listAllQueue() {
		// This method prints the transactions that are in queue for the person
		transactions_queue.listAll();
	}
	
	public String listAllAccounts() {
		// This method prints the accounts following the file format of the file transaction
		String s = "";
		for(int i = 0; i<accounts.size(); i++) {
			s += accounts.get(i).getAccount_number() + ", " + accounts.get(i).getType() + ", " + accounts.get(i).getBalance() + "/ ";
		}
		return s;
	}

}
