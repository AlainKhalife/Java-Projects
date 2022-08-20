package Exam;

public class Books {
	private String name, author, publishdate;
	private double price;
	private int availablecopies;
	
	// Constructor
	
	public Books(String bname, String bauthor, String bpublishdate, double bprice, int bavailablecopies){
		name = bname;
		author = bauthor;
		publishdate = bpublishdate;
		price = bprice;
		availablecopies = bavailablecopies;
		
	}
	
	//Getters methods
	
	public String getname(){
		return name;
	}
	
	public double getprice(){
		return price;
	}
	
	public int getavailablecopies(){
		return availablecopies;
	}
	
	public int removecopy(){
		return availablecopies--;
	}
	public String toString(){
		String str = "Book Details:\nName: " + name + "\nAuthor: " + author + "\nPublish Date: " + publishdate + "\nPrice: " + price + "\nAvailable Copies: " + availablecopies;
		return str;
	}
	

}
