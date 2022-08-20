package hw2;

public class EquilateralTriangle extends Triangle{
	
	public EquilateralTriangle(double xco, double yco, double base, double sidelen1, double sidelen2, String color) {
		super(xco, yco, base, sidelen1, sidelen2, color);
		shape = "Equilateral Triangle";
	}
	
	public String toString() {
		String ans = super.toString();
		return ans;
	}
}
