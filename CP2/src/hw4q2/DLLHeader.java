package hw4q2;

/* 
 * Hello :)
 * I'm sorry for the lack of comments in my code, its because most of the ways that I though of when I was designing the code couldn't be explained with just words
 * Some methods like insert, reverse , split. I couldn't explain them with comments on how exactly I did them. Most of the things require drawings to explain
 * how i dealt with pointers and positions of nodes.
 * But I hope that my code is as neat and good as it should.
 * Thank you
 * Alain 
 */

public class DLLHeader {
	
	private HeaderNode header;
	
	public DLLHeader() {
		header = new HeaderNode();
	}
	
	// This method add elements to the end of the list
	public void add(int i) {
		DNode n = new DNode(i);
		if(header.getCount() == 0) {
			header.setFirst(n);
			header.setLast(n);
			header.increment();
		}
		
		else {
			DNode current = header.getLast();
			current.setNext(n);
			header.setLast(n);
			n.setPrev(current);
			header.increment();
		}
	}
	
	// This method returns the size of the list
	public int size() {
		return header.getCount();
	}
	
	// This method deletes an element at a specified index
	public boolean delete(int index) {
		if(index<0 || index>header.getCount()-1 || header == null)
			return false;
		
		else if(index == 0) {
			DNode current = header.getFirst();
			header.setFirst(current.getNext());
			current.setNext(null);
			header.decrement();
			return true;
		}
		
		else if(index == (header.getCount()-1)) {
			DNode current = header.getFirst();
			while(current.getNext()!=null)
				current = current.getNext();
			
			header.setLast(current.getPrev());
			current.getPrev().setNext(null);
			current.setPrev(null);
			header.decrement();
			return true;
		}
		
		else {
			DNode current = header.getFirst();
			DNode todelete = header.getFirst();
			for(int i =0; i<index-1; i++)
				current = current.getNext();
			
			for(int i = 0; i<index; i++)
				todelete = todelete.getNext();
			
			current.setNext(current.getNext().getNext());
			current.getNext().getNext().setPrev(current);
			todelete.setNext(null);
			todelete.setPrev(null);
			header.decrement();
			return true;
			
		}
	}
	
	public boolean insert(int i, int index) {
		if(index<0 || index>header.getCount())
			return false;
		
		else if(index == 0) {
			DNode n = new DNode(i);
			n.setNext(header.getFirst());
			header.getFirst().setPrev(n);
			header.setFirst(n);
			header.increment();
			return true;
		}
		
		else if(index == header.getCount()) {
			DNode n = new DNode(i);
			n.setPrev(header.getLast());
			header.getLast().setNext(n);
			header.setLast(n);
			header.increment();
			return true;
		}
		
		else {
			DNode n = new DNode(i);
			DNode current = header.getFirst();
			for(int j =0; j<index-1; j++)
				current = current.getNext();
			
			current.getNext().setPrev(n);
			n.setNext(current.getNext());
			current.setNext(n);
			n.setPrev(current);
			header.increment();
			return true;
		}
	}
	
	// This method returns the integer in a specified location in a list
	public Object get(int i) {
		if(i<0 || i>(header.getCount()-1))
			return -999;
		
		else if(i == 0)
			return header.getFirst().getInfo();
		
		else if(i == header.getCount()-1)
			return header.getLast().getInfo();
		
		else {
			DNode current = header.getFirst();
			for(int j =0; j<i; j++)
				current = current.getNext();
			
			return current.getInfo();
		}
	}
	
	// This method displays all the elements in the list
	public void display() { 
		for(int i = 0; i<header.getCount(); i++)
			System.out.print(get(i) + " ");
	}
	
	// This method reverses the order of the elements in the list
	public void reverse() {

		DNode current = header.getLast();
		
		while(current.getPrev()!=null) {
			current.setNext(current.getPrev());
			current.setPrev(current.getNext());
			current = current.getNext();
		}

		DNode switch_last = header.getFirst();
		DNode switch_first = header.getLast();
		header.setLast(switch_last);
		header.setFirst(switch_first);

	}
	
	// This method calculates the sum of all the integers in the list
	public int sum() {
		int sum = 0;
		DNode current = header.getFirst();
		for(int i = 0; i<size(); i++) {
			sum = sum + (int) current.getInfo();
			current = current.getNext();
		}
		return sum;
	}
	
	// This method splits the list into two other lists at a specified location and return an array containing a pointer to each list
	public DLLHeader[] split(int i) {
		int size = 0; // This will be used to set the size of l1 and l2
		DLLHeader[] array = new DLLHeader[2];
		DLLHeader l1 = new DLLHeader();
		DLLHeader l2 = new DLLHeader();
		DNode current = header.getFirst();
		
		for(int j = 0; j<i-1; j++) {
			current = current.getNext();
			size++;
		}
		
		// what I am doing here is making the first field of headerNode of l1 point to the first node on the list
		// and making the last field in headerNode of l1 point to the node at current which is at i-1
		// And I am setting the size of my list to size+1 because I started at index 0
		l1.header.setFirst(header.getFirst());
		l1.header.setLast(current);
		l1.header.setCount(size+1);
		
		// Here and I am doing the same for l2
		// I am making the first field of headerNode of l2 point to the node at i and the last field point to the last node in the list
		current = current.getNext();
		l2.header.setFirst(current);
		l2.header.setLast(header.getLast());
		l2.header.setCount(size()-(size+1));
		
		array[0] = l1; // putting the pointer of l1 in array[0]
		array[1] = l2; // putting the pointer of l2 in array[1]
		return array;
	}

}
