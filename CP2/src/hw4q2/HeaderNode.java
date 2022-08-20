package hw4q2;

public class HeaderNode {
	
	private int count;
	private DNode first, last;
	
	public HeaderNode() {
		count = 0;
		first = null;
		last = null;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int i) {
		count = i;
	}
	
	public DNode getFirst() {
		return first;
	}
	
	public void setFirst(DNode f) {
		first = f;
	}
	
	public DNode getLast() {
		return last;
	}
	
	public void setLast(DNode l) {
		last = l;
	}
	
	public void increment() {
		count++;
	}
	
	public void decrement() {
		count--;
	}

}
