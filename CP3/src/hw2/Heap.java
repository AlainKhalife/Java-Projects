package hw2;

public class Heap {
	
	/*
	 * All running times are explained under each method
	 */
	
	private PrintJob[] jobs; // My heap is a print job heap
	private int min; // This will be used to point to the size of my array and in case of delete
	
	public Heap() {
		// Constructor
		jobs = new PrintJob[2];
		min = 1;
	}
	
	public int getsize() {
		// Just returning the size of my array
		return min;
	}
	
	public PrintJob[] getJobs() {
		// Just returning the array of jobs
		return jobs;
	}
	
	private void exch(int index1, int index2) {
		// This method will just exchange the jobs at indexes 1 and 2
		// O(1)
		PrintJob temp = jobs[index1];
		jobs[index1] = jobs[index2];
		jobs[index2] = temp;
		
	}
	
	private void swim(int n) {
		// This is method swim which will swim a node up the tree in case the priority of the job was bigger than its parent.
		// We know that the parent is a location n/2 of the node. So we just go to the parent and compare
		// Since each time I am comparing n/2. My running time is:
		// O(log n)
		
		while((n/2!=0) && jobs[n].getPriority()>jobs[n/2].getPriority()) {
			// I am comparing each time to element at n/2 which is the parent node
			exch(n, n/2);
			n = n/2;
		}
	}
	
	private void increaseCapacity() {
		// Method Increase capacity will increase the capacity of my array.
		// Running time O(n)
		
		PrintJob[] temp = new PrintJob[jobs.length+1];
		for(int i = 0; i<jobs.length; i++) {
			temp[i] = jobs[i];
		}
		jobs = temp;
	}
	
