package lab9;

public class Student {
	
	private int ID;
	private String name;
	private int credits;
	
	public Student(int ID, String name, int credits){
		this.ID = ID;
		this.name = name;
		this.credits = credits;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String str = "ID: " + ID + "\t" + "Name: " + name + "\t" + "Number of credits: " + credits;
		return str;
	}

}
