package hw4;

public class Node {
	
	private String info;
	private int weight;
	private Node next;
	
	public Node(String vertexname) {
		info = vertexname;
		weight = -99;
		next = null;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String vname) {
		info = vname;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getNext() {
		return next;
	}
	
	public void setNext(Node n) {
		next = n;
	}

}
