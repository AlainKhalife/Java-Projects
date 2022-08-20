package hw3q3;

public class Game implements Product{
	
	private double price;
	private String type, name;
	private static int count = 0; // This variable keeps track of the number of games I have
	
	public Game(String name, double price) {
		this.name = name;
		this.price = price;
		type = "Game";
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
	
	public static int getNbrOfGames() {
		return count;
	}
	
	public String toString() {
		return "Type: Book\nName: " + name + "\nPrice: " + price + "$\n";
	}

}
