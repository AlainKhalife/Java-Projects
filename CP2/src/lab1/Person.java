package lab1;

public class Person {
	private String name;
	private String nationality;
	private int age;


	public Person(String name, String nationality, int age) {
		//constructor
		//this.name refers to the name variable of the class while name alone refers to the parameter 
		this.name = name;
		this.nationality = nationality;
		this.age = age;
	}

	public String toString() {
		//override to String method
		return "hello! My name is " + name + ", I'm from " + nationality + ", and I am " + age + " years old.";
	}

	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


}
