package lab5;

import java.util.Scanner;

public class Complex {
	
	private float Re;
	private float Im;
	
	public Complex() {
		Re = 0;
		Im = 0;
	}
	
	public Complex(float a, float b){
		Re = a;
		Im = b;
	}
	
	public static Complex Read() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Re :");
		float R = scan.nextFloat();
		System.out.println("Enter Im :");
		float I = scan.nextFloat();
		
		return new Complex(R, I);
		
	}
	
	public void print() {
		
		System.out.println(Re + "+" + Im );
		
	}
	
	public Complex Add(Complex c){
		float R = Re + c.Re;
		float I = Im + c.Im;
		
		return new Complex(R, I);
	}
	
	public Complex Sub(Complex c){
		float R = Re + c.Re;
		float I = Im + c.Im;
		
		return new Complex(R, I);
	}
	
	public Complex Mult(Complex c){
		float R = (Re * c.Re - Im * c.Im);
		float I = (Re * c.Im + c.Re * Im);
		
		return new Complex(R, I);
	}
	
	public boolean equals(Complex c){
		return (Re == c.Re && Im ==c.Im);
	}
	

	public String toString(){
		
		String real = Re + " ";
		String ans;
		if (Im > 0)
			ans = real + "+ " + Im + "i";
		else if (Im < 0)
			ans = real + Im + "i";
		else ans = real;
		return ans;
	}
	
}
