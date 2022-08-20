package lab2;

public class position {
	
	public static void arrayCopy(int[] source, int srcIndex, int[] dest, int destIndex, int length) {
		for(int i = srcIndex; i<=srcIndex+length; i++) {
			for(int j = destIndex; j<destIndex+length; j++)
				dest[j-1] = source[i];
		}
	}
	
	public static void main (String[] args)
	   {
	    int[] array1 = new int[30];
	    int[] array2 = new int[30];
	    
	    for(int c=0; c<array1.length; c++)
	    	array1[c] = 120; 
	    
	    arrayCopy(array1, 0, array2, 3, 4);
	    
	    for(int j = 0; j<10; j++)
	    	System.out.println(array2[j]);
	   }
}
