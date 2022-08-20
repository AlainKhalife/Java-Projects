package lab6;

import java.util.Vector;

public class DefinitionNode {
	private Vector<String> info;
	private DefinitionNode next;
	public DefinitionNode(Vector <String> info) {
		this.info=info;
	}
	public Vector<String> getInfo() {
		return info;
	}
	public void setInfo(Vector<String> info) {
		this.info = info;
	}
	public DefinitionNode getNext() {
		return next;
	}
	public void setNext(DefinitionNode next) {
		this.next = next;
	}
	public void addDefinition(String s) {
		this.info.add(s);
	}
	public boolean removeDefinition(String s) {
		for( int i = 0;i<info.size();i++) {
			if(info.get(i).equals(s)) {
				info.remove(i);
				return true;
			}
		}
		return false;
	}
	public String toString() {
		String s ="";
		for (int i = 0; i < info.size(); i++) {
		s=s+"\n-"+info.get(i);
		}
		return s;
	}
}
