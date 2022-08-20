package extra_lab;

import java.util.*;
import java.io.*;

public class Municipality {
	Family[] families;
	static int number_of_families;
	public Municipality() {
		families= new Family[10];
		number_of_families=0;
	}

	public void ensureCapacity() {
		Family[] temp=new Family[families.length*2+1];
		for (int i = 0; i < families.length; i++) {
			temp[i]=families[i];
		}
		families=temp;
	}
	public int readRecords() {
		int families_skipped=0;
		try {
			System.out.println("Reading from records.txt");
			FileReader fr = new FileReader(new File("./src/extra_lab/records.txt"));
			BufferedReader br = new BufferedReader(fr);

			String line="";
			while((line=br.readLine())!=null) {
				try {

					String[] current_family=line.split(",");

					//add parent
					Parent current_p1 = new Parent(current_family[0],Integer.parseInt(current_family[1]),Double.parseDouble(current_family[2]));
					Parent current_p2 = new Parent(current_family[3],Integer.parseInt(current_family[4]),Double.parseDouble(current_family[5]));

					//see how many children there are, create an array with the correct length
					int number_of_current_children = (current_family.length-6)/3;
					Child[] current_children=new Child[number_of_current_children];
					int added_children=0;
					for (int i = 6; i < current_family.length; i=i+3) {
						current_children[added_children]=new Child(current_family[i],Integer.parseInt(current_family[i+1]),Boolean.parseBoolean(current_family[i+2]));
						added_children++;
					}
					//create family
					Family f = new Family(current_p1,current_p2,current_children);

					if (number_of_families==(families.length-1))
						ensureCapacity();

					families[number_of_families]=f;
					number_of_families++;

				}
				catch (NumberFormatException e) {
					families_skipped++;
					continue;
				}
				catch(NullPointerException e) {
					families_skipped++;
					continue;

				} catch(IndexOutOfBoundsException e) {
					families_skipped++;
					continue;
				}
			}

		}
		catch(FileNotFoundException e){
			System.out.println(e);
			System.out.println("record.txt not found");
			return -1;
		}
		finally {
			return families_skipped;
		}
	}


	//when the user wants to add a family manually
	public boolean addFamilyManually() {
		try {
			//add first parent
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the name of the first parent");
			String name_p1 = scan.nextLine();
			System.out.println("Enter the age of the first parent");
			int age_p1=scan.nextInt();
			System.out.println("Enter the salary of the first parent");
			double salary_p1=scan.nextDouble();
			Parent p1_toadd =new Parent(name_p1,age_p1,salary_p1);
			System.out.println("Parent 1 created");
			//add second parent
			scan.nextLine();
			System.out.println("Enter the name of the second parent");
			String name_p2 = scan.nextLine();
			System.out.println("Enter the age of the second parent");
			int age_p2=scan.nextInt();
			System.out.println("Enter the salary of the second parent");
			double salary_p2=scan.nextDouble();
			Parent p2_toadd =new Parent(name_p2,age_p2,salary_p2);
			System.out.println("Parent 2 created");

			scan.nextLine();

			//add children
			System.out.println("How many children does this family have");
			int number_of_children=scan.nextInt();
			if(number_of_children<0) {
				number_of_children=0;
				System.out.println("entered an invalid value, assuming this family has no children");
			}
			scan.nextLine();
			Child[] children_of_family=new Child[number_of_children];
			for (int i = 0; i < number_of_children; i++) {
				System.out.println("Adding child number "+(i+1));
				System.out.println("Enter the name of the child");
				String name_c = scan.nextLine();
				System.out.println("Enter the age of the child");
				int age_c=scan.nextInt();

				scan.nextLine();
				System.out.println("is the child a student? enter yes or anything else...");
				String is_student_c = scan.nextLine();
				boolean is_s_c=false;
				if(is_student_c.equalsIgnoreCase("yes"))
					is_s_c=true;
				children_of_family[i]=new Child(name_c,age_c,is_s_c);
				System.out.println("Child created and added to the family");
			}
			Family f = new Family(p1_toadd,p2_toadd,children_of_family);

			//array of families is full
			if (number_of_families==(families.length-1))
				ensureCapacity();

			families[number_of_families]=f;
			number_of_families++;
			return true;
		}
		catch(InputMismatchException e) {
			System.out.println("Scanned an inalid value");
			return false;
		}
	}

