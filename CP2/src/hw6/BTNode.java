package hw6;

public class BTNode {
	
	private BTNode left, right;
	private Definition info;
	
	public BTNode(Definition d) {
		left = null;
		right = null;
		info = d;
	}

	public BTNode getLeft() {
		return left;
	}

	public void setLeft(BTNode left) {
		this.left = left;
	}

	public BTNode getRight() {
		return right;
	}

	public void setRight(BTNode right) {
		this.right = right;
	}

	public Definition getInfo() {
		return info;
	}

	public void setInfo(Definition info) {
		this.info = info;
	}
	
	

}
