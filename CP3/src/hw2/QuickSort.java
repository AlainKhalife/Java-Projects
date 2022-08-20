package hw2;

public class QuickSort {
	
	/*	
	 	Hi Manuella :)
	 	
	 	Name: Alain Khalife
	 	ID: 201807278
	 	
	 	Thank you for all your help. I really appreciate it
	 	
	 	This is the recurrence solution for my implementation of the problem:
	 	My while loop will run N times and I am calling the quick sort method recursively 2 times on half of my array which is T(n/2).
	 	My base case is when the indexes start and end cross each other or when the array reaches a size of 1
	 	
	 	T(n) = 2T(n/2) + n					|		n = 2^k		T(1) = 1
	 		 = 2( 2T(n/4) + n/2) + n		|
	 		 = 4T(n/4) + n + n				|
	 		 = 4(2T(n/8) + n/4) + n + n		|
	 		 = 8T(n/8) + n + n + n			|
	 		 = ...							|
	 		 = 2^kT(2^k/2^k) + k.n			|
	 		 = n.T(1) + k.n					|
	 		 = n + log(n).n					|
	 		 								|
	 		 = O( n.log(n) )				|
	 		 
	 		 
	 	But this is only true when the pivot is not the largest or smallest element.
	 	In case the pivot is the smallest or largest element, I would have to make n-1 comparisons each time
	 	In this case, the real time of quick sort will evolve to O(n^2)
	 	
	 	T(n) = T(n-1) + T(0) + n
	 		 = T(n-1) + n
	 		 = ...
	 		 = O(n^2)
	 	
	 	
	 */
	
	private void exch(int[] a, int i, int j) {
		// This method will just exchange variables in array
		// We did this method in class with Dr Azar
		int k = a[i];
		a[i] = a[j];
		a[j] = k;
	}
	
	public void quickSort(int[] a, int start, int end, int iteration) {
			/*
			 	I though of taking my pivot to be the middle element.
	 			I will have two indices pointing left and right to my array.
	 			I will loop through the elements. If the elements at the left index is less then the pivot, I continue.
	 			If the element at the right of my pivot are larger, I continue.
	 			When I found an element at the left to be smaller than the pivot and an element at the right to larger.
	 			I swap them and continue
	 			
	 			When my right index, passes the left one. I these indexes will constitute the boundaries of my new sub-array
	 			Which I would loop through it again recursively.
			 */
			// the iteration parameter is just used when printing the array at each iteration to tell you which iteration it is
			
			// If my array length is less then 1, return
			if(a.length<=1)
				return;
		
			if(start>=end) {
				// Base case is when start index is larger than the end index
				return;
			}
			
			int pivot = (start+end)/2; // I will take the pivot to be the middle of my array
			int least = start; // Assigning a left index to indicate the least
			int largest = end; // Assigning a right index to indicate the largest
			while(largest>=least) {
				// This while loop in the worst case will run n times where n  is the size of the array
				// I need to loop through all the elements of my array until my left index surpasses my right one
				// O(n)
				
				for(int i=start; i<=end; i++) {
					// Now I need to loop between the indices in order to find the least compared to my pivot
					if(a[i]>a[pivot]) {
						// Once found I assign the left index to i and break
						least = i;
						break;
					}
				}
				
				for(int j=end; j>=start; j--) {
					// Same here, I need to find the biggest element compared to the pivot
					if(a[j]<a[pivot]) {
						// Once found, assign j to it and break
						largest = j;
						break;
					}
				}
				
				if(least<=largest) {
					// When I reach the indexes of the elements less and greater to the pivot. I need to swap them
					exch(a,least,largest);
					least++;
					largest--;
				}
				
			}
			
			// Just Printing the array at each iteration as asked for in the assignment
			for(int j = 0; j<a.length; j++) {
				System.out.print(a[j] + ", ");
			}
			System.out.println("This is the array at iteration " + iteration++);
			
			
			quickSort(a, start, largest, iteration++); // Calling quick sort on the unsorted part of the array. Each time the method is called. It will be running on half of the other array since my pivot is at half of my array
			quickSort(a, least, end, iteration++); // Same here --> T(n/2)
	}
	
	
	
	public static void main(String[] args) {
		QuickSort quick = new QuickSort();
		int[] a = {5,2,4,1,3,7,6};
		System.out.println("Original Array: ");
		for(int i = 0; i<a.length; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.println("\n");
		
		quick.quickSort(a, 0, a.length-1,1);
		
		System.out.println("\nSorted Array: ");
		for(int i = 0; i<a.length; i++) {
			System.out.print(a[i] + ", ");
		}
	}

}
