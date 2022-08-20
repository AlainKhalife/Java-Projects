package lab5;

public class ComplexTest {
	
	public static void main (String[] Args){
		
		Complex a = new Complex();
		Complex b = new Complex();
		Complex c = new Complex();
		Complex d = new Complex(2,4);
		
		
		System.out.println("Enter a");
		a = Complex.Read();
		
		System.out.println("Enter b");
		b = Complex.Read();
		
		c = a.Add(b);
		a = b.Add(c.Mult(d));
		b = a.Add(c.Sub(d));
		d = a.Mult(b.Mult(c.Mult(d)));
		
		System.out.println("c = " + c);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("d = " + d);
		
	}

}
