package hw5q2;

public class Account {
	
	private String account_number, type; // An account has a number, a type
	private double balance; // The balance
	
	// Constructor
	public Account(String accNumber, String type, double balance) {
		account_number = accNumber;
		this.type = type;
		this.balance = balance;
	}

	// Below are Getters and Setters for all the fields
	
	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
