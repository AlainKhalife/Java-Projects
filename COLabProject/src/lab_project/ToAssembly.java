package lab_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ToAssembly {
	
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String s = null;
			while((s=br.readLine())!=null) {
				String[] a = s.split(" "); // Splitting at space
				String[] comment = s.split("//");
				
				// Several cases must be handeled
				if(a[0].equalsIgnoreCase("int")) {
					// Now I know that my string is of the form int x = x + c where every word and character is separated by a space
					// To make it more dynamic, I will play with the ASCII code of the letters and use their number as the register numbers
					if(a[4].equals("+")) {
						if(Character.isDigit(a[3].charAt(0))) {
							// If my character in the string is a digit, then I should perform an ADDI operation
							// Here my digit is in a[3]. Ex: int a = 1 + a
							System.out.print("ADDI X" + (int)(a[1].charAt(0)-97) + ", X" + (int)(a[5].charAt(0)-97) + ", #" + a[3].charAt(0));
							if(comment.length>1)
								System.out.print(" // " + comment[1]);
							System.out.println("\n");
						}
						
						else if(Character.isDigit(a[5].charAt(0))) {
							// Now the same thing but this time if a digit is present in a[5]. EX: int a = a + 1
							System.out.print("ADDI X" + (int)(a[1].charAt(0)-97) + ", X" + (int)(a[3].charAt(0)-97) + ", #" + a[5].charAt(0));
							if(comment.length>1)
								System.out.print(" // " + comment[1]);
							System.out.println("\n");
						}
						
						else {
							// Otherwise, I am adding two registers. EX: int a = a + b
							System.out.print("ADD X" + (int)(a[1].charAt(0)-97) + ", X" + (int)(a[3].charAt(0)-97) + ", X" + (int)(a[5].charAt(0)-97));
							if(comment.length>1)
								System.out.print(" //" + comment[1]);
							System.out.println("\n");
						}
					}
					
					else if(a[4].equals("-")) {
						// Now in case I have a - sign. I should print the SUBI command
						if(Character.isDigit(a[3].charAt(0))) {
							// If my character in the string is a digit, then I should perform an ADDI operation
							// Here my digit is in a[3]. Ex: int a = 1 - a;
							System.out.print("SUBI X" + (int)(a[1].charAt(0)-97) + ", X" + (int)(a[5].charAt(0)-97) + ", #" + a[3].charAt(0));
							if(comment.length>1)
								System.out.print(" //" + comment[1]);
							System.out.println("\n");
						}
						
						else if(Character.isDigit(a[5].charAt(0))) {
							// Now the same thing but this time if a digit is present in a[5]. EX: int a = a - 1;
							System.out.print("SUBI X" + (int)(a[1].charAt(0)-97) + ", X" + (int)(a[3].charAt(0)-97) + ", #" + a[5].charAt(0));
							if(comment.length>1)
								System.out.print(" //" + comment[1]);
							System.out.println("\n");
						}
						
						else {
							// Otherwise, I am adding two registers. EX: int a = a - b;
							System.out.print("SUB X" + (int)(a[1].charAt(0)-97) + ", X" + (int)(a[3].charAt(0)-97) + ", X" + (int)(a[5].charAt(0)-97));
							if(comment.length>1)
								System.out.print(" //" + comment[1]);
							System.out.println("\n");
						}
					}
				}
				
				else if(a[0].equalsIgnoreCase("if")) {
					String condition = a[2]; // This will hold the comparator sign < or > or ==
					char comparator1 = a[1].charAt(1); // This will hold the first comparator in the if statement
					char comparator2 = a[3].charAt(0); // This will hold the second comparator in the if statement
					String functions_in_if = "";
					String functions_in_else= "";
					boolean is_else = false;
					
					while(!((s=br.readLine()).charAt(0)=='}')) { // Now Continue looping until you find a closing bracket }
						try {
						String[] b = s.split(" ");
						String[] comm = s.split("//");
						
						if(b[3].equals("+")) {
							if(Character.isDigit(b[2].charAt(0))) {
								// If my character in the string is a digit, then I should perform an ADDI operation
								// Here my digit is in b[2]. Ex: a = 1 + a
								functions_in_if += "\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2];
								
								if(comm.length>1)
									functions_in_if += " //" + comm[1];
								functions_in_if+="\n";
							}
							
							else if(Character.isDigit(b[4].charAt(0))) {
								// Now the same thing but this time if a digit is present in b[5]. EX: int a = a + 1
								functions_in_if += "\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4];
								if(comm.length>1)
									functions_in_if += " //" + comm[1];
								functions_in_if+="\n";
							}
							
							else {
								// Otherwise, I am adding two registers. EX: int a = a + b
								functions_in_if += "\tADD X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97);
								if(comm.length>1)
									functions_in_if += " //" + comm[1];
								functions_in_if+="\n";
							}
						}
						
						else if(a[4].equals("-")) {
							// Now in case I have a - sign. I should print the SUBI command
							if(Character.isDigit(b[2].charAt(0))) {
								// If my character in the string is a digit, then I should perform an ADDI operation
								// Here my digit is in b[2]. Ex: a = 1 - a
								functions_in_if += "\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2];
								if(comm.length>1)
									functions_in_if += " //" + comm[1];
								functions_in_if+="\n";
							}
							
							else if(Character.isDigit(b[4].charAt(0))) {
								// Now the same thing but this time if a digit is present in b[4]. EX: a = a - 1
								functions_in_if += "\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4];
								if(comm.length>1)
									functions_in_if += " //" + comm[1];
								functions_in_if+="\n";
							}
							
							else {
								// Otherwise, I am adding two registers. EX: a = a + b
								functions_in_if += "\tSUB X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97);
								if(comm.length>1)
									functions_in_if += " //" + comm[1];
								functions_in_if+="\n";
							}
						 }
					  }
						catch(ArrayIndexOutOfBoundsException e) {
							continue;
						}
					}
					
					if(s.equals("}else{")) {
						is_else = true;
						while(!((s=br.readLine()).charAt(0)=='}')) { // Now Continue looping until you find a closing bracket }
							try {
							String[] b = s.split(" ");
							String[] comm = s.split("//");
							
							if(b[3].equals("+")) {
								if(Character.isDigit(b[2].charAt(0))) {
									// If my character in the string is a digit, then I should perform an ADDI operation
									// Here my digit is in b[2]. Ex: a = 1 + a
									functions_in_else += "\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2];
									
									if(comm.length>1)
										functions_in_else += " //" + comm[1];
									functions_in_else+="\n";
								}
								
								else if(Character.isDigit(b[4].charAt(0))) {
									// Now the same thing but this time if a digit is present in b[5]. EX: int a = a + 1
									functions_in_else += "\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4];
									if(comm.length>1)
										functions_in_else += " //" + comm[1];
									functions_in_else+="\n";
								}
								
								else {
									// Otherwise, I am adding two registers. EX: int a = a + b
									functions_in_else += "\tADD X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97);
									if(comm.length>1)
										functions_in_else += " //" + comm[1];
									functions_in_else+="\n";
								}
							}
							
							else if(a[4].equals("-")) {
								// Now in case I have a - sign. I should print the SUBI command
								if(Character.isDigit(b[2].charAt(0))) {
									// If my character in the string is a digit, then I should perform an ADDI operation
									// Here my digit is in b[2]. Ex: a = 1 - a
									functions_in_else += "\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2];
									if(comm.length>1)
										functions_in_else += " //" + comm[1];
									functions_in_else+="\n";
								}
								
								else if(Character.isDigit(b[4].charAt(0))) {
									// Now the same thing but this time if a digit is present in b[4]. EX: a = a - 1
									functions_in_else += "\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4];
									if(comm.length>1)
										functions_in_else += " //" + comm[1];
									functions_in_else+="\n";
								}
								
								else {
									// Otherwise, I am adding two registers. EX: a = a + b
									functions_in_else += "\tSUB X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97);
									if(comm.length>1)
										functions_in_else += " //" + comm[1];
									functions_in_else+="\n";
								}
							}
							}
							catch(ArrayIndexOutOfBoundsException e) {
								continue;
							}
						}
					}
					
					if(is_else == true) {
						// If is else is true, then this means that there is an else condition
						// Then I need to find its functions
						System.out.println("if: ");
						System.out.println((Character.isDigit(comparator2))? "\tCMP X" + (int)(comparator1-97) + ", #" + comparator2 : "\tCMP X" + (int)(comparator1-97) + ", X" + (int)(comparator2-97));
						if(condition.equals(">") || condition.equals(">="))
							System.out.println("\tB.LE else");
						
						else if(condition.equals("<") || condition.equals("<="))
							System.out.println("\tB.GE else");
						
						else
							System.out.println("\tB.NE else");
						
						System.out.print(functions_in_if);
						System.out.print("\tB exit");
						System.out.println("\nelse: ");
						System.out.print(functions_in_else);
						System.out.println("\tB exit");
						System.out.println("exit:\n");
					}
					
					else {
						System.out.println("if: ");
						System.out.println((Character.isDigit(comparator2))? "\tCMP X" + (int)(comparator1-97) + ", #" + comparator2 : "\tCMP X" + (int)(comparator1-97) + ", X" + (int)(comparator2-97));
						if(condition.equals(">") || condition.equals(">="))
							System.out.println("\tB.LE exit");
						
						else if(condition.equals("<") || condition.equals("<="))
							System.out.println("\tB.GE exit");
						
						else
							System.out.println("\tB.NE exit");
						
						System.out.print(functions_in_if);
						System.out.println("exit:\n");
					}
				}
				
				
				
				else if(a[0].equalsIgnoreCase("for")) {
					// Now in case of a for loop.
					// Here there are some things that I am sure of. The conditions will always be in this form: for(int x = smthing; x< or x> than smthing; x++ or x--)
					// So the variable is the same. What will change is what is inside the loop.
					String condition = a[6]; // This will hold the comparator sign < or >
					String what_to_do = a[8].substring(1, 3); // This will hold the operation performed on the variable during the for loop EX: i++ or i--
					System.out.println(((a[4].charAt(0)=='0')? "ADDI X" + (int)(a[5].charAt(0)-97) + " XZR, XZR": "ADDI X" + (int)(a[5].charAt(0)-97) + ", XZR, #" + a[4].charAt(0))); // Here I am initializing the value of the loop
					System.out.println("forloop: ");
					System.out.println("\tCMP X" + (int)(a[5].charAt(0)-97) + ", #" + a[7].charAt(0));
					System.out.println((condition.equals(">") || condition.equals(">="))? "\tB.LE endOfFor" : "\tB.GE endOfFor");
			
					while(!(s=br.readLine()).equals("}")) { // Now Continue looping until you find a closing bracket }
						try {
						String[] b = s.split(" ");
						String[] comm = s.split("//");
						
						if(b[3].equals("+")) {
							if(Character.isDigit(b[2].charAt(0))) {
								// If my character in the string is a digit, then I should perform an ADDI operation
								// Here my digit is in b[2]. Ex: a = 1 + a
								System.out.print("\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else if(Character.isDigit(b[4].charAt(0))) {
								// Now the same thing but this time if a digit is present in b[5]. EX: int a = a + 1
								System.out.print("\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else {
								// Otherwise, I am adding two registers. EX: int a = a + b
								System.out.print("\tADD X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97));
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
						}
						
						else if(a[4].equals("-")) {
							// Now in case I have a - sign. I should print the SUBI command
							if(Character.isDigit(b[2].charAt(0))) {
								// If my character in the string is a digit, then I should perform an ADDI operation
								// Here my digit is in b[2]. Ex: a = 1 - a
								System.out.print("\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else if(Character.isDigit(b[4].charAt(0))) {
								// Now the same thing but this time if a digit is present in b[4]. EX: a = a - 1
								System.out.print("\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else {
								// Otherwise, I am adding two registers. EX: a = a + b
								System.out.print("\tSUB X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97));
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
						}
						}
						catch(ArrayIndexOutOfBoundsException e) {
							continue;
						}
					}
					if(what_to_do.equals("++"))
						System.out.println("\tADDI X" + (int)(a[5].charAt(0)-97) + " X" + (int)(a[5].charAt(0)-97) + ", #1"); // Here I am incrementing the value of the loop in case it was for ex i++
					else
						System.out.println("\tSUBI X" + (int)(a[5].charAt(0)-97) + " X" + (int)(a[5].charAt(0)-97) + ", #1"); // Here I am decrementing the value of the loop in case it was for ex i--
					System.out.println("\tB forloop");
					System.out.println("endOfFor: \n");
				}
				
				else if(a[0].equalsIgnoreCase("while")) {
					String condition = a[2]; // This will hold the comparator sign < or > or ==
					char comparator1 = a[1].charAt(1); // This will hold the first comparator in the if statement
					char comparator2 = a[3].charAt(0); // This will hold the second comparator in the if statement
					System.out.println("while: ");
					System.out.println((Character.isDigit(comparator2))? "\tCMP X" + (int)(comparator1-97) + ", #" + comparator2 : "\tCMP X" + (int)(comparator1-97) + ", X" + (int)(comparator2-97));
					System.out.println((condition.equals(">") || condition.equals(">="))? "\tB.LE exit" : "\tB.GE exit");
					
					while(!(s=br.readLine()).equals("}")) { // Now Continue looping until you find a closing bracket }
						try {
						String[] b = s.split(" ");
						String[] comm = s.split("//");
						
						if(s.equals(comparator1 + "++;")) {
							System.out.println("\tADDI X" + (int)(comparator1-97) + ", X" + (int)(comparator1-97) + ", #1");
						}
						
						else if(s.equals(comparator1 + "--;")) {
							System.out.println("\tSUBI X" + (int)(comparator1-97) + ", X" + (int)(comparator1-97) + ", #1");
						}
						
						else if(b[1].equals("*=")) {
							System.out.println((Character.isDigit(b[2].charAt(0)))? "\tMUL X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[0].charAt(0)-97) + ", #" + b[2].charAt(0) : "\tMUL X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97));
						}
						
						else if(b[3].equals("+")) {
							if(Character.isDigit(b[2].charAt(0))) {
								// If my character in the string is a digit, then I should perform an ADDI operation
								// Here my digit is in b[2]. Ex: a = 1 + a
								System.out.print("\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else if(Character.isDigit(b[4].charAt(0))) {
								// Now the same thing but this time if a digit is present in b[5]. EX: int a = a + 1
								System.out.print("\tADDI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else {
								// Otherwise, I am adding two registers. EX: int a = a + b
								System.out.print("\tADD X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97));
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
						}
						
						else if(a[4].equals("-")) {
							// Now in case I have a - sign. I should print the SUBI command
							if(Character.isDigit(b[2].charAt(0))) {
								// If my character in the string is a digit, then I should perform an ADDI operation
								// Here my digit is in b[2]. Ex: a = 1 - a
								System.out.print("\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97) + ", #" + b[2]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else if(Character.isDigit(b[4].charAt(0))) {
								// Now the same thing but this time if a digit is present in b[4]. EX: a = a - 1
								System.out.print("\tSUBI X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", #" + b[4]);
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
							
							else {
								// Otherwise, I am adding two registers. EX: a = a + b
								System.out.print("\tSUB X" + (int)(b[0].charAt(0)-97) + ", X" + (int)(b[2].charAt(0)-97) + ", X" + (int)(b[4].charAt(0)-97));
								if(comm.length>1)
									System.out.print(" //" + comm[1]);
								System.out.println();
							}
						}
						}
						catch(ArrayIndexOutOfBoundsException e) {
							continue;
						}
					}
					System.out.println("\tB while");
					System.out.println("exit:\n");
				}
			}
			br.close();
			fr.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found, make sure it is in project that you are working in");
		}
		catch(IOException e ) {
			System.out.println("Something went wrong");
		}
	}

}
