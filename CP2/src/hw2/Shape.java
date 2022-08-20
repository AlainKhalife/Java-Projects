package hw2;

public abstract class Shape {
	
	protected double xcoordinate, ycoordinate, perimeter, area;
	protected String shape, color;
	
	public abstract double area(); // This method is abstract because it is common for all the shapes
	
	public abstract double perimeter(); // This method too
	
	public String toString() {
		String ans = "Shape: " + shape +"\nCoordinates: (" + xcoordinate + "," + ycoordinate + ")" + "\nColor: " + color;
		return ans;
	}
	
	public String getshape() {
		return shape;
	}
	
	public double getXCoordinate() {
		return xcoordinate;
	}
	
	public double getYCoordinate() {
		return ycoordinate;
	}

}
