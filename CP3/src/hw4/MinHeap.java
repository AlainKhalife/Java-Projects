package hw4;

public class MinHeap {
	
	private Node[] vertex; // My heap is a vertex node heap
	private int min; // This will be used to point to the size of my array and in case of delete
	
	public MinHeap() {
		// Constructor
		vertex = new Node[2];
		min = 1;
	}
	
	public int getsize() {
		// Just returning the size of my array
		return min;
	}
	
	public Node[] getvertexs() {
		// Just returning the array of jobs
		return vertex;
	}
	
	private void exch(int index1, int index2) {
		// This method will just exchange the jobs at indexes 1 and 2
		// O(1)
		Node temp = vertex[index1];
		vertex[index1] = vertex[index2];
		vertex[index2] = temp;
		
	}
	
	private void swim(int n) {
		// This is method swim which will swim a node up the tree in case the priority of the job was bigger than its parent.
		// We know that the parent is a location n/2 of the node. So we just go to the parent and compare
		// Since each time I am comparing n/2. My running time is:
		// O(log n)
		
		while((n/2!=0) && vertex[n].getWeight()<vertex[n/2].getWeight()) {
			// I am comparing each time to element at n/2 which is the parent node
			exch(n, n/2);
			n = n/2;
		}
	}
	
	private void increaseCapacity() {
		// Method Increase capacity will increase the capacity of my array.
		// Running time O(n)
		
		Node[] temp = new Node[vertex.length+1];
		for(int i = 0; i<vertex.length; i++) {
			temp[i] = vertex[i];
		}
		vertex = temp;
	}
	
	public void insert(Node v) {
		/*
		   This method will make use of increaseCapacity() and swim().
		   IncreaseCapacity -> O(n)
		   swim() -> O(log n)
		   
		   Running time of insert: O(log n) because I am using empty spots in my array and filling them
		   
		   But in worst case I need to increase capacity, Time will increase to O(n + log n) = O(n);
		 */
		
		if(vertex[1]==null) {
			// If my array is empty just put it at root
			// O(1)
			vertex[1] = v;
			min++;
		}
		
		else {
			if(min<vertex.length) {
			// Now in case I deleted a job from the queue. min will be pointing to a null location in my array.
			// I will insert the job at location min and then swim it up.
			if(vertex[min]==null) {
				vertex[min] = v;
				swim(min);   // <--- O(log n)
				min++;
			}
			}
			
			else if(min==vertex.length) {
			// If my min has the same length as my array, then my queue is full
			// I need to increase capacity, insert the job at the end and swim it up
			increaseCapacity(); // <-- O(n)
			vertex[min] = v; // <---- O(1)
			swim(min); // <---- O(log n)
			min++; // <---- O(1)
			}
		}
	}
	
	private void sink(int root) {
		// This is method sink. It will be called be method delete.
		/*
		 	This method will sink a node down the tree (array) until its priority is less than the children nodes it traverses
		 	The same concept as for swim. I am comparing nodes at 2*n and 2*n+1 since its the children location.
		 	If it's priority is less then the children node, I exchange them and continue.
		 	Running time: O(log n)
		 */
		while((2*root <= min && 2*root+1<=min)) {
			// As long as I am in the boundaries of the array
			// min sometimes will be pointing to an empty location in my array.
			// I can use this location to store some elements and make use of my array
			
			if(vertex[2*root]==null && vertex[2*root+1]!=null) {
				// Since I am making use of the free space in my array. I need to check for null positions at 2*root and 2*root+1
				if(vertex[root].getWeight()>vertex[2*root+1].getWeight()) {
					// If its priority is less, exchange jobs and continue sinking.
					exch(root, 2*root+1);
					root=2*root+1;
				}
				
				// else break
				else
					break;
			}
			
			else if(vertex[2*root]!=null && vertex[2*root+1]==null) {
				// Same here
				if(vertex[root].getWeight()>vertex[2*root].getWeight()) {
					exch(root, 2*root);
					root=2*root;
				}
				
				// else break
				else
					break;
			}
			
			else if(vertex[2*root]!=null && vertex[2*root+1]!=null) {
				//Now in case both my children are not null, I need to find the one with the highest priority and exchange it with the corresponding children.
				
				int highest_priority = root; // Initializing my highest priority as being the root node where i'm starting in
				if(2*root+1<min && vertex[highest_priority].getWeight()>vertex[2*root+1].getWeight()) {
					// If the priority of the child at 2*root+1 is bigger than the root, then I make my highest priority to be the child at 2*root+1
					highest_priority = 2*root+1;
				}
				
				if(2*root<min && vertex[highest_priority].getWeight()>vertex[2*root].getWeight()) {
					// If the priority of the child at 2*root is bigger than the root, then I make my highest priority to be the child at 2*root+1
					highest_priority = 2*root;
				}
				
				if(highest_priority!=root) {
					// If a change of root was made I need make the exchanges
					exch(root, highest_priority);
					root=highest_priority;
				}
				
				// else nothing to do break
				else
					break;
				
			}
			
			// If there is nothing to do, break.
			else
				break;
		}
		
	}
	
	public Node deleteMin() {
		
		if(vertex[1]==null)
			return null;
		
		else {
			// If found replace it with the minimum element in the queue and swim it down
			min--;
			Node temp = vertex[1];
			vertex[1] = vertex[min]; // Replacing with minimum
			vertex[min] = null; // Making jobs[min] null to use it later
			sink(1); // sink the element replaced which is O(log n)
			return temp;
		}
	}
	
	public boolean delete(String v) {
		// Method delete needs to search for the owner of the job and delete him from the queue
		// Since I am searching the entire array --> O(n)
		// Once found I need to replace the node with the smallest job priority and then sink it down which is O(log n)
		
		// Running time of delete: O(n)
		
		if(vertex[1]==null)
			return false;
		
		else {
			int pos = 0;
			// Searching for the name of the job owner
			for(int i=1; i<min; i++) {
				// O(n)
				if(vertex[i].getInfo().equals(v)) {
					pos = i;
					break;
				}
			}
			
			// If not found, pos will stay 0. return false
			if(pos==0)
				return false;
			
			else {
				// If found replace it with the minimum element in the queue and swim it down
					min--;
					vertex[pos] = vertex[min]; // Replacing with minimum
					vertex[min] = null; // Making jobs[min] null to use it later
					sink(pos); // sink the element replaced which is O(log n)
					return true;
				}
			}
		}
	
	public void reheapify() {
		for(int i=min-1; i>0; i--) {
			swim(i);
		}
	}
	
	public Node find(String vname) {
		for(int i=1; i<min; i++) {
			if(vertex[i].getInfo().equals(vname))
				return vertex[i];
		}
		return null;
	}
	
	public boolean isEmpty() {
		if(vertex[1]==null)
			return true;
		
		else
			return false;
	}
}
