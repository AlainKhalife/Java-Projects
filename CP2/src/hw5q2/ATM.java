package hw5q2;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * I would like to note a few things:
   1. The transactions text file, the users text file and the IdandPass text file must be present under "./src/hw5q2/"
      If you like you can change the path for all files
   
   2. All the transactions that are in queue will be processed at 6:00 am in the morning. but you must at least be in the login page and press anything at that time. Even if it is wrong
      just so the method responsible for the dequeue and process can be evaluated (method run)
      
   3. All the transactions and users accounts balances will be updated in the users text file every time the user adds a transaction to queue so that if you close or
      end the program, they will still be saved.    
 */
public class ATM {

	private ArrayQueue transactions_in_queue; // This is the main queue of the ATM which will store the transactions of the users
	private int location; // This is used to pinpoint the location of the user in the ArrayList and not have to search for its position every time
	private String user_name; // This is used to store the name of the user
	private ArrayList<Person> users; // This ArrayList is used to store the users registered to use the ATM

	// Constructor
	public ATM() {
		users = new ArrayList();
		transactions_in_queue = new ArrayQueue();
		user_name = null;
		location = -99;
	}

	public void run() {
		// This method runs at exactly at 6:00am, and this methods deals with the transactions that are in main queue and processes them
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E"); // Here I used DateTimeFormater to check whether we are on a Sunday
		LocalDate localDay = LocalDate.now();
		LocalDateTime localTime = LocalDateTime.now(); // Here I used LocalDateTime to check whether we are past 6:00 pm
		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH"); // This is used to format into hours only
		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("mm"); // This is used to format into minutes only
		String day = localDay.format(dtf1); // This string is used to see on which day we are
		
		int hour = Integer.parseInt(localTime.format(dtf3)); // This int refers to hours
		int minutes = Integer.parseInt(localTime.format(dtf4)); // This int refers to the minutes
		
		
		if(transactions_in_queue.empty()) // If empty, no actions is needed, so I return
			return;

		else {
			// Otherwise we need to process the transactions that are in the main queue
			// I need to keep dequeing until there is no more elements in the main queue
			while(!(transactions_in_queue.empty())) {
				Transaction t = (Transaction) transactions_in_queue.dequeue(); // get transaction from main queue by dequeuing
				Person p = t.getPerson(); // This is used to reference the owner of the transaction
				p.removeTrasactionFromQueue(); // Since each person has his own transaction queue, i also need to dequeue it
				double amount = t.getAmount(); // This is used to reference the amount of the transaction
				String account_number = t.getAccount_number(); // This is used to reference the account number
				String account_number_transfer = t.getAccount_to_transfer(); // This is used to reference to account number that the person wants to transfer to if there is any

				if(t.getType().equals("Debit")) {
					// If the type of the transaction is "Debit", this means that I need to remove the amount of the transaction from the corresponding account
					for(int i = 0; i<p.getAccounts().size(); i++) {
						// Here I am looping through the linkedList of accounts to find the corresponding account of the person
						if(p.getAccounts().get(i).getAccount_number().equals(account_number)) {
							if((p.getAccounts().get(i).getBalance() - amount)<0) // If I found it but the debited amount exceeds the amount in the account, I must not deduct, because this would lead to a negative balance
								break;

							p.getAccounts().get(i).setBalance(p.getAccounts().get(i).getBalance() - amount); // Otherwise, I deduct from the account
							break;
						}
					}
					// I also need to make sure I update the file users
					try {
						// Updating the users file to match the new balances
						FileWriter fr = new FileWriter("./src/hw5q2/users");
						BufferedWriter br = new BufferedWriter(fr);
						
						for(int d =0; d<users.size(); d++) {
							// Here am I am updating the users file to match the new updated balance
							// I am copying to the file following the file format that I described in method startup
							String s1 = users.get(d).getName() + "// " + users.get(d).getClient_number() + "// ";
							s1 = s1 + users.get(d).listAllAccounts(); // Method listAllAccounts will give me the accounts in the correct file format
							br.write(s1 + "\n"); // Writing to the file
						}
						br.close(); // Always close Buffer
						fr.close();
					}
					catch(IOException e) {
						System.out.println("");
					}
				}

				else if(t.getType().equals("Credit")) {
					// The same thing is done here if the transaction type is "Credit"
					// Here I don't need to worry about the amount in the account since I am adding to it
					for(int i = 0; i<p.getAccounts().size(); i++) {
						if(p.getAccounts().get(i).getAccount_number().equals(account_number)) {
							p.getAccounts().get(i).setBalance(p.getAccounts().get(i).getBalance() + amount);
							break;
						}
					}
					// Also Update the users file
					try {
						// Updating the users file to match the new balances
						FileWriter fr = new FileWriter("./src/hw5q2/users");
						BufferedWriter br = new BufferedWriter(fr);
						
						for(int d =0; d<users.size(); d++) {
							// Here am I am updating the users file to match the new updated balance
							// I am copying to the file following the file format that I described in method startup
							String s1 = users.get(d).getName() + "// " + users.get(d).getClient_number() + "// ";
							s1 = s1 + users.get(d).listAllAccounts(); // Method listAllAccounts will give me the accounts in the correct file format
							br.write(s1 + "\n"); // Writing to the file
						}
						br.close(); // Always close Buffer
						fr.close();
					}
					catch(IOException e) {
						System.out.println("");
					}
				}

				else {
					// Otherwise the transaction type is "Transfer".
					boolean can_transfer = false; // This boolean is used to tell if the amount the user wants to transfer is possible and so I don't get a negative balance
					
					// First I need to find the account I want to transfer to, so I loop through the account list of the user to find the account
					for(int i = 0; i<p.getAccounts().size(); i++) {
						// When searching for the account that I want to transfer from, I also need to make sure that when transferring I don't get a negative balance
						if(p.getAccounts().get(i).getAccount_number().equals(account_number)) {
							if((p.getAccounts().get(i).getBalance() - amount)<0) // If the balance of the from account would turn out negative, then I don't need to deduct 
								break;
							p.getAccounts().get(i).setBalance(p.getAccounts().get(i).getBalance() - amount); // Otherwise I could deduct from the account the user wants to transfer from
							can_transfer = true;
						}

					}
					
					if(can_transfer == true) {  // If I can transfer then, I should find the account that I want to transfer to from the users account list and add the amount
						for(int j = 0; j<p.getAccounts().size(); j++) {
							if(p.getAccounts().get(j).getAccount_number().equals(account_number_transfer)) { // When I found the account number the user wants to transfer to, I add the amount
								p.getAccounts().get(j).setBalance(p.getAccounts().get(j).getBalance() + amount);
								break;
							}
						}
					}
					
					// Also updating the users file
					try {
						// Updating the users file to match the new balances
						FileWriter fr = new FileWriter("./src/hw5q2/users");
						BufferedWriter br = new BufferedWriter(fr);
						
						for(int d =0; d<users.size(); d++) {
							// Here am I am updating the users file to match the new updated balance
							// I am copying to the file following the file format that I described in method startup
							String s1 = users.get(d).getName() + "// " + users.get(d).getClient_number() + "// "; // s1 should match the correct format of the file users
							s1 = s1 + users.get(d).listAllAccounts(); // Method listAllAccounts will give me the accounts in the correct file format of the file users
							br.write(s1 + "\n"); // Writing to the file
						}
						br.close(); // Always close Buffer
						fr.close();
					}
					catch(IOException e) {
						System.out.println("");
					}
				}
			}
		}
	}

	
	public void startup() {
		// This method is used to read the information stored in the file about the users and create the appropriate objects
		/*
		 * Information about the file read to create the objects and how I divided it
		 * This is the content of the users file:
		 * Alain Khalife// 201807278// 120425, savings, 2100/ 122001, savings, 1200
		   John Smith// 11203201// 120414, savings, 1200
		   Leo Sam// 124530152// 252014, checking, 0
		   
		   This is my file format:
		   client_name// client_number// account_number1, account_type, account_balace/ account_number2, account_type, account_balace ...
		   
		   I divided it into 2 parts:
		   The first parts are separated by '// ' and the other parts by '/ '
		   The parts the are followed by '// ' are related the the user
		   The parts separated by '/ ' are information related to the accounts that the users have
		   
		    Ex: in the first line: Alain Khalife// 201807278// 120425, savings, 2100/ 122001, savings, 1200
		    The name of the user is: Alain Khalife
		    His number (client number) is : 201807278
		    Since there is no more //, everything else is information related to the accounts that this person owns
		    He has 2 accounts:
		    Account 1: Account number: 120425       |     Account 2: Account number: 122001
		               Account type: Savings        |                Account type: Savings
		               Account balance: 2100$       |                Account balance: 1200$
		               
		   The account information are coma and space separated (, )
		   EVERY USER HAS TO HAVE AT LEAST 1 ACCOUNT, OTHERWISE HE CANNOT BE IN THE BANK           
		 */
		try {
			FileReader fr = new FileReader("./src/hw5q2/users"); // The file is stored in the package under the name user 
			BufferedReader br = new BufferedReader(fr);
			String s = null;
			while((s = br.readLine()) != null) {
				try {
				String[] a = s.split("// "); // First I am seperating the information related to user and the ones related to his accounts 
				String person_name = a[0]; // person's name
				int person_number = Integer.parseInt(a[1]); // person's client number
				String[] split_accounts = a[2].split("/ "); // Here I am sure a[2] contains a string that is '/ ' separated which holds the information of the accounts. Therefore I must separate it too

				LinkedList accounts = new LinkedList(); // Initializing the linkedlist of accounts so I can add it to the person
				for(int i = 0; i<split_accounts.length; i++) {
					String acc = split_accounts[i]; // Here I got the string that holds the account information about the account which is coma and space separated (, ), and therefore must split it too
					String[] account_details = acc.split(", "); 
					// After the split, I am sure that a[0] holds the account number, a[1] holds its type and a[2] holds its balance
					accounts.add(new Account(account_details[0], account_details[1], Double.parseDouble(account_details[2])));
				}
				Person p = new Person(person_name, person_number, accounts); // Creating the person object and passing to it the corresponding information
				users.add(p);
			}
			catch(NumberFormatException e) {
				continue;
			}
			catch(NullPointerException e) {
				continue;
			}
		  }
			br.close();
			fr.close();
			
			// Below I am adding the transactions that were in queue in case the program ends and I want to process them during the next day from file transactions
			/*
			   The format of the file transaction is the following:
			   client_number,transaction_date,transaction_time,transaction_amount,transaction_type,account_number,account_number_to_transfer_to
			   
			   The file is coma separated(",") (no space)
			 */
			FileReader fr2 = new FileReader("./src/hw5q2/transactions");
			BufferedReader br2 = new BufferedReader(fr2);
			String s2 = null;
			while((s2=br2.readLine())!=null) {
				String[] b = s2.split(","); // This is the array that I want to add the elements of the transaction to
				/*
				 * Once the string is split I should have the following
				   b[0] = client number
				   b[1] = transaction date
				   b[2] = transaction time
				   b[3] = amount
				   b[4] = type
				   b[5] = original account number
				   b[6] = account number of the one one I want to transfer to (if present, if not it will be null)
				 */
				for(int i = 0; i<users.size(); i++) {
					if((Integer.parseInt(b[0]) == (users.get(i).getClient_number())) && (b[4].equals("Transfer"))) {
						// If I found the corresponding user id and the type is transfer
						// Then I should add b[6] to my transaction constructor
						Transaction t = new Transaction(users.get(i), b[1], b[2], Double.parseDouble(b[3]), b[4], b[5], b[6]);
						transactions_in_queue.enqueue(t); // Adding to main queue
						users.get(i).addTransactionToQueue(t); // Adding to the person's queue
						break;
					}
					
					if(Integer.parseInt(b[0]) == (users.get(i).getClient_number())) {
						// If I found the corresponding user id and the type is not transfer
						// Then I could not add b[6] to my transaction constructor
							Transaction t = new Transaction(users.get(i), b[1], b[2], Double.parseDouble(b[3]), b[4], b[5]);
							transactions_in_queue.enqueue(t);
							users.get(i).addTransactionToQueue(t);
							break;
						}
					}
				}

			br2.close(); // Always close
			fr2.close();
			}
			
		catch (FileNotFoundException e) {
			System.out.println("Wrong read file path");
		}
		catch(IOException e) {
			System.out.println("Something went wrong, check the path of the file");
		}
	}

	
	
