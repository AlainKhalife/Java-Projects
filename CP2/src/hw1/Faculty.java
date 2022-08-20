package hw1;

import java.util.Scanner;

public class Faculty extends Employee{
	
	private final double bonus = 2;
	private int teachingLoad;
	Scanner scan = new Scanner(System.in);
	
	public Faculty(String name, String dateofrec, double salary) {
		super(name, dateofrec, salary);
		System.out.println("How many hours does the faculty work per week ?");
		double hoursperweek = scan.nextDouble();
		teachingLoad = (int) Math.ceil(hoursperweek);
	}
	
	
	public void raiseSalary(double percent) {
		super.raiseSalary(percent);
		salary = salary + salary*(bonus/100);
	}
	
	public String toString() {
		String ans = super.toString() + "\nTeaching Load: " + teachingLoad + " credits\nType: Faculty\n";
		return ans;
	}

}
