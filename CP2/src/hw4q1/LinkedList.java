package hw4q1;

public class LinkedList {
	
	private Node header;
	
	public LinkedList() {
		header = null;
	}
	
	public Node getHeader() {
		return header;
	}
	
	// Method add that will add a node to the list
	public void addString(String s) {
		Node n = new Node(s);
		Node current = header;
		
		if(header == null)
			header = n;
		
		else {
			while(current.getNext()!= null)
				current = current.getNext();
			
			current.setNext(n);
		}
	}
	
	// Method size that will return the size of the list
	public int size() {
		int count = 1;
		if(header == null)
			return 0;
		
		else {
			Node current = header;
			while(current.getNext()!= null) {
				current = current.getNext();
				count++;
			}
			
			return count;
		}
	}
	
	// Method delete that will delete a node from the list 
	public boolean delete(int i) {
		if(i<0 || i> size()-1)
			return false;
		
		else {
			Node current = header;
			if(i==0) {
				header = header.getNext();
				current.setNext(null);
				return true;
			}
			
			else {
				for(int j = 0; j<i-1; j++)
					current = current.getNext();
				
				Node temp = current.getNext();
				current.setNext(current.getNext().getNext());
				temp.setNext(null);
				return true;
			}
		}
	}
	
	// This will return the content of a specific node index
	public Object get(int i) {
		if(i<0 || i> size()-1)
			return null;
		
		else {
			Node current = header;
			for(int j=0; j<i; j++)
				current = current.getNext();
			
			return current.getInfo();
		}
	}
	
	// This method will return a pointer the a certain node in the list
	public Node getNode(int i) {
		if(i<0 || i> size()-1)
			return null;
		
		else {
			Node current = header;
			for(int j=0; j<i; j++)
				current = current.getNext();
			
			return current;
		}
	}
	

	public void printReverse() {
		// This method prints the elements of the list in reverse order without modifying the original list order
		Node current = header;
		
		// Moving current to the end of the list
		for(int i = 0; i<size(); i++)
			current = current.getNext();
		
		// This for loop prints the elements inside the array in reverse
		for(int j = (size()-1); j>=0; j--)
			System.out.print(get(j) + " ");
		
	}
	
	// This methods returns the elements at odd indicies 
	public void returnOdd(LinkedList l) {
		Node current = header;
		if(l.size() == 1)
			System.out.print("Empty");
		
		else
			for(int i = 1; i<size(); i = (i+2)) 
				System.out.print(l.get(i) + " ");
			
	}
	
	public void removeDup() {
		// Removing duplicates
		int index = 0;
		while(index<size()) {
			for(int i = index+1; i<size(); i++) {
				try {
				if(Double.parseDouble(((String) get(index))) == Double.parseDouble((String) get(i))) {
						/*
						 * Here i am checking if an elements in the list is the same in its double type
						 * EX: If in my list I have [monkey, 2, train, 2.0, car]
						 * 2 == 2.0, So they are the same.
						 * Therefore one of them will be removed.
						 */
					        double temp1 = Math.ceil(Double.parseDouble(((String) get(index))));
							double check = Math.abs(temp1 - Double.parseDouble((String) get(i)));
							/*
							 * What I am doing here is the following:
							 * I used two double variable temp1 and check
							 * In temp1 I am storing the ceil of the first value and in check I am storing the absolute value of the difference between the first and the second value
							 * Why ?
							 * because suppose in my list I had [2.0, 2.0, 2.1, 2.1]
							 * I just want to delete 2.0 and convert the one remaining to an integer 2 because they are the same
							 * But for 2.1, although they are the same, I don't want to convert one of the to an integer.
							 * By using Math.ceil(2.1) I get 3.0, then I do Math.abs(3.0-2.1) = 0.9 > 0 which I stored in check
							 * This way I know that i do not have a double equivalent to an integer
							 */
							
							if(check==0) {
							String temp = "" + (int) Double.parseDouble((String) get(index)); // Here I am just converting the double to int and storing it an a string
							getNode(index).setInfo(temp);
							delete(i);
							i = index;
							}
							
							else {
								delete(i);
								i = index;
							}
					}
				}
				    // If I catch a number format exception this means that I the element cannot be parsed into a  number and therefore must be treated as a string
					catch(NumberFormatException e) {
						if(((String) get(index)).equals((String) get(i))) {
							delete(i);
							i = index; // i = index because i want to recheck from the beginning of the list for any duplicates
						}
						continue;
					}
					catch(ClassCastException e) {
						continue;
					}
				}
			index++;
			}
			
		
		// Printing the result
		System.out.println("\nThe list with removed duplicate is: ");
		for(int i =0; i<size(); i++)
			System.out.print(get(i) + " ");

		System.out.println("\n");
	}
	
	public boolean insert(Object o, int i) {
		if(i<0 || i>size())
			return false;
		
		else {
			Node n = new Node(o);
			if(i==0) {
				n.setNext(header);
				header = n;
				return true;
			}
			
			else {
				Node current = header;
				for(int j = 0; j<i-1; j++)
					current = current.getNext();
				
				n.setNext(current.getNext());
				current.setNext(n);
				return true;
			}
		}
	}
	
	public LinkedList append(LinkedList l1, LinkedList l2) {
		if(l1==null || l1.getHeader()==null)
			return l2;
		
		else if(l2 == null || l2.getHeader() == null)
			return l1;
		
		else {
			Node current = l1.getHeader();
			while(current.getNext() != null)
				current = current.getNext();
			
			current.setNext(l2.getHeader());
			l2=null;
			return l1;
		}
	}

}
