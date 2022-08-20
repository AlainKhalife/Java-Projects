package lab6;

import java.util.Vector;

public class DefinitionLL {
	private DefinitionNode header;
	public DefinitionLL() {
		header = new DefinitionNode(null);
		header.setNext(null);
	}
	public DefinitionNode getHeader() {
		return header;
	}
	public void setHeader(DefinitionNode header) {
		this.header = header;
	}
	public void insert(DefinitionNode n,int location) {
		if(location==0) {
		 n.setNext(this.header.getNext());
		 this.header.setNext(n);
		 return;
		}
		DefinitionNode current=this.header;
		while(location!=0 && current!=null) {
			current=current.getNext();
			location--;
		}
		if(location!=0)
			return;
		n.setNext(current.getNext());
		current.setNext(n);
	}
	public Vector<String> delete(int location) {
		if(location<0)
			return null;
		DefinitionNode current=this.getHeader();
		while(location!=0 && current.getNext()!=null) {
			current=current.getNext();
			location--;
		}
		
		if(current.getNext()!=null) {
			Vector<String> temp = current.getNext().getInfo();
			current.setNext(current.getNext().getNext());
			return temp;
		}
		return null;
	}
}
