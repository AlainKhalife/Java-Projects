package hw5q2;

public class Transaction {
	
	private Person person; // The transaction has a person field
	private String date, time; // Has a date and Time
	private double amount; // Has an amount
	private String type; // Has a type
	private String account_number, account_to_transfer; // These are used to reference the accounts that are linked to the transaction
	
	public Transaction(Person p, String date, String time, double amount, String type, String accountNbr) {
		// This constructor is used for Debit and Credit transactions since they do not have an account to transfer to
		person = p;
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.type = type;
		account_number = accountNbr;
		account_to_transfer = "null"; // This is set to null since there is no account to transfer to
	}
	
	public Transaction(Person p, String date, String time, double amount, String type, String accountNbr, String account_number_transfer) {
		// This constructor is used for the transaction of type transfer, they need a reference to the account i want to transfer to and from
		person = p;
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.type = type;
		account_number = accountNbr;
		account_to_transfer = account_number_transfer;
	}
	
	// Below are getters and setters for all the fields

	public String getAccount_to_transfer() {
		return account_to_transfer;
	}

	public void setAccount_to_transfer(String account_to_transfer) {
		this.account_to_transfer = account_to_transfer;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		String ans = "\nType: " + type + "\nDate: " + date + "\nTime: " + time + "\nAmount: " + amount + "$\n";
		return ans;
	}

}
