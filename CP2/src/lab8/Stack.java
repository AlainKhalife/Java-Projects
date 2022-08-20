package lab8;

public class Stack {
	
	private Node top;
	
	public Stack() {
		top = null;
	}
	
	public void push(Object o) {
		Node n = new Node(o);
		if(top == null)
			top = n;
		
		else {
			n.setNext(top);
			top = n;
		}
	}
	
	public Object pop() {
		if(top == null)
			return -99;
		
		else {
			Node current = top;
			top = top.getNext();
			current.setNext(null);
			return current.getInfo();
					
		}
	}
	
	public Object peek() {
		if(top == null)
			return -99;
		
		else
			return top.getInfo();
	}
}