	public void insert(PrintJob job) {
		// Method insert will insert a print job in my queue
		/*
		   This method will make use of increaseCapacity() and swim().
		   IncreaseCapacity -> O(n)
		   swim() -> O(log n)
		   
		   Running time of insert: O(log n) because I am using empty spots in my array and filling them
		   
		   But in worst case I need to increase capacity, Time will increase to O(n + log n) = O(n);
		 */
		
		if(jobs[1]==null) {
			// If my array is empty just put it at root
			// O(1)
			jobs[1] = job;
			min++;
		}
		
		else {
			if(min<jobs.length) {
			// Now in case I deleted a job from the queue. min will be pointing to a null location in my array.
			// I will insert the job at location min and then swim it up.
			if(jobs[min]==null) {
				jobs[min] = job;
				swim(min);   // <--- O(log n)
				min++;
			}
			}
			
			else if(min==jobs.length) {
			// If my min has the same length as my array, then my queue is full
			// I need to increase capacity, insert the job at the end and swim it up
			increaseCapacity(); // <-- O(n)
			jobs[min] = job; // <---- O(1)
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
			
			if(jobs[2*root]==null && jobs[2*root+1]!=null) {
				// Since I am making use of the free space in my array. I need to check for null positions at 2*root and 2*root+1
				if(jobs[root].getPriority()<jobs[2*root+1].getPriority()) {
					// If its priority is less, exchange jobs and continue sinking.
					exch(root, 2*root+1);
					root=2*root+1;
				}
				
				// else break
				else
					break;
			}
			
			else if(jobs[2*root]!=null && jobs[2*root+1]==null) {
				// Same here
				if(jobs[root].getPriority()<jobs[2*root].getPriority()) {
					exch(root, 2*root);
					root=2*root;
				}
				
				// else break
				else
					break;
			}
			
			else if(jobs[2*root]!=null && jobs[2*root+1]!=null) {
				//Now in case both my children are not null, I need to find the one with the highest priority and exchange it with the corresponding children.
				
				int highest_priority = root; // Initializing my highest priority as being the root node where i'm starting in
				if(2*root+1<min && jobs[highest_priority].getPriority()<jobs[2*root+1].getPriority()) {
					// If the priority of the child at 2*root+1 is bigger than the root, then I make my highest priority to be the child at 2*root+1
					highest_priority = 2*root+1;
				}
				
				if(2*root<min && jobs[highest_priority].getPriority()<jobs[2*root].getPriority()) {
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
	
	private void sinkForHeapSort(int k, int end) {
		// This method is the same as the above ( sink() ) but the difference here is that I am heap sorting according to the length and not to the priority
		// Also here I am given a fixed array where I need to heap sort
		// I am concerned about null entries since I know the array is full
		// This method will be called by ShowD() and ShowI() when printing the array in ascending and descending order.
		
		// Running Time: O(log n)
		
		while(2*k<end || 2*k+1<end) {
			// Same concept as explained in method sink()
			// But this time I don't need to worry about null positions
			try {
				// Handle array index in case out of bound in case
			int biggest_element = k; // This will tell me which node to swap with
			if(2*k+1<end && jobs[biggest_element].getLength()<jobs[2*k+1].getLength()) {
				// If the priority of the child at 2*root+1 is bigger than the root, then I make my highest priority to be the child at 2*root+1
				biggest_element = 2*k+1;
			}
			
			if(2*k<end && jobs[biggest_element].getLength()<jobs[2*k].getLength()) {
				// If the priority of the child at 2*root is bigger than the root, then I make my highest priority to be the child at 2*root+1
				biggest_element = 2*k;
			}
			
			if(biggest_element!=k) {
				// If a change of root was made I need make the exchanges
				exch(k, biggest_element);
				k =biggest_element;
			}
			
			else // else break
			break;
			
			}
			catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
			
		}
			
	}
	
	public boolean delete(String owner) {
		// Method delete needs to search for the owner of the job and delete him from the queue
		// Since I am searching the entire array --> O(n)
		// Once found I need to replace the node with the smallest job priority and then sink it down which is O(log n)
		
		// Running time of delete: O(n)
		
		if(jobs[1]==null)
			return false;
		
		else {
			int pos = 0;
			// Searching for the name of the job owner
			for(int i=1; i<min; i++) {
				// O(n)
				if(jobs[i].getOwner().equalsIgnoreCase(owner)) {
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
					jobs[pos] = jobs[min]; // Replacing with minimum
					jobs[min] = null; // Making jobs[min] null to use it later
					sink(pos); // sink the element replaced which is O(log n)
					return true;
				}
			}
		}
	
	public boolean prioritize(String owner) {
		// This method will prioritize the job of an owner and make it highest priority
		// Again, I need to search for the name in the array. Replace it with root, change its priority to be the same as root. And finally swim the replaced node (which was the root) back up
		// Since I am searching and swimming again, same as delete
		
		// Running time of prioritize(): O(n + log n) = O(n) | Because I am searching in my array for the name of the owner
		
		if(jobs[1]==null)
			return false;
		
		else {
			int pos = 0;
			for(int i=1; i<min; i++) {
				// Search the array for the owner
				// O(n)
				if(jobs[i].getOwner().equalsIgnoreCase(owner)) {
					pos = i;
					break;
				}
			}
			
			// If not found, pos will stay 0. return false
			if(pos==0)
				return false;
			
			else {
				if(jobs[1].getLength()>1) {
				// If my max element has a length bigger than 1, I can subtract 1 from the length and give it a higher priority
					
				exch(1, pos); // Exchange root with the node
				int new_length = jobs[pos].getLength()-1; // new length will be the max length -1 since lower length= higher priority
				double new_priority = 1/((double)new_length); // Calculating the new priority
				
				jobs[1].setPriority(new_priority); // Change priority of node to match the root
				jobs[1].setLength(new_length); // Change priority of node to match the root
				swim(pos); // Swim my exchanged root back up the tree --> O(log n)
				return true;
				}
				
				else {
					// Otherwise, my max has length of 1, so I can only give it the same length and priority as the max
					// Because I cannot have a length less than 1
					
					exch(1, pos); // Exchange root with the node
					jobs[1].setPriority(jobs[pos].getPriority()); // Change priority of node to match the root
					jobs[1].setLength(jobs[pos].getLength()); // Change priority of node to match the root
					swim(pos); // Swim my exchanged root back up the tree --> O(log n)
					return true;
					
				}
			}
			
		}
	}
	
	public void showD(Heap temp) {
		/*
		 * Since I am sorting the array of size n at each location and doing sink which is O(log n) 
		 * Running time of showD: O(n.log n)
		 */
		
		if(temp.getJobs()[1]==null) {
			// In case no elements elements in queue return with message
			System.out.println("No elements in Queue\n");
			return;
		}
		
		for(int i =jobs.length/2; i>0; i--) {
			// Here I am heap sorting the array based on the length of the array and not on the priority (bigger length = less priority)
			temp.sinkForHeapSort(i, min); // <--- O(log n)
		}
		
		/*
		 Now I know that the job with the highest length is at position 1 in my queue.
		 What I will do is first store the highest length at the first location then exchange the first and last position in the array.
		 This way I will have the smallest element at index 1 always.
		 What I will do, is sink the smallest element. Therefore at index 1, I will always be left with the highest element.
		 So would always copy and exchange the elements until my end index is bigger than 1.
		 
		 Example:
		 If my array is the following:
		 [2,4,3,1]
		 
		 1. I will heapify my array using sink from the middle. I will get : [4,2,3,1]
		 2. Now I copy 4 into my sorted array, exchange it with 1 and move my end index - 1, and redo the same thing. --> [1,2,3,4]
		 3. ---> [1,2,3,4]
		 
		 Now my array is sorted
		 */
		
		int end = temp.min; // Index of the end of my queue
		end--; // Decrease my end
		exch(1, end); // Swap the elements so that at index 1 I have the least and loop until end > 1
		while(end>1) {
			// O(n.log n)
			temp.sinkForHeapSort(1, end); // Perform sink on passed indexes --> O(log n)
			end--; // Decrease end
			exch(1, end); // Exchange again and loop
		}
		
		for(int i = temp.min-1; i>=1; i--) {
			// Here I am just printing the array sorted
			System.out.println(temp.getJobs()[i].toString()); // O(n)
		}
		
		System.out.println("-----------------------------");
	}
	
	
	
	public void showI(Heap temp) {
		// Same concept as showD but this time printing sorted array in reverse
		// O(n.log n)
		
		if(temp.getJobs()[1]==null) {
			System.out.println("No elements in Queue\n");
			return;
		}
		
		for(int i =jobs.length/2; i>0; i--) {
			temp.sinkForHeapSort(i, min);
		}
		
		int end = temp.min;
		end--;
		exch(1, end);
		while(end>1) {
			temp.sinkForHeapSort(1, end);
			end--;
			exch(1, end);
		}
		
		for(int i = 1; i<temp.min; i++) {
			System.out.println(temp.getJobs()[i].toString());
		}
		
		System.out.println("-----------------------------");
	}
}
