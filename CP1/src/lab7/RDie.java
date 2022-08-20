package lab7;

import java.util.Random;

public class RDie {
	
private int face;
	
	public RDie(){
		face  = 0;
	}
	
	public void flip(){
		Random rand = new Random();
		face = rand.nextInt(6)+1;
	}
	
	public int get(){
		return face;
	}
	
	public String toString(){
		
		String ans = "Face value is " + face;
		return ans;
	}

}
