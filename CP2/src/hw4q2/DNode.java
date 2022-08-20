package hw4q2;

public class DNode {
	
	private Object info;
	private DNode prev, next;
	
	public DNode(int i) {
		info = i;
		prev = null;
		next = null;
	}
	
	public Object getInfo() {
		return info;
	}
	
	public void setInfo(int i) {
		info = i;
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
