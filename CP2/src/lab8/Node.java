package lab8;

public class Node {
	
	private Object info;
	private Node next;
	
	public Node(Object o) {
		info = o;
		next = null;
	}
	
	public Object getInfo() {
		return info;
	}
	
	public void setInfo(Object o) {
		info = o;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node n) {
		next = n;
	}

}
