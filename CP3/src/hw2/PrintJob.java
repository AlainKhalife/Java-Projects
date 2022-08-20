package hw2;

public class PrintJob {
	
	private String owner;
	private int length;
	private double priority;
	
	public PrintJob(String owner, int length) {
		this.owner = owner;
		this.length = length;
		this.priority = 1/(double)length;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}
	
	public String toString() {
		String ans = "Name: " + owner + "\nLength: " + length + "\nPriority: " + priority + "\n";
		return ans;
	}

}