	public boolean cridentialsCheck() {
		/*
		 * This methods checks for the id and password for of the users to log him in
		   The ID and Password of the users are stored in different text file called IDandPass
		   NOTE: THE ID FOR LOGIN AND THE CLIENT_NUMBER MUST BE IDENTICAL IN ORDER TO MATCH
		   EX: In the first file (users) in the first line : Alain Khalife// 201807278// 120425, savings, 2100/ 122001, savings, 1200
		       The client number is 201807278, therefore in the IDandPass file, the id must be identical to the client number 201807278
		       
		   Here is the format of the IDandPass file:
		   ID, password
		   ID2, password
		   ...
		   
		   THE FILE IS COMA AND SPACE SEPARTED (, )
		   
		 */
		boolean check = false;
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter your Id Number: ");
			int id = scan.nextInt(); // Asking for the id
			System.out.print("Enter you password: ");
			String pass = scan.next(); // Asking for the password

			try {
				FileReader fr = new FileReader("./src/hw5q2/IDandPass"); // Reading from file IDandPass
				BufferedReader br = new BufferedReader(fr);
				String s = null;
				while((s=br.readLine())!=null) {
					String[] a = s.split(", ");
					if((Integer.parseInt(a[0]) == id) && a[1].equals(pass)) {  // If the id and password match, this means that the credentials are correct
						check = true;
						break;
					}
				}
				if(check == false) // If not, the user needs to reenter the cridentials
					System.out.println("\nInvalid ID or password\n");
				br.close();
				fr.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("Wrong read file path");
			}
			catch(IOException e) {
				System.out.println("Something went wrong");
			}

			for(int i = 0; i<users.size(); i++) {
				// Here I am searching for the position of the user in the users ArrayList and pinpointing his location in the array list
				if(users.get(i).getClient_number() == id) {
					user_name = users.get(i).getName(); // This will point to his name
					location = i; // This location is the location of the person the ArrayList
					break;
				}	
			}
			
			if(user_name == null) {
				System.out.println("Invalid ID or password");
				return false;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid ID number");
		}
		System.out.println();
		
		
		return check;
	}
	
	

