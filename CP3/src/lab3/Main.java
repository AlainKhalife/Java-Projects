package lab3;

public class Main {
	
	public static void main(String[] args) {
		BST bst = new BST();
		
		bst.add(10);
		bst.add(5);
		bst.add(12);
		bst.add(4);
		bst.add(6);
		bst.add(11);
		bst.add(13);
		bst.add(5);
		Node n = bst.root();
		bst.rightRotate(n);
		bst.printInOrder();
	}
}
