package lab9;

public class test {
	
	public static void main(String[] args){
		Student sd1, sd2, sd3;
		StudentCollection stdc = new StudentCollection(3);
		
		// Creating the student Objects
		sd1 = new Student(201807278, "Alain Khalife", 12);
		sd2 = new Student(222200000, "Someone Unkown", 18);
		sd3 = new Student(451122334, "Someone Rich", 3);
		
		// Adding Students to the Collection
		stdc.add(sd1);
		stdc.add(sd2);
		stdc.add(sd3);
		stdc.delete(201807278);
		
		// Printing out the results
		System.out.println(stdc);
		
	}

}
