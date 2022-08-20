package hw2;

public class Circle extends Shape {
	
	private double radius;
	
	public Circle(double xco, double yco, double radius ,String color) {
		xcoordinate = xco;
		ycoordinate = yco;
		super.color = color;
		this.radius = radius;
		shape = "Circle";
	}
	
	public double area() {
		area = (Math.PI)*Math.pow(radius, 2);
		return area;
	}
	
	public double perimeter() {
		perimeter = 2*(Math.PI)*radius;
		return perimeter;
	}
	
	public String toString() {
		String ans = super.toString() + "\nRadius: " + radius;
		return ans;
	}

}
