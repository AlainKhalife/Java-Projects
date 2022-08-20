package lab8;

//*******************************************************
//Account.java
//
//A bank account class with methods to deposit to, withdraw from,
//change the name on, and get a String representation
//of the account.
//*******************************************************

public class Account
{
private static int numAccounts = 0;	
private double balance;
private String name;
private long acctNum;

//----------------------------------------------
//Constructor -- initializes balance, owner, and account number
//----------------------------------------------
public Account(double initBal, String owner, long number)
{
 balance = initBal;
 name = owner;
 acctNum = number;
 numAccounts++;
}

// Overloading the Constructor
public Account(double initBal, String owner)
{
 balance = initBal;
 name = owner;
 acctNum = (int) (Math.random()*1000 +1);
 numAccounts++;
}

public Account(String owner)
{
 balance = 0;
 name = owner;
 acctNum = (int) (Math.random()*1000 +1);
 numAccounts++;
}


// Adding method to get the number of objects
public static int getNumAccounts(){
	return numAccounts;
}



// Adding the CLOSED Method

public void close(){
	name = "CLOSED";
	balance = 0;
	numAccounts--;
}


public static Account consolidate(Account acct1, Account acct2){
	
	if(acct1.getname().equals(acct2.getname())){
	double balancenew = acct1.getBalance() + acct2.getBalance();
	long newacctnum = (int) (Math.random()*1000 +1); 
	String actname = acct1.getname();
	acct1.close();
	acct2.close();
	
	return new Account(balancenew, actname, newacctnum);
	}
	
	else if(acct1.getnumber() == acct2.getnumber()){
		
		return new Account(acct1.getBalance()+acct2.getBalance(), acct1.getname());
	}
	
	else 
		return null;
}

// Overloading the withdraw Constructor
public void withdraw(double amount, double fee)
{
 if (balance >= amount+fee)
    balance -= amount+fee;
 else
    System.out.println("Insufficient funds");
}

//----------------------------------------------
// Checks to see if balance is sufficient for withdrawal.
// If so, decrements balance by amount; if not, prints message.
//----------------------------------------------
public void withdraw(double amount)
{
 if (balance >= amount)
    balance -= amount;
 else
    System.out.println("Insufficient funds");
}

//----------------------------------------------
// Adds deposit amount to balance.
//----------------------------------------------
public void deposit(double amount)
{
 balance += amount;
}

//----------------------------------------------
// Returns balance.
//----------------------------------------------
public double getBalance()
{
 return balance;
}

//get name method
public String getname(){
	return name;
}

//get number method
public long getnumber(){
	return acctNum;
}


//----------------------------------------------
// Returns a string containing the name, account number, and balance.
//----------------------------------------------
public String toString()
{
	return "Name:" + name + 
"\nAccount Number: " + acctNum +
"\nBalance: " + balance; 
}
}

