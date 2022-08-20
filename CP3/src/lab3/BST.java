package lab3;

public class BST {
	
	private Node root;
	
	public BST() {
		root = null;
	}
	
	public Node root() {
		return root;
	}
	
	public void add(int data) {
		Node n = new Node(data);
		if(root==null)
			root = n;
		
		else
			addHelp(data, root);
	}
	
	private Node addHelp(int data, Node n) {
		if(n==null) {
			n = new Node(data);
		}
		
		else if(data<=n.getData()) {
			n.setLeft(addHelp(data, n.getLeft()));
		}
		
		else {
			n.setRight(addHelp(data, n.getRight()));
		}
		
		return n;
	}
	
	public boolean find(int data) {
		if(root==null)
			return false;
		
		else
			return findHelp(data, root);
	}
	
	private boolean findHelp(int data, Node n) {
		if(n==null)
			return false;
		
		if(n.getData()==data)
			return true;
		
		if(data<n.getData())
			return findHelp(data, n.getLeft());
		
		else
			return findHelp(data, n.getRight());
	}
	
	public boolean delete(int data) {
		if(root == null)
			return false;
		
		else {
			Node current = root;
			Node previous = null;
			while(current!=null && current.getData()!=data) {
				if(data<current.getData()) {
					previous = current;
					current = current.getLeft();
				}
				
				else {
					previous = current;
					current = current.getRight();
				}
			}
			
			if(current==null)
				return false;
			
			else if(current.getLeft()==null && current.getRight()==null) {
				// in case no childs
				if(previous.getLeft()==current) {
					previous.setLeft(null);
					return true;
				}
				
				else {
					previous.setRight(null);
					return true;
				}
			}
			
			else if(current.getRight()==null || current.getLeft()==null) {
				// In case 1 child
				if(current.getLeft()==null) {
					current.setData(current.getRight().getData());
					current.setRight(null);
					return true;
				}
				
				else {
					current.setData(current.getLeft().getData());
					current.setLeft(null);
					return true;
				}
			}
			
			else {
				// Node has 2 children
				Node biggest = current.getRight();
				while(biggest.getLeft()!=null)
					biggest = biggest.getLeft();
				
				int big = biggest.getData();
				delete(biggest.getData());
				current.setData(big);
				return true;
				
			}
				
		}
		
	}
	
	public void printInOrder() {
		if(root==null)
			return;
		
		else
			printInOrderHelp(root);
	}
	
	private void printInOrderHelp(Node n) {
		if(n==null)
			return;
		
		printInOrderHelp(n.getLeft());
		System.out.print(n.getData() + ", ");
		printInOrderHelp(n.getRight());
	}
	
	public boolean checkIfBST() {
		if(root==null)
			return true;
		
		else
			return checkIsBinaryHelp(root);
	}
	
	private boolean checkIsBinaryHelp(Node n) {
		if(n==null)
			return true;
		
		if(n.getLeft()!=null && n.getData()<n.getLeft().getData())
			return false;
		
		if(n.getRight()!=null && n.getData()>n.getRight().getData())
			return false;
		
		if(!checkIsBinaryHelp(n.getLeft()) || !checkIsBinaryHelp(n.getRight()))
			return false;
		
		
		return true;
	}
	
	private Node getParent(Node n) {
		// This method will be called by leftRotate() and rightRotate() to find the parent of a node
		if(n==null)
			return n;
		
		else {
			Node parent = null;
			Node current = root;
			while(current!=null && current!=n) {
				if(n.getData()<=current.getData()) {
					parent = current;
					current = current.getLeft();
				}
				
				else {
					parent = current;
					current = current.getRight();
				}
			}
			return parent;
		}
	}
	
	public void leftRotate(Node n) {
		// Perform left rotate on a specific node in the tree
		Node r  = n.getRight();
		n.setRight(r.getLeft());
		if(getParent(n)==null) {
			n.setRight(r.getLeft());
			root = r;
		}
		
		else if(getParent(n).getLeft()==n)
			getParent(n).setLeft(r);
		
		else
			getParent(n).setRight(r);
		
		r.setLeft(n);
		
	}
	
	public void rightRotate(Node n) {
		// Perform right rotate on a specific node in the tree
		Node l  = n.getLeft();
		n.setLeft(l.getRight());
		if(getParent(n)==null) {
			n.setLeft(l.getRight());
			root = l;
		}
		
		else if(getParent(n).getLeft()==n)
			getParent(n).setLeft(l);
		
		else
			getParent(n).setRight(l);
		
		l.setRight(n);
		
	}

}
