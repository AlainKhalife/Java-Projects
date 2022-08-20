package hw4q1;

public class DNode {
	
	private Object info;
	private DNode prev, next;
	
	public DNode(Object o) {
		info = o;
		prev = null;
		next = null;
	}
	
	public Object getInfo() {
		return info;
	}
	
	public void setInfo(Object o) {
		info = o;
	}
	
	public DNode getNext() {
		return next;
	}
	
	public void setNext(DNode n) {
		next = n;
	}
	
	public void setPrev(DNode n) {
		prev = n;
	}
	
	public DNode getPrev() {
		return prev;
	}

}
