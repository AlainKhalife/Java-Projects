package lab1;

import java.util.*;

public class Country {
	private String name;
	private Person[] people;
	private static int earth_population;

	public Country(String name) {
		this.name = name;
		this.people = new Person[1];//the country is initialized as empty of any people but can fit 10 people
	}

	//getters and setters
	public Person[] getPeople() {
		return people;
	}
	public void setPeople(Person[] people) {
		this.people = people;
	}
	public static int getEarth_population() {
		return earth_population;
	}
	public static void setEarth_population(int earth_population) {
		Country.earth_population = earth_population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//make sure that there is enough place when adding a person to the array people
	public void ensureCapacity() {

		Person [] temp = new Person[people.length*2+1];// create a new bigger array
		for (int i = 0; i < people.length; i++) {//copy the elements of people into the new array
			temp[i]=people[i];
		}
		people=temp;//let people point to that new array of bigger size
	}

	//the static methods
	public static void increment() {
		earth_population++;
	}
	public static void decrement() {
		earth_population--;
	}
	public void print() {
		System.out.println("the earth population is: "+earth_population);
	}

	//the nonstatic method
	public void createPerson() {
		Scanner scan = new Scanner(System.in);
		try {//if the user inputs a string instead of an int in age

			//get the information of the person we are creating
			System.out.println("creating person...");
			System.out.println("Enter the name of the person");
			String create_name = scan.nextLine();
			System.out.println("Enter the nationality of the person");
			String create_nationality = scan.nextLine();
			System.out.println("Enter the age of the person");
			int create_age = scan.nextInt();

			//create instance of the eprson
			Person created_person = new Person(create_name,create_nationality,create_age);

			if (earth_population == people.length) {
				ensureCapacity();
			}
			people[earth_population]=created_person;
			increment();
			System.out.println(create_name+" has been created");

		}catch(InputMismatchException e ) {
			System.out.println("You entered an invalid value");
		}
	}

	public void kill() {
		Scanner scan = new Scanner(System.in);	
		try {
			System.out.println("Enter the name of the person you want to kill");
			String kill_name=scan.nextLine();

			int index =-1; //index of the person to kill
			for (int i = 0; i < earth_population; i++) {
				if(people[i].getName().equals(kill_name)) {
					index=i; //save the index when found
				}
			}
			if(index ==-1 ) {//the person is not found
				System.out.println("the person could not be found");
			}else {
				//the person is found
				//swap the last person in the array of people with the person you want to kill
				people[index]=people[earth_population-1];
				people[earth_population-1]=null;
				decrement();
				System.out.println(kill_name+" died");
			}
		}catch (InputMismatchException e) {
			System.out.println("You enetered an invalid value");
		}

	}
}
