package hw5q2;

public class Node {
	
	private Account info;
	private Node next;
	
	public Node(Account acc) {
		info = acc;
		next = null;
	}
	
	public Account getInfo() {
		return info;
	}
	
	public void setInfo(Account acc) {
		info = acc;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node n) {
		next = n;
	}

}
