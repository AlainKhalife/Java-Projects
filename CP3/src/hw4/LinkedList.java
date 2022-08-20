package hw4;

public class LinkedList {
	
	private Node header;
	
	public LinkedList() {
		header = null;
	}
	
	public Node getHeader() {
		return header;
	}
	
	// Method add that will add a node to the list
	public void add(String vertex) {
		Node n = new Node(vertex);
		Node current = header;
		
		if(header == null)
			header = n;
		
		else {
			while(current.getNext()!= null)
				current = current.getNext();
			
			current.setNext(n);
		}
	}
	
	public void addWithWeight(String vertex, int weight) {
		Node n = new Node(vertex);
		n.setWeight(weight);
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
	
	// Method delete that will delete a node from the list at a given index
	public boolean deleteAtIndex(int i) {
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
	
	// Method delete that will delete a node from the list at a given index
	public boolean delete(String vertex) {
		Node current = header;
		if(header==null)
			return false;
		
		if(size()==1 && header.getInfo().equals(vertex)) {
			header=null;
			return true;
		}
		
		if(size()>1 && header.getInfo().equals(vertex)) {
			header = current.getNext();
			current.setNext(null);
			return true;
		}

		while(current.getNext()!=null && !current.getNext().getInfo().equals(vertex)) {
			if(current.getInfo().equals(vertex))
				break;
			
			else {
			current = current.getNext();
			}
		}

		Node temp = current.getNext();
		current.setNext(current.getNext().getNext());
		temp.setNext(null);
		return true;
	}
	
	// This will return the content of a specific node index
	public String get(int i) {
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
	
	public boolean insert(String vertex, int i) {
		if(i<0 || i>size())
			return false;
		
		else {
			Node n = new Node(vertex);
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
	
	public boolean contains(String vertex) {
		for(int i=0; i<size(); i++) {
			if(get(i).equals(vertex)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Node find(String vname) {
		Node current = header;
		for(int i=0; i<size(); i++) {
			if(current.getInfo().equals(vname)) {
				return current;
			}
			current=current.getNext();
		}
		return null;
	}
	
	public String toString() {
		String ans = "";
		Node current = header;
		for(int i=0; i<size(); i++) {
			if(i==size()-1) {
				ans+=current.getInfo();
			}
			else
			ans += current.getInfo()+"-->";
			
			current=current.getNext();
		}
		return ans;
	}
	
	public String printReverse() {
		String ans = "";
		Node current = header;
		
		for(int i=size()-1; i>=0; i--) {
			current = getNode(i);
			if(i<=0) {
				ans+=current.getInfo();
			}
			else
			ans += current.getInfo()+"-->";
			
			current=current.getNext();
		}
		return ans;
	}

}