	public boolean divorce() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the name of the parent to leave their family");
			String name_to_divorce = reader.readLine();
			System.out.println("Enter their age");
			int age_to_divorce =Integer.parseInt(reader.readLine());//parse the salary of the parent because the buffered reader reads input as string always
			Parent parent_to_divorce=new Parent(name_to_divorce, age_to_divorce, 0);

			boolean divorced =false;
			boolean found = false;
			for (int i = 0; i < number_of_families; i++) {
				if(families[i].getParent1().equals(parent_to_divorce)) {
					System.out.println("found the parent you want to divorce in this family:");
					System.out.println(families[i]);
					System.out.println("are you sure you want to divorce them?");
					if(reader.readLine().equalsIgnoreCase("yes")) {
						families[i].setParent1(new Parent("no name",0,0));
						divorced=true;
					}
					found=true;
				}
				if(families[i].getParent2().equals(parent_to_divorce)) {
					System.out.println("found the parent you want to divorce in this family:");
					System.out.println(families[i]);
					System.out.println("are you sure you want to divorce them?");
					if(reader.readLine().equalsIgnoreCase("yes")) {
						families[i].setParent2(new Parent("no name",0,0));
						divorced = true;
					}
					found=true;
				}
			}
			if(!found) {
				System.out.println("Could not find that parent");
			}
			return divorced;
		}catch(IOException e) {
			System.out.println("an error occured while read");
			return false;
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input");
			return false;
		}

	}

	public boolean exit() {
		try {
			FileWriter fr = new FileWriter(new File("./src/extra_lab/records.txt"));
			BufferedWriter br = new BufferedWriter(fr);

			for (int i = 0; i <number_of_families; i++) {
				br.write(families[i].familyFormat());
				br.write("\n");
			}
			br.close();
			fr.close();
			return true;
		} catch (IOException e) {
			System.out.println("IO exception");
			return false;
		}

	}

	public void menu() {
		Scanner scan = new Scanner(System.in);

		int mistakes=0;

		while(mistakes<5){
			int choice=0;
			System.out.println("Enter 1. to read records");
			System.out.println("      2. to add a family");
			System.out.println("      3. to divorce a family");
			System.out.println("      4. to list all families");
			System.out.println("      5. to exit");
			try {
				choice = scan.nextInt();
			}catch(NumberFormatException e) {
				System.out.println("Invalid number");
				mistakes++;
				continue;
			}
			switch(choice) {
			case 1:
				mistakes=0;
				int misread=readRecords();
				System.out.println(misread+" family were skipped while reading");
				break;


			case 2:
				mistakes=0;
				if(addFamilyManually()) {
					System.out.println("family added successfully");
				}else {
					System.out.println("could not add family");
				}
				break;

			case 3:
				mistakes=0;
				if(divorce()) {
					System.out.println("family got divorced");
				}else {
					System.out.println("could not divorce family");
				}
				break;
			case 4:
				if(number_of_families==0)
					System.out.println("There are no recorded families");
				for (int i = 0; i < number_of_families; i++) {
					System.out.println();
					System.out.println(families[i]);
				}
				break;

			case 5:
				mistakes=0;
				if(exit()) {
					System.out.println("records saved successfully");
					System.exit(0);
				}else {
					System.out.println("Something went wring while saving");
					System.exit(1);
				}
				break;

			}
		}

	}
	public static void main(String[] args) {
		Municipality m = new Municipality();
		m.menu();
	}
}
