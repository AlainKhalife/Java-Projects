package hw2;

public class Triangle extends Shape{
	
	private double base, sidelen1, sidelen2;
	
	public Triangle(double xco, double yco, double base, double sidelen1, double sidelen2, String color) {
		xcoordinate = xco;
		ycoordinate = yco;
		super.color = color;
		this.base = base;
		this.sidelen1 = sidelen1;
		this.sidelen2 = sidelen2;
		shape = "Triangle";
	}
	
	public boolean isEquilateral() {
		if(base == sidelen1 && sidelen1 == sidelen2)
			return true;
		
		return false;
	}
	
	// Used Heron's formula to compute the height of the triangle
	public double area() {
		double s = (base+sidelen1+sidelen2)/2;
		double height = (2*(Math.sqrt(s*(s-base)*(s-sidelen1)*(s-sidelen2))))/base;
		area = (base*height)/2;
		return area;
	}
	
	public double perimeter() {
		perimeter = base + sidelen1 + sidelen2;
		return perimeter;
	}
	
	public String toString() {
		String ans = super.toString() + "\nBase: " + base + "\nSide 1: " + sidelen1 + "\nSide 2: " + sidelen2;
		return ans;
	}

}
