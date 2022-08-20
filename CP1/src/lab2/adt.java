package lab2;

public class adt {
	
	
	public double finalAverage(double test1, double test2, double test3, double testf) {
		
		double best1 = 0, best2 = 0;
		
		
		if (test1 >=0 && test1 <= 100 && test2 >=0 && test2 <= 100 && 
				test3 >=0 && test3 <= 100 && testf >=0 && testf <= 100)
		{
			if (test1 > test2 && test1 > test3)
				best1 = test1;
			
			if (test2 >= test3) {
			    best2 = test2;
			}
			
			else {
				best1 = test2;
				best2 = test3;
			}
			
		
			
			double bestTwo = (((best1 + best2)/2)*60)/100;
			testf = (testf*40)/100;
			
			return bestTwo+testf;
		
		}
		
		else
			return -1;
		
	}

}
