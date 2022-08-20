package extra_lab;

public abstract class Person {

	String name;
	int age;
	
	public Person(String name, int age) {
		this.name=name;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean equals(Object obj) {
		try {
			if(((Person)obj).getName().equals(this.name) && ((Person)obj).getAge()==this.age)
				return true;
			else
				return false;
		}catch(ClassCastException e) {
			return false;
		}
		
	}
}
