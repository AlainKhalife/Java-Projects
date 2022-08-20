package hw1;

public class Staff extends Employee {
	
	public Staff(String name, String dateofrec, double salary) {
		super(name, dateofrec, salary);
	}
	
	
	public String toString() {
		String ans = super.toString() + "\nType: Staff\n";
		return ans;
	}

}
