package extra_lab;

public class Child extends Person{
	boolean isStudent;
	public Child(String name, int age, boolean isStudent) {
		super(name,age);
		this.isStudent=isStudent;
	}
	public boolean isStudent() {
		return isStudent;
	}
	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	public String toString() {
		return isStudent? "Hi! my name is "+name+", I am "+age+" years old and I go to school":"Hi! my name is "+name+", I am "+age+" years old and I don't go to school";
	}
	public String childFormat() {
		return name+","+age+","+isStudent;
	}
}
