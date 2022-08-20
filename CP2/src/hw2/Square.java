package hw2;

public class Square extends Shape {
	
	private double sidelength;
	
	public Square(double xco, double yco, double sidelen ,String color) {
		xcoordinate = xco;
		ycoordinate = yco;
		super.color = color;
		sidelength = sidelen;
		shape = "Square";
	}
	
	public double area() {
		area = sidelength*sidelength;
		return area;
	}
	
	public double perimeter() {
		perimeter = sidelength*4;
		return perimeter;
	}
	
	public String toString() {
		String ans = super.toString() + "\nSide Length: " + sidelength;
		return ans;
	}

}
