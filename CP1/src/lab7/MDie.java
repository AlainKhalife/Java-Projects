package lab7;

import java.util.Random;

public class MDie {
	
	private int face;
	
	public MDie(){
		face  = 0;
	}
	
	public void flip(){
		face = (int) (Math.random()*6) + 1;
	}
	
	public int get(){
		return face;
	}
	
	public String toString(){
		
		String ans = "Face value is " + face;
		return ans;
	}

}