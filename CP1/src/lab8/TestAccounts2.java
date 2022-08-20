package lab8;

import java.util.Scanner;

public class TestAccounts2 {
	
	 public static void main(String[] args)
	 {
		Account testAcct1, testAcct2, testAcct3, consolidate;

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the name of the first account");
		String acct1 = scan.nextLine();
		
		System.out.println("Enter the name of the second account");
		String acct2 = scan.nextLine();
		
		System.out.println("Enter the name of the third account");
		String acct3 = scan.nextLine();
		
		testAcct1 = new Account(acct1);
		testAcct2 = new Account(acct2);
		testAcct3 = new Account(acct3);
		testAcct1.deposit(100);
		testAcct2.deposit(100);
		testAcct3.deposit(100);
		
		// Before Consolidation
		System.out.println("Before Consolidation");
		System.out.println();
		System.out.println(testAcct1);
		System.out.println(testAcct2);
		System.out.println(testAcct3);
		
		// After Consolidation
		testAcct1.close();
		consolidate = Account.consolidate(testAcct2, testAcct3);
		
		System.out.println();
		System.out.println();
		System.out.println("After Consolidation");
		System.out.println();
		System.out.println(testAcct1);
		System.out.println();
		System.out.println(consolidate);
		
		
	 }
}
