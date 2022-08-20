package hw3q3;

public class Electronic implements Product {
	
	private double price;
	private String type, name;
	private static int count = 0; // This variable keeps track of the number of electronics I have
	
	public Electronic(String name, double price) {
		this.name = name;
		this.price = price;
		type = "Electronic";
		count++;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public static int getNbrOfElectronics() {
		return count;
	}
	
	public String toString() {
		return "Type: Book\nName: " + name + "\nPrice: " + price + "$\n";
	}

}
