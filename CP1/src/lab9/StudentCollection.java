package lab9;

public class StudentCollection {
	
	private Student[] collection;
	private int count;
	
	public StudentCollection(int init){
		collection = new Student[init];
		count =0;
	}
	
	// Increase Size of the array
	
	public void increaseSize(){
		Student[] temp = new Student[count*2+1];
		for(int i = 0; i<count; i++)
			temp[i] = collection[i];
		
		collection = temp;
	}
	
	
	//Add a Student
	
	public void add(Student item){
		if(count == collection.length)
			increaseSize();
		
		collection[count] = item;
		count++;
	}
	
	//Search for Student by ID
	
	public int SearchId(int IDD){
		for(int i =0; i<count; i++)
			if(IDD == collection[i].getID())
				return i;
		
		return -1;
	}
	
	// Return a Student
	public Student Search(int idd){
		for(int i = 0; i<count; i++)
			if(idd == collection[i].getID())
				return collection[i];
		
		return null;
	}
	
	// Delete a student
	
	public boolean delete(int DD){
		int loc = SearchId(DD);
		if(loc<0)
			return false;
		
		else{
			count--;
			collection[loc] = collection[count];
			collection[count] = null;
			return true;
		}
	}
	
	// To string method
	public String toString(){
		String str = "This collection has the following Students: \n";
		for(int i = 0; i<count; i++){
			str += collection[i].toString() + "\n";
		}
		return str;
	}

}
