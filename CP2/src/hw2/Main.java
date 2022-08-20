package hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Space collection = new Space();
		int choice = 0; // For the main menu
		Scanner scan = new Scanner(System.in);
		
		while(choice != 6) {
		System.out.println("1. Add a shape\n2. Delete a shape\n3. Compute Area and Perimeter\n4. Display all\n5. Read from file\n6. Exit");
		System.out.print("----------------\nChoice: ");
		
		try {  // Try Block to catch an input mismatch for the main menu. Ex: If the user enters any non numerical value;
			
		choice = scan.nextInt();
		
			switch(choice) {
			
			case 1:
				String shape = ""; // For the sub menu of option 1
				// This loop is to stay inside the sub menu of option 1
				while(shape.equalsIgnoreCase("d") == false) {
				System.out.print("\nA. Add a Circle\nB. Add a Square\nC. Add a Triangle\nD. Return to main menu\n------------------\nEnter shape(A, B, C or D): ");
				shape = scan.next();
				
				
				int correct = -1;   // This is used in order to maintain the inner while loop and to only exit when the user enters "D"
				
				while(correct!=0) {
					
				try {  // Try block to catch any input mismatch inside the sub menu
						
				if(shape.equalsIgnoreCase("a")) {
					double radius, xcor, ycor;
					System.out.print("\nEnter the radius of the circle: ");
					radius = scan.nextDouble();
					System.out.print("\nEnter the x-coordinate of this circle: ");
					xcor = scan.nextDouble();
					System.out.print("\nEnter the y-coordinate of this circle: ");
					ycor = scan.nextDouble();
					System.out.print("\nEnter the color of this circle: ");
					String color = scan.next();
					Shape circle = new Circle(xcor, ycor, radius, color);
					collection.add(circle);
					System.out.println("\nCircle added\n");
					correct =0;
				}
				
				else if(shape.equalsIgnoreCase("b")) {
					double length, xcor, ycor;
					System.out.print("\nEnter the side length of the square: ");
					length = scan.nextDouble();
					System.out.print("\nEnter the x-coordinate of this square: ");
					xcor = scan.nextDouble();
					System.out.print("\nEnter the y-coordinate of this square: ");
					ycor = scan.nextDouble();
					System.out.print("\nEnter the color of this square: ");
					String color = scan.next();
					Shape square = new Square(xcor, ycor, length, color);
					collection.add(square);
					System.out.println("\nSquare added\n");
					correct = 0;
				}
				
				else if(shape.equalsIgnoreCase("c")) {
					double length1, length2, base, xcor, ycor;
					System.out.print("\nEnter the length of the base of the triangle: ");
					base = scan.nextDouble();
					System.out.print("\nEnter the length of one side of the triangle: ");
					length1 = scan.nextDouble();
					System.out.print("\nEnter the length of the other side of the triangle: ");
					length2 = scan.nextDouble();
					System.out.print("\nEnter the x-coordinate of this triangle: ");
					xcor = scan.nextDouble();
					System.out.print("\nEnter the y-coordinate of this triangle: ");
					ycor = scan.nextDouble();
					System.out.print("\nEnter the color of this triangle: ");
					String color = scan.next();
					
					// If all the length are equal, i'm creating a new Equilateral triangle
					if(base == length1 && length1 == length2) {
						Shape triangle= new EquilateralTriangle(xcor, ycor, base, length1, length2, color);
						collection.add(triangle);
						System.out.println("\nEquilateral Triangle added\n");
						correct = 0;
					}
					
					// Else i'm creating a new triangle
					else {
					Shape triangle= new Triangle(xcor, ycor, base, length1, length2, color);
					collection.add(triangle);
					System.out.println("\nTriangle added\n");
					correct = 0;
					}
				}
				
				else if(shape.equalsIgnoreCase("d")){
					System.out.println();
					correct = 0;
				}
				
				else {
					System.out.println("\nInvalid choice");
					correct = 0;
				}
					}
					
					catch(InputMismatchException e) {
						System.out.println("\n-------------------------------\nOops, you entered an invalid value\n-------------------------------\n");
						scan.next();
					}
				}
				
				
				}
				break;
				
			case 2:
				System.out.print("\nEnter the x-coordinates of the shape: ");
				double xcor = scan.nextDouble();
				System.out.print("\nEnter the y-coordinates of the shape: ");
				double ycor = scan.nextDouble();
				
				if(collection.searchByCoordinates(xcor, ycor)<0) // To check if the shapes exit
					System.out.println("\nNo Shapes found\n");
				
				
				else {
				int allDeleted = 1; // This variable is used in order to maintain the delete loop.
				while(allDeleted>0) {
					collection.delete(xcor, ycor);
					allDeleted = collection.searchByCoordinates(xcor, ycor);
					/* Since the searchByCoordinates method return a positive integer if the shape is found.
					   I'm letting the loop run and keep on deleting shapes as long as it keep finding one with the same
					   x and y coordinates.
					   Once it returns a negative value. It will exit the loop  */
				}
				
				System.out.println("\nAll Shapes of the following coordinates: \nx-coordinate: " + xcor +"\ny-coordinate: " + ycor + "\nHave been deleted\n");
				}
				break;
				
				
			case 3:
				System.out.print("\nEnter the x-coordinates of the shape: ");
				double xcoor = scan.nextDouble();
				System.out.print("\nEnter the y-coordinates of the shape: ");
				double ycoor = scan.nextDouble();
				
				if(collection.searchByCoordinates(xcoor, ycoor)<0) // To check if the shapes exit
					System.out.println("\nNo Shapes found\n");
				
				else {
					int position = collection.searchByCoordinates(xcoor, ycoor); // To get the position of the shape in the array
					double area = collection.search(position).area();
					double perimeter = collection.search(position).perimeter();
					
					System.out.println(collection.search(position).toString() + "\nArea: " + area + "\nPerimeter: " + perimeter +"\n");
				}
				break;
				
				
			case 4:
				if(collection.getNumberOfShapes()==0)
					System.out.println("\nNo shape exists\n");
				
				else {
				System.out.println("\nThese are all the shapes: \n");
				collection.displayAll();
				}
				break;
				
			
				
			case 5:
				System.out.println("\nYou are about to read from a file, please enter the path of the file: ");
				System.out.println("REMARK: if the file is not found, you need to change the backslash \\ like this /\n");
				System.out.println("Example: C:\\User\\Desktop\\myfile  should be: C:/User/Desktop/myfile");
				System.out.println("----------------------------------\nIMPORTANT");
				System.out.println("You should seperate each element by a coma and a space \", \"");
			    System.out.println("File format should be in this form: ");
			    System.out.println("circle, red, 0, 1\ncircle, green, 4, 2\nequilateral triangle, pink, -1, -3\n");
				System.out.println("Enter the file path:");
				try {
					
				// Had To use the inputStreamReader since the scanner class was not fully scanning the string	
				InputStreamReader sr = new InputStreamReader(System.in);
				BufferedReader br1 = new BufferedReader(sr);
				
				String path = br1.readLine(); // String for the path the user enters
				
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				
				String s = null; // This string is used in used to store the first line read by the buffered reader
				int counter = 0; // This counter is to count how many shapes couldn't be created
				
				while((s = br.readLine()) != null) {
					try {
					String [] a = s.split(", ");
					if(a[0].equalsIgnoreCase("circle")) {
						Shape circle = new Circle(Integer.parseInt(a[2]), Integer.parseInt(a[3]), 1, a[1]);
						collection.add(circle);
					}
					
					else if(a[0].equalsIgnoreCase("square")) {
						Shape square = new Square(Integer.parseInt(a[2]), Integer.parseInt(a[3]), 5, a[1]);
						collection.add(square);
					}
					
					else if(a[0].equalsIgnoreCase("triangle")) {
						Shape triangle = new Triangle(Integer.parseInt(a[2]), Integer.parseInt(a[3]), 3, 4, 5, a[1]);
						collection.add(triangle);
					}
					
					else if(a[0].equalsIgnoreCase("equilateral triangle")) {
						Shape eqtriangle = new EquilateralTriangle(Integer.parseInt(a[2]), Integer.parseInt(a[3]), 5, 5, 5, a[1]);
						collection.add(eqtriangle);
					}
					
					else {
						counter++;
					}
					}
					
					
					// Catching any Number Format Exception during parsing
	 				catch(NumberFormatException e) {
	 					counter++;
	 					continue;
	 				}
					
					}
				
				// If counter stays at 0, this means that all the shapes have been succesfully created
				if(counter == 0)
				    System.out.println("\nAll shapes created\n");
				
				else
				// Used the conditional operator just for the sake of correct spellings :)
					
				System.out.println("\n" + counter + ((counter == 1)? " line":" lines") + " in the input file" + ((counter == 1)? " has a":" have") + " wrong synthax.");
				System.out.println(((counter == 1)? "It has":"They have") + " been skipped\nPlease follow the correct synthax given in the example");
				System.out.println("All remaining shapes have been succesfully created\n");
				
				br.close();
				fr.close();
				}
				
				// Catching any IOExceptions
				catch(IOException e) {
					System.out.println("\nFile not found\n");
				}
				
				// Catching any Number Format Exception during parsing
 				catch(NumberFormatException e) {
 					System.out.println("\nThere is something wrong in a line in the file you gave us\nCheck the values of the coordinates or the order of the file\n");
 					continue;
 				}
				break;
				
				
				
			case 6:
				
				// Saving all the shapes in an output text file inside the workspace project folder
				try {
					File f = new File("output.txt");
					FileWriter fw = new FileWriter("output.txt");
					BufferedWriter br = new BufferedWriter(fw);
					
					// Looping through the array to save each shape
					for(int i = 0; i<collection.getNumberOfShapes(); i++) {
						if(collection.getShape(i).getshape().equalsIgnoreCase("circle"))
						br.write(collection.getShape(i).getshape() + ", " + collection.getShape(i).color + ", " + collection.getShape(i).getXCoordinate() + ", " + collection.getShape(i).getYCoordinate() + "\n");
					}
					
					br.close();
					fw.close();
				} 
				
				catch (IOException e) {
					System.out.println("Oops, Something went wrong");
				}
				catch(NullPointerException e) {
					System.out.println("No shapes exist to save");
				}
				
				System.out.println("\nEnding Program ...\nAll circle shapes have been saved in output.txt\nin your workspace project folder");
				System.out.println("Refresh the workspace to view the output file");
				System.exit(0);
				break;
				
				
				
			default:
				System.out.println("\nInvalid Choice\n");
				break;
				
			}
		}
		
		
		
		// Catching the input mismatch exception for the main menu
		
		catch(InputMismatchException e) {
			System.out.println("\nInvalid choice, enter again\n");
			choice=0;
			scan.next();
		}
		
		
	}
  }
}	
