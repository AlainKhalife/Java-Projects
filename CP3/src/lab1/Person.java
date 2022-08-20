package lab1;

public class Person {
	
	private boolean is_engaged; // This would tell me if this person is engaged or not
	private Person[] preference = new Person[3]; // This array will store the list of preference of the person
	private Person is_engaged_to; // This is used to determine the person he/she is engaged to.
	private int top_preference = 0; // This integer will be used to point to the top preference person on the list stored in the array
	private String name = null; // The name of the Person
	
	public Person(String name) {
		is_engaged = false;
		is_engaged_to = null;
		this.name = name;
	}
	
	 
	public void set_preferences(Person p1, Person p2, Person p3) {
		// I will consider that preference[0] is the top preference moving down to preference[3] as the lowest
		preference[0] = p1;
		preference[1] = p2;
		preference[2] = p3;
	}
	
	public void decreaseTopPref() {
		top_preference++;
	}
	
	public Person getTopPreference() {
		return preference[top_preference];
	}
	
	public boolean is_engaged() {
		return is_engaged;
	}
	public void setIs_engaged(boolean is_engaged) {
		this.is_engaged = is_engaged;
	}
	public Person[] getPreference() {
		return preference;
	}
	public void setPreference(Person[] preference) {
		this.preference = preference;
	}
	public void setIsEngagedTo(Person p) {
		is_engaged_to = p;
	}
	public Person getPersonEngagedTo() {
		return is_engaged_to;
	}
	
	public String toString() {
		return name;
	}

}
