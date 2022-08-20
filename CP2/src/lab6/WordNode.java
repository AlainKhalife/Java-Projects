package lab6;

public class WordNode {
	private String info;
	private WordNode next;
	public WordNode(String info) {
		this.info=info;
		this.next=null;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public WordNode getNext() {
		return next;
	}
	public void setNext(WordNode next) {
		this.next = next;
	}
	public String toString() {
		return info+":";
	}

}
