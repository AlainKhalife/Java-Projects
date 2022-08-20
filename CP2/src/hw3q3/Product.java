package hw3q3;

public interface Product {
	
	/* This interface is used and implemented in the 3 classes : Book, Game and Electronic
	 * By implementing this interface in those three classes, I am guaranteeing that the methods below will be implemented in those classes.
	 * In Addition, All methods of the classes that implement this interface can now be referenced using this interface ( Polymorphism ). 
	 */
	
	public abstract String toString();
	public abstract double getPrice();
	public abstract String getType();
	public abstract String getName();

}