	public void withdrawMoney() {
		// This method is the withdraw method for the menu when the user logs in
		
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E"); // Here I used DateTimeFormater to check whether we are on a Sunday
		LocalDate localDay = LocalDate.now();
		LocalDateTime localTime = LocalDateTime.now(); // Here I used LocalDateTime to check whether we are past 6:00 pm or 18:00
		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH"); // This is used to format into hours only
		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("mm"); // This is used to format into minutes only
		String day = localDay.format(dtf1); // This string is used to see on which day we are
		
		int hour = Integer.parseInt(localTime.format(dtf3)); // This int refers to hours
		int minutes = Integer.parseInt(localTime.format(dtf4)); // This int refers to the minutes

		boolean found = false; // This boolean is used to indicate if the account has been found or not
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the account number of which you would like to withdraw from: ");
		String acc_number = scan.next(); // Asking for the account number in which the user wants to withdraw
		for(int i = 0; i<users.get(location).getAccounts().size(); i++) {
			// Checking the LinkedList of Account of the user to see if I found it
			if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_number)) {
				found = true;
				// If found I need to check the time first

				if(day.equalsIgnoreCase("Sun") || (hour>=18 && minutes>0) || (hour<6)) {
					// If we are on a Sunday or the time is passed 6:00pm or below 6:00am, this means that the Bank is closed and all transactions must be queued
					System.out.println("\nSorry you are out of the working hours, all your transactions will be processed in the morning at 6:00am");
					System.out.print("How much would you like to schedule for withdrawal: ");
					try {
						double withdraw = scan.nextDouble();
						while(withdraw<0) {
							// Cannot withdraw a negative value
							System.out.print("Value must be positive, enter again: ");
							withdraw = scan.nextDouble();
						}
						if((users.get(location).getAccounts().get(i).getBalance() - withdraw) > 0) { // Need to make sure the balance doesn't get negative
							// If the user can withdraw then, I need to add his transaction to the main queue
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // These are used to set the time and date of the transaction
							LocalDate localDate = LocalDate.now();
							DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
							LocalDateTime now = LocalDateTime.now();
							Transaction t = new Transaction(users.get(location), dtf.format(localDate), time.format(now), withdraw, "Debit", acc_number); // Creating the transaction of type Debit and adding it to the queue
							transactions_in_queue.enqueue(t); // Adding the transaction to the main queue
							users.get(location).addTransactionToQueue(t); // Also adding to the queue of the person
							
							try {
								// Here I am copying the transaction information to the file transactions so I can keep track of them in case the program closes
								FileWriter fr = new FileWriter("./src/hw5q2/transactions", true);
								BufferedWriter br = new BufferedWriter(fr);
								// Now I am copying the transaction details following the appropriate format of the file described in method startup
								br.write(t.getPerson().getClient_number() + "," + t.getDate() + "," + t.getTime() + "," + t.getAmount() + "," + t.getType() + "," + t.getAccount_number()+ "\n");
								br.close(); // Always close the Buffer
								fr.close();
								
							}
							catch(IOException e) {
								System.out.println("File transaction not found");
							}
							System.out.println("\nYour transaction will be scheduled for tommorow morning at 6:00am\n");
							break;
						}

						
						else {
							// Otherwise the user cannot make a withdraw because the withdraw amount is bigger than the account balance
							System.out.println("\nCould not schedule a withdraw due to insufficient founds\n");
							break;
						}
					}
					catch(InputMismatchException e) {
						System.out.println("\nWrong withdrawal amount\n");
					}
				}

				else {
					// If we are during work time, then the user can withdraw immediatly or add to the queue
					System.out.print("\nWould you like to withdraw immediatly ? (y/n)\n");
					System.out.println("Any input other than \"y\" will be considered as a no and the transaction will be queued");
					String choice = scan.next();
					if(choice.equalsIgnoreCase("y")) {
						System.out.println("\nHow much would you like to withdraw: ");
						try {
							// Same thing as above, checking for the values of the withdraw ammount and making sure the balance doesn't go negative
							double withdraw = scan.nextDouble();
							while(withdraw<0) {
								System.out.print("Value must be positive, enter again: ");
								withdraw = scan.nextDouble();
							}
							if((users.get(location).getAccounts().get(i).getBalance() - withdraw) >= 0) {
								// If you can withdraw, then the balance id changed
								double remaining = users.get(location).getAccounts().get(i).getBalance() - withdraw; // This is the remaining balance
								users.get(location).getAccounts().get(i).setBalance(remaining); // Setting the balance of the account to the remaining
								try {
									// Updating the users file to match the new balances
									FileWriter fr = new FileWriter("./src/hw5q2/users");
									BufferedWriter br = new BufferedWriter(fr);
									
									for(int d =0; d<users.size(); d++) {
										// Here am I am updating the users file to match the new updated balance
										// I am copying to the file following the file format that I described in method startup
										String s1 = users.get(d).getName() + "// " + users.get(d).getClient_number() + "// ";
										s1 = s1 + users.get(d).listAllAccounts(); // Method listAllAccounts will give me the accounts in the correct file format
										br.write(s1 + "\n"); // Writing to the file
									}
									br.close(); // Always close Buffer
									fr.close();
								}
								catch(IOException e) {
									System.out.println("");
								}
								System.out.println("\nWithdrawal succesful\n");
								break;
							}

							else {
								System.out.println("\nCould not withdraw from account due to insufficient founds\n");
								break;
							}
						}
						catch(InputMismatchException e) {
							System.out.println("\nWrong withdrawal ammount\n");
						}
					}
					
					
					else {
						// Here the user chose no, and therefore the transaction will be added to queue
						System.out.println("\nHow much would you like to schedule for withdrawal: ");
						try {
							double withdraw = scan.nextDouble();
							while(withdraw<0) {
								System.out.print("Value must be positive, enter again: ");
								withdraw = scan.nextDouble();
							}
							if((users.get(location).getAccounts().get(i).getBalance() - withdraw) > 0) {
								// Again, setting up the date and time for the transaction
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
								LocalDate localDate = LocalDate.now();
								DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
								LocalDateTime now = LocalDateTime.now();
								Transaction t = new Transaction(users.get(location), dtf.format(localDate), time.format(now), withdraw, "Debit", acc_number);
								transactions_in_queue.enqueue(t); // Adding transaction to main queue
								users.get(location).addTransactionToQueue(t); // Adding transaction to the user's queue
								try {
									// Here I am copying the transaction information to the file transactions so I can keep track of them in case the program closes
									FileWriter fr = new FileWriter("./src/hw5q2/transactions", true);
									BufferedWriter br = new BufferedWriter(fr);
									// Now I am copying the transaction details following the appropriate format of the file described in method startup
									br.write(t.getPerson().getClient_number() + "," + t.getDate() + "," + t.getTime() + "," + t.getAmount() + "," + t.getType() + "," + t.getAccount_number()+ "\n");
									br.close(); // Always close the Buffer
									fr.close();
									
								}
								catch(IOException e) {
									System.out.println("File transaction not found");
								}
								System.out.println("\nYour transaction will be scheduled for tommorow morning\n");
								break;
							}

							else {
								System.out.println("\nCould not schedule a withdraw due to insufficient founds\n");
								break;
							}
						}
						catch(InputMismatchException e) {
							System.out.println("\nWrong withdrawal amount\n");
						}
					}

				}
			}
		}

			if(found == false)
				System.out.println("\nNo such account number exist\n");

	}

	
	
	public void depositMoney() {
		// This is the deposit money method
		boolean found = false; // This boolean is used to indicate if the account has been found or not
		Scanner scan = new Scanner(System.in);
		System.out.println("\nPlease enter the account number of which you would like to deposit to: ");
		String acc_number = scan.next(); // Asking for the amount to deposit
		
		// Again, setting up Date and time formatters to see if we are during work time or no
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E");
		LocalDate localDay = LocalDate.now();
		LocalDateTime localTime = LocalDateTime.now();
		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH");
		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("mm");
		String day = localDay.format(dtf1);
		int hour = Integer.parseInt(localTime.format(dtf3));
		int minutes = Integer.parseInt(localTime.format(dtf4));
		
		if(day.equalsIgnoreCase("Sun") || (hour>=18 && minutes>0) || (hour<6)) {
			// If we are on a Sunday or the time has passed 6:00pm or below 6:00am, this means that the Bank is closed and all transactions must be queued
			for(int i = 0; i<users.get(location).getAccounts().size(); i++) {
				// Searching for the account number in the linked list of accounts of the user
				if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_number)) {
					found = true; // Account has been found
					System.out.println("\nSorry you are out of the working hours, all your transactions will be processed tommorrow morning at 6:00am");
					System.out.print("How much money would you like to schedual for deposit: ");
					try {	
						double deposit = scan.nextDouble();
						// Making sure the deposit value is positive
						while(deposit<0) {
							System.out.print("Value must be positive, enter again: ");
							deposit = scan.nextDouble();
						}
						// These Date and Time formatters are used for the transactions fields date and time
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDate localDate = LocalDate.now();
						DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						Transaction t = new Transaction(users.get(location), dtf.format(localDate), time.format(now), deposit, "Credit", acc_number);
						transactions_in_queue.enqueue(t); // Adding transaction to main queue 
						users.get(location).addTransactionToQueue(t); // Adding transaction to the users queue
						try {
							// Here I am copying the transaction information to the file transactions so I can keep track of them in case the program closes
							FileWriter fr = new FileWriter("./src/hw5q2/transactions", true);
							BufferedWriter br = new BufferedWriter(fr);
							// Now I am copying the transaction details following the appropriate format of the file described in method startup
							br.write(t.getPerson().getClient_number() + "," + t.getDate() + "," + t.getTime() + "," + t.getAmount() + "," + t.getType() + "," + t.getAccount_number()+ "\n");
							br.close(); // Always close the Buffer
							fr.close();
							
						}
						catch(IOException e) {
							System.out.println("File transaction not found");
						}
						System.out.println("\nYour deposit amount has been scheduled for tomorrow morning at 6:00 am\n");
						break;
					}
					catch(InputMismatchException e) {
						System.out.println("\nWrong deposit amount\n");
					}
				}
			}

			if(found == false)
				System.out.println("\nNo such account exist\n");
		}
		
		else {
			// If we are during working hours, then the user can directly withdraw
			for(int i = 0; i<users.get(location).getAccounts().size(); i++) {
				if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_number)) {
					// Again, searching for the account to see if found
					found = true;
					System.out.println("\nHow much money would you like to deposit: ");
					try {	
						double deposit = scan.nextDouble();
						while(deposit<0) {
							// Making sure value is positive
							System.out.print("Value must be positive, enter again: ");
							deposit = scan.nextDouble();
						}
						users.get(location).getAccounts().get(i).setBalance(users.get(location).getAccounts().get(i).getBalance() + deposit); // Changing the balance of the account by adding to it the deposit
						try {
							// Updating the users file to match the new balances
							FileWriter fr = new FileWriter("./src/hw5q2/users");
							BufferedWriter br = new BufferedWriter(fr);
							
							for(int d =0; d<users.size(); d++) {
								// Here am I am updating the users file to match the new updated balance
								// I am copying to the file following the file format that I described in method startup
								String s1 = users.get(d).getName() + "// " + users.get(d).getClient_number() + "// ";
								s1 = s1 + users.get(d).listAllAccounts(); // Method listAllAccounts will give me the accounts in the correct file format
								br.write(s1 + "\n"); // Writing to the file
							}
							br.close(); // Always close Buffer
							fr.close();
						}
						catch(IOException e) {
							System.out.println("");
						}
						System.out.println("\nYour new balance is: " + users.get(location).getAccounts().get(i).getBalance() + "$\n");
						break;
					}
					catch(InputMismatchException e) {
						System.out.println("\nWrong deposit amount\n");
					}
				}
			}

			if(found == false)
				System.out.println("\nNo such account exist\n");
		}
	}
	
	
	

	public void transferMoney() {
		// This is the transfer Money method for the menu
		
		if(users.get(location).getAccounts().size()==1) // If the user has only 1 account, then he cannot transfer money between 2 accounts
			System.out.println("\nYou cannot transfer money since you only have 1 account\n");

		else {
			// Otherwise he can
			boolean found_from_account = false; // This is used to check if the account the user wants to transfer from is available
			boolean found_to_account = false; // This is used to check if the account the user wants to transfer to is available
			int location_from = -1; // This is used to point to the location the account to transfer from in the linkedList
			int location_to = -1; // This is used to point to the location the account to transfer to in the linkedLis
			Scanner scan = new Scanner(System.in);
			System.out.println("\nPlease enter the account number of which you would like to transfer from: ");
			String acc_from_number = scan.next(); // Asking for the account number of the account the user wants to transfer from
			System.out.println("\nPlease enter the account number of which you would like to transfer to: ");
			String acc_to_number = scan.next(); // Asking for the account number of the account the user wants to transfer to

			if(acc_from_number.equals(acc_to_number)) // If the user enters the same account number, he cannot transfer because its the same account
				System.out.println("\nCannot transfer to the same account\n");


			else {
				// Otherwise he can
				// Here same as before explained, setting up the Date and Time formatters to see if we are during working hours
				DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E");
				LocalDate localDay = LocalDate.now();
				LocalDateTime localTime = LocalDateTime.now();
				DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH");
				DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("mm");
				String day = localDay.format(dtf1);
				int hour = Integer.parseInt(localTime.format(dtf3));
				int minutes = Integer.parseInt(localTime.format(dtf4));

				if(day.equalsIgnoreCase("Sun") || (hour>=18 && minutes>0) || (hour<6)) {
					// If we are on a Sunday or the time has passed 6:00pm or below 6:00am, this means that the Bank is closed and all transactions must be queued
					for(int i = 0; i<users.get(location).getAccounts().size(); i++) {
						// Searching for both accounts to see if they are available
						if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_from_number)) {
							// here we found the transfer from account
							found_from_account = true; // setting the boolean of from to true
							location_from = i; // pointing to the from account in the LinkedList
						}

						if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_to_number)) {
							found_to_account = true; // setting the boolean of to to true
							location_to = i; // pointing to the to account in the LinkedList
						}
					}

					if(found_from_account == false) {
						// If the from account is not found, cannot transfer
						System.out.println("\nNo account found to transfer from\n");
					}

					else if(found_to_account == false) {
						// If the to account is not found, cannot transfer
						System.out.println("\nNo account found to transfer to\n");
					}
					
					else {
					// Here we found both accounts, but we are not during working hours
					System.out.println("\nSorry you are out of the working hours, all your transactions will be processed tommorrow morning");
					System.out.print("How much would money would you like to schedule for transfer: ");
					try {
						double amount = scan.nextDouble(); // Asking for the amount to transfer
						while(amount<0) {
							// Making sure value is positive
							System.out.print("Value must be positive, enter again: ");
							amount = scan.nextDouble();
						}
						double amount_in_from = users.get(location).getAccounts().get(location_from).getBalance(); // Here I am getting the amount of the account I want to transfer from
						if((amount_in_from - amount)<0) {
							// Here I am making the amount I want to transfer does not exceed the amount the user already has in his account
							System.out.println("\nCannot transfer this much because you exceeded the amount (" + amount_in_from + "$) present in this account");
						}
						else {
							// Adding Transaction to queue
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
							LocalDate localDate = LocalDate.now();
							DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
							LocalDateTime now = LocalDateTime.now();
							Transaction t = new Transaction(users.get(location), dtf.format(localDate), time.format(now), amount, "Transfer", acc_from_number, acc_to_number);
							transactions_in_queue.enqueue(t); // Adding to main queue
							users.get(location).addTransactionToQueue(t); // Adding to the queue of the user
							try {
								// Here I am copying the transaction information to the file transactions so I can keep track of them in case the program closes
								FileWriter fr = new FileWriter("./src/hw5q2/transactions", true);
								BufferedWriter br = new BufferedWriter(fr);
								// Now I am copying the transaction details following the appropriate format of the file described in method startup
								br.write(t.getPerson().getClient_number() + "," + t.getDate() + "," + t.getTime() + "," + t.getAmount() + "," + t.getType() + "," + t.getAccount_number() + "," + t.getAccount_to_transfer() + "\n");
								br.close(); // Always close the Buffer
								fr.close();
								
							}
							catch(IOException e) {
								System.out.println("File transaction not found");
							}
							System.out.println("\nYour account money transfer has been squeduled for tommorow morning\n");
						}
					}
					catch(InputMismatchException e) {
						System.out.println("\nWrong transfer amount\n");
					}
				  }
				}

				
				else {
					// Here we are during working hours
					// All the setps here are exactly the same as above except that this time, the user can make his transfer immediatly.
					for(int i = 0; i<users.get(location).getAccounts().size(); i++) {
						if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_from_number)) {
							found_from_account = true;
							location_from = i;
						}

						if(users.get(location).getAccounts().get(i).getAccount_number().equals(acc_to_number)) {
							found_to_account = true;
							location_to = i;
						}
					}

					if(found_from_account == false) {
						System.out.println("\nNo account found to transfer from\n");
					}

					else if(found_to_account == false) {
						System.out.println("\nNo account found to transfer to\n");
					}

					else {
						System.out.print("\nWould you like to transfer immediatly ? (y/n)\n");
						System.out.println("Any input other than \"y\" will be considered as a no and the transaction will be queued for tomorrow morning");
						String ans = scan.next();
						if(ans.equalsIgnoreCase("y")) {
							System.out.print("\nHow much would money would you like to transfer: ");
							try {
								double amount = scan.nextDouble();
								while(amount<0) {
									System.out.print("Value must be positive, enter again: ");
									amount = scan.nextDouble();
								}
								double amount_in_from = users.get(location).getAccounts().get(location_from).getBalance();
								if(amount_in_from-amount<0) {
									System.out.println("\nCannot transfer this much because you exceeded the amount (" + amount_in_from + "$) present in this account");
								}
								else {
									users.get(location).getAccounts().get(location_from).setBalance(amount_in_from-amount);
									users.get(location).getAccounts().get(location_to).setBalance(users.get(location).getAccounts().get(location_to).getBalance()+amount);
									try {
										// Updating the users file to match the new balances
										FileWriter fr = new FileWriter("./src/hw5q2/users");
										BufferedWriter br = new BufferedWriter(fr);
										
										for(int d =0; d<users.size(); d++) {
											// Here am I am updating the users file to match the new updated balance
											// I am copying to the file following the file format that I described in method startup
											String s1 = users.get(d).getName() + "// " + users.get(d).getClient_number() + "// ";
											s1 = s1 + users.get(d).listAllAccounts(); // Method listAllAccounts will give me the accounts in the correct file format
											br.write(s1 + "\n"); // Writing to the file
										}
										br.close(); // Always close Buffer
										fr.close();
									}
									catch(IOException e) {
										System.out.println("");
									}
									System.out.println(amount + "$ have been transfered");
								}
							}
							catch(InputMismatchException e) {
								System.out.println("\nWrong transfer amount\n");
							}
						}

						else {
							System.out.print("\nHow much would money would you like to schedule for transfer: ");
							try {
								double amount = scan.nextDouble();
								while(amount<0) {
									System.out.print("Value must be positive, enter again: ");
									amount = scan.nextDouble();
								}
								double amount_in_from = users.get(location).getAccounts().get(location_from).getBalance();
								if(amount_in_from-amount<0) {
									System.out.println("\nCannot transfer this much because you exceeded the amount (" + amount_in_from + "$) present in this account");
								}
								else {
									DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
									LocalDate localDate = LocalDate.now();
									DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
									LocalDateTime now = LocalDateTime.now();
									Transaction t = new Transaction(users.get(location), dtf.format(localDate), time.format(now), amount, "Transfer", acc_from_number, acc_to_number);
									transactions_in_queue.enqueue(t);
									users.get(location).addTransactionToQueue(t);
									try {
										// Here I am copying the transaction information to the file transactions so I can keep track of them in case the program closes
										FileWriter fr = new FileWriter("./src/hw5q2/transactions", true);
										BufferedWriter br = new BufferedWriter(fr);
										// Now I am copying the transaction details following the appropriate format of the file described in method startup
										br.write(t.getPerson().getClient_number() + "," + t.getDate() + "," + t.getTime() + "," + t.getAmount() + "," + t.getType() + "," + t.getAccount_number() + "," + t.getAccount_to_transfer() + "\n");
										br.close(); // Always close the Buffer
										fr.close();
										
									}
									catch(IOException e) {
										System.out.println("File transaction not found");
									}
									System.out.println("\nYour account money transfer has been squeduled for tommorow morning\n");
								}
							}
							catch(InputMismatchException e) {
								System.out.println("\nWrong transfer amount\n");
							}
						}
					}
				}
			}
		}
	}

	
	
	public void displayAccountInfo() {
		// This method displays the information and balance of the accounts that the user has
		System.out.println("\nHere is the balance of all your accounts: ");
		for(int i = 0; i<users.get(location).getAccounts().size(); i++) {
			// Looping through the LinkedList of accounts of the user and printing there details and balance
			System.out.println("Account Id: " + users.get(location).getAccounts().get(i).getAccount_number());
			System.out.println("Balance: " + users.get(location).getAccounts().get(i).getBalance() + "$\n");
		}
		System.out.println("--------------------------------------------------");
	}

	
	
	public void exit() {
		// This is the exit option
		System.out.println("Here are all the transactions in queue:");
		users.get(location).listAllQueue(); // Listing all the transactions that the user have in queue
	}
	
	

	public void menu() {
		// This method is the menu method which simply calls the methods above
		Scanner scan = new Scanner(System.in);
		int choice =0;
		while(choice!=5) {
			System.out.println("Welcome " + user_name + "\n");
			System.out.println("1. Withdraw money\n2. Deposit money\n3. Transfer of Money\n4. Display account info\n5. Exit\n- - - - - - - - - - - - \n");
			System.out.print("Input your choice: ");
			try {
				choice = scan.nextInt();
				switch(choice) {
				case 1:
					withdrawMoney();
					break;

				case 2:
					depositMoney();
					break;

				case 3:
					transferMoney();
					break;

				case 4:
					displayAccountInfo();
					break;

				case 5: 
					exit();
					break;

				default: 
					System.out.println("\nInvalid choice, enter again\n");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("\nInvalid choice, enter again\n");
				scan.next();
			}
		}
	}
	
	public void checkTime() {
		// This method checks the time of the day to see if it is 6:00 am and we are not on a Sunday
		// If it is, it will call method run that will process all the transactions that were in main queue
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E"); // Here I used DateTimeFormater to check whether we are on a Sunday
		LocalDate localDay = LocalDate.now();
		LocalDateTime localTime = LocalDateTime.now(); // Here I used LocalDateTime to check whether we are past 6:00 pm or 18:00
		DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH"); // This is used to format into hours only
		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("mm"); // This is used to format into minutes only
		String day = localDay.format(dtf1); // This string is used to see on which day we are
		
		int hour = Integer.parseInt(localTime.format(dtf3)); // This int refers to hours
		int minutes = Integer.parseInt(localTime.format(dtf4)); // This int refers to the minutes
		
		if((hour==6 && minutes == 0) && (!day.equals("Sun"))) { // If it is 6:00 am, and we are not on Sunday, then I need to process the transactions
			run(); // Calling method run
			try {
			// Here, since I processed the transactions that were in queue, there is no need to keep them in the file transactions, Therefore I need to remove them
			PrintWriter writer = new PrintWriter("./src/hw5q2/transactions");
			writer.print("");
			writer.close();
			}
			catch(IOException e) {
				System.out.println("Something went wrong");
			}
		}
	}
	
	// Method main
	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.startup(); // Starting up the ATM by calling the startup method to initialize the objects
		atm.checkTime(); // Checking time to process transactions in queue
		while(true) {
			atm.checkTime(); // Checking time again
			boolean check = false;
			while(check==false) {
				atm.checkTime(); 
				check = atm.cridentialsCheck();
				atm.checkTime();
			}
			atm.menu();
			System.out.println("------------------------");
		}
	}

}
