package extra_lab;

public class Parent extends Person {

	double salary;
	
	public Parent(String name, int age ,double salary) {
		super(name,age);
		this.salary=salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String toString() {
		return "My name is "+name+", my age is "+age+", and my salary is "+salary;
	}
	public String parentFormat() {
		return name+","+age+","+salary;
	}

}
