package hw3q1;
import java.util.*;
public class IterativeTOH
{
	    private int nbr_Of_Disks; 
	    
	    public IterativeTOH(int nDisks){
	        nbr_Of_Disks = nDisks;
	    }
	       
	    private int disk(int i) {
	        int a, b = i+1; 
	        for(a=0;b%2==0;a++){ 
	            b/=2;
	        }
	        return a;
	    }
	    
	    private int movements(int i, int c) { 
	        while(c--!=0)
	            i/=2;
	        return (i+1)/2;
	    }
	    
	    private int direction(int d,int n) {
	        return 2 - (n + d)%2;
	    }
	    
	    private void move(int disk, int source, int destination) {
	        System.out.println("Move Disk " + (disk+1)+ " from Tower " + (source+1) + " to Tower " + (destination+1));
	    }
	    
	    public void TOH(){
	        int n = nbr_Of_Disks;
	        int limit = (int)Math.pow(2,n)-1; 
	        for(int i=0;i<limit;i++){
	            int disk = disk(i);
	            int source = ( movements(i,disk)*direction(disk,n))%3;
	            int destination = (source + direction(disk,n))%3;
	            move(disk,source,destination);
	        }
	    }
	    
	    public static void main(String[] args){
	       Scanner scan = new Scanner(System.in);
	       System.out.print("Enter the number of disks : ");
	       int n = scan.nextInt();
	       IterativeTOH toh = new IterativeTOH(n);
	       toh.TOH();
	    }
    
}