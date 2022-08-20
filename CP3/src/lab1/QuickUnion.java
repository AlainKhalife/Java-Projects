package lab1;

public class QuickUnion {
	
	private static int[] id = new int[10];
	
	private static void setup() {
		for(int i =0; i<id.length; i++)
			id[i] = i;
	}
	
	private static int root(int i) {
		// get the root of the node at index i
		while( i!=id[i])
			i=id[i];
		
		return i;
	}
	
	public static boolean find(int p, int q) {
		// return true if there is a connection between p and q
		return root(p) == root(q);
	}
	
	public static void union(int p, int q) {
		// Move the root of p's tree to become a child of the root of q
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
	
	public static void main(String[] args) {
		setup();
		union(4, 3);
		union(3, 8);
		union(6, 5);
		union(9, 4);
		union(2, 1);
		
		for(int i =0; i<id.length; i++)
		System.out.print(id[i] + ", ");
	}

}
