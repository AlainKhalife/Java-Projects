package hw4q1;

public class DoublyLinkedList {
	
	private DNode header;
	
	public DoublyLinkedList() {
		header = null;
	}
	
	public void add(Object o) {
		
		DNode n = new DNode(o);
		if(header == null)
			header = n;
		
		else {
			DNode current = header;
			while(current.getNext()!=null)
				current = current .getNext();
			
			n.setPrev(current);
			current.setNext(n);
		}
	}
	
	public int size() {
		int count = 1;
		if(header == null)
			return count;
		
		else {
			DNode current = header;
			while(current.getNext()!= null) {
				current = current.getNext();
				count++;
			}
			
			return count;
		}
	}
	
	public void displayAll() {
		/* This method displays all the elements in the linked list
		 */
		DNode current = header;
		for(int i =0; i<size(); i++) {
			System.out.print(current.getInfo() + " ");
			current = current.getNext();
		}
	}
	
	public DNode delete(int i) {
		if(header ==null || i<0 || i>size()-1)
			return null;
		
		else if(i==0) {
			DNode current = header;
			if(size()==1) {
				header = null;
				return current;
			}
			
			else {
				header = header.getNext();
				current.setNext(null);
				header.setPrev(null);
				return current;
			}
		}
		
		else {
			DNode current = header;
			for(int j=0; j<i; j++)
				current = current.getNext();
			
			DNode temp = current;
			current.getPrev().setNext(current.getNext());
			
			if(current.getNext()!=null) {
				current.getNext().setPrev(current.getPrev());
				temp.setNext(null);
			}
			temp.setPrev(null);
			return temp;
		}
	}

}
