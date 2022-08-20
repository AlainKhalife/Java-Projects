package hw1;

public class Employee {
	
	protected String name, date_of_recruitment;
	protected double salary;
	private static int size = 0;
	
	public Employee(String namee, String dateOfRecruitment, double salaryy) {
		name = namee;
		date_of_recruitment = dateOfRecruitment;
		salary = salaryy;
		size++;
	}
	
	public static int getSize() {
		return size;
	}
	
	public String getname() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double percentage) {
		salary = salary + salary*(percentage/100);
	}
	
	public String toString() {
		String ans = "Employee Details: \nName: " + name +"\nDate Of Recruitment " + date_of_recruitment + "\nSalary: " + salary;
		return ans;
	}

}
