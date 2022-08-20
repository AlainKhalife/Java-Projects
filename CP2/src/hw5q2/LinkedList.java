package hw5q2;

// The linked list class that will hold the accounts of the users
	
public class LinkedList {

	private Node header;
	
	public LinkedList() {
		header = null;
	}
	
	public Node getHeader() {
		return header;
	}
	
	// Method add that will add a node to the list
	public void add(Account s) {
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
	public Account get(int i) {
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

	
	public boolean insert(Account o, int i) {
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

}
