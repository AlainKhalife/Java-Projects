package hw5q1;

public class ArrayQueue {
	
	private Object[] array;
	private int counter; // used to determine the number of elements in-queue
	private int i, j, front, back;
	// i and j are used to determine the location where to enqueue the element and where to dequeue
	// i point to the location where to enqueue and j point the location where to dequeue
	// front and back are used to point the the element at the front and back of the queue
	
	// Constructor
	public ArrayQueue() {
		array = new Object[10];
		counter = 0;
		i=0;
		j=0;
		front = 0;
		back = 0;
	}
	
	public void enqueue(Object o) {
		if(i==array.length && j==array.length && counter == 0) {
			/* if i and j are are pointing to the length of the array and counter is 0, 
			   this means that the array is empty and therefore must reset the values
			 */
			i=0;
			j=0;
			front=0;
			back = 0;
		}
		
		if(back == array.length) {
			// when enqueuing and the pointer "back" is at the end of the array. It must be reset to 0.
			back = 0;
			j=0;
		}
		
		if(i==array.length && j == 0) {
			// This methods ensures capacity normally. and no specific order should be taken care of since i == array length and j == 0
			// This means that I did a series of enqueues and no dequeues.
			ensureCapacity();
			array[i] = o;
			i++;
			counter++;
			front = i-1;
			back = j;
			counter++;
		}
		
		else if(i==array.length && j!=0) {
			// When the end is reached and j is not 0. This means that I have an empty slot that I can use to enqueue.
			// Therefore i and front are reset to 0 and I enqueue at the beginning of the array
			i=0;
			array[i] = o;
			i++; // i needs to become 1 to point to the next slot to enqueue to
			counter++;
			front = 0;
		}
		
		else if(i==j && counter==array.length) {
			/* Here my array is full and the order of the elements is my array is not linear.
			 * Therefore I need to copy the elements to a new array but with the correct order.
			 */
			int i = 0; // This variable is used to point to the locations of the new array
			Object[] temp = new Object[counter*2+1];
			
			if((front-back)>0) {
			// coping the elements to temp while making sure of the order of the elements in the queue
			for(int c = front+1; c<array.length; c++) {
				temp[i] = array[c];
				i++;
			}
			
			for(int d = 0; d<back; d++) {
				temp[i] = array[d];
				i++;
			}
			}
			
			else {
				for(int c = back; c<array.length; c++) {
					temp[i] = array[c];
					i++;
				}
				
				for(int d = 0; d<front+1; d++) {
					temp[i] = array[d];
					i++;
				}
			}
			
			counter++;
			front = counter-1;
			back = 0;
			this.i = counter;
			this.j = back;
			temp[i] = o;
			array = temp;
		}
		
		
		else {
			// anything else is just enqueuing at i
			array[i] = o;
			i++;
			front=i-1;
			counter++;
		}
		
	}
	
	public Object dequeue() {
		
		if(counter == 0 )
			return "Queue Empty";
			
		
		else if(j==array.length) {
			j=0;
			back = j;
			Object temp = array[j];
			array[j] = null;
			counter--;
			return temp;
		}


		else {
			Object temp = array[j];
			array[j] = null;
			j++;
			back++;
			counter--;
			return temp;
		}

	}
	
	public Object peek() {
		// returning the first element to be dequeued without dequeuing it. 
		return array[back];
	}
	
	public boolean empty() {
		return (counter==0);
	}
	
	
	public void ensureCapacity(){
		Object[] temp = new Object[counter*2+1];
		for(int i = 0; i<counter; i++)
			temp[i] = array[i];
		
		array = temp;
	}

}
