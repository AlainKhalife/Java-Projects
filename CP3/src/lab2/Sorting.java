package lab2;

public class Sorting {
	
	private void exch(int[] a, int i, int j) {
		// Method to exchange variables in array
		int k = a[i];
		a[i] = a[j];
		a[j] = k;
	}
	
	public int[] insertionSortV1(int[] a, int start, int end) {
		// First Version of Insertion sort
		// Compare each element at int pos and swap
		for(int i = start; i<end; i++) {
			int pos = i;
			while(pos>start) {
			if(a[pos]<a[pos-1] ) {
				exch(a, pos, pos-1);
				pos--;
			}
			else
			pos--;
			}
		}
		
		return a;
		
	}
	
	public int[] bubbleSort(int[] a, int start, int end) {
		// This is bubble sort
		// max of end is (a.length-1)
		boolean swaped = false;
		int i = end;
		
		while(end!=start) {
			
			if(i>start && a[i]<a[i-1]) {
				exch(a, i, i-1);
				i--;
				swaped = true;
				continue;
			}
			
			else if(i==start) {
				if(swaped==true) {
				start++;
				i = end;
				swaped = false;
				continue;
				}
				
				else
					break;
			}
			
			else
			i--;
		}
		
		return a;
	}
	
	public int[] insertionSortV2(int[] a, int start, int end) {
		// First Version of Insertion sort
		// Compare each element at int pos and swap after finding the lowest element
        for (int i = 1; i < end; ++i) { 
            int element = a[i]; 
            int j = i - 1; 
  
            while (j >= start && a[j] > element) { 
                a[j+1] = a[j]; 
                j = j-1; 
            } 
            a[j+1] = element; 
        } 
		
		return a;
		
	}
	
	/*
	private int[] merge(int[] a, int[] b) {
		// this merge method will be called by merge sort supposing that a and b are sorted
		int j = 0; // this will reference the index in a[]
		int k = 0; // this will reference the index in b[] 
		int[] c = new int[a.length+b.length];
		for(int i =0; i<c.length; i++) {
			if(j<a.length && k<b.length) {
				if(a[j]<b[k]) {
					c[i] = a[j];
					j++;
				}

				else {
					c[i] = b[k];
					k++;
				}
			}
			
			else if(j>=a.length){
				c[i] = b[k];
				k++;
			}
			
			else {
				c[i] = a[j];
				j++;
			}
		}
		
		return c;
	}
	
	public int[] mergeSort(int[] a, int l, int r) {
		if(a.length<=1)
			return a;
		
		int m = (l+r)/2;
		int[] half1 = new int[m-l+1];
		int[] half2 = new int[r-m];
		int pos = 0;
		int index = r-l;
		for(int i =0; i<r-l+1; i++) {
			if(i<half1.length) {
				half1[i] = a[index];
				index++;
			}
			
			else {
				half2[pos] = a[index];
				index++;
				pos++;
			}
		}
		return merge(mergeSort(half1, 0, half1.length-1), mergeSort(half2, 0, half2.length-1));
		
	}
	
	*/
	
	/*
	public void mergeOut(int[] a, int s, int m , int e) {
		// Out of place merge
		if(s>e)
			return;
		
		int count = 0;
		int[] aux = new int[e-s];
		int s2 = m+1;
		int s1 = s;
		while(s1<=m && s2<=e) {
			if(a[s1]<a[s2]) {
				aux[count] = a[s1];
				count++;
				s1++;
			}
			
			else {
				aux[count] = a[s2];
				count++;
				s2++;
			}
		}
		
		while(s<=m) {
			aux[count] = a[s];
			count++;
			s++;
		}
		
		while(s2<=e) {
			aux[count] = a[s2];
			count++;
			s2++;
		}
		
		int count2 = 0;
		for(int i =s; i<)
	}
	*/
	
	
	public void merge(int[] a, int s, int m , int e) {
		// Inplace merge sort
		int s2 = m+1;
		while(s<=m && s2<=e) {
			if(a[s]<=a[s2]) {
				s++;
			}
			
			else {
				int temp = a[s2];
				for(int i = s2; i>s; i--)
					a[i] = a[i-1];
				
				a[s] = temp;
				s++;
				m++;
				s2++;
			}
		}
	}
	
	public void mergeSort(int[] a, int l, int r) {
		if(l<r) {
			int m = (l+r)/2;
			mergeSort(a, l, m);
			mergeSort(a, m+1, l);
			merge(a, l, m ,r);
		}
	}
	
	public static void main(String[] args) {
		Sorting sort = new Sorting();
		/*
		int[] a = new int[10];
		
		for(int i =0 ; i<10; i++) {
			double k = Math.random()*20;
			a[i] = (int) k;
		}
		*/
		
		int[] a = {7,2,3,8,1};
		
		sort.mergeSort(a, 0, a.length);
		
		for(int i =0 ; i<a.length; i++) {
			System.out.print(a[i] + ", ");
		}
	}

}
