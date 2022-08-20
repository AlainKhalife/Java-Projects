package lab6;

public class WordLL {
	private WordNode header;
	public WordLL() {
		header=new WordNode("");
		header.setNext(null);
	}
	public WordNode getHeader() {
		return header;
	}
	public void setHeader(WordNode header) {
		this.header = header;
	}
	public void insert(WordNode n,int location) {
		if(location==0) {
		 n.setNext(this.header.getNext());
		 this.header.setNext(n);
		 return;
		}
		WordNode current=this.header;
		while(location!=0 && current!=null) {
			current=current.getNext();
			location--;
		}
		if(location!=0)
			return;
		n.setNext(current.getNext());
		current.setNext(n);
	}
	public int delete(String s) {
		int location=0;
		WordNode current=header;
		while(current.getNext()!=null) {
			if(current.getNext().getInfo().equals(s)) {
				current.setNext(current.getNext().getNext());
				return location;
			}
			location++;
			current=current.getNext();
		}
		return -1;
	}
}
