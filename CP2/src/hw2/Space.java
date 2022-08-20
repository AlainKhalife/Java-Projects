package hw2;

public class Space {
	
	private Shape[] shapes = new Shape[10];
	private int count;
	
	public Space() {
		count =0;
	}
	
	    // Increase Size of the array
		public void increaseSize(){
			Shape[] temp = new Shape[count*2+1];
			for(int i = 0; i<count; i++)
				temp[i] = shapes[i];
			
			shapes = temp;
		}
		
		
		//Add a Shape
		
		public void add(Shape item){
			if(count == shapes.length)
				increaseSize();
			
			shapes[count] = item;
			count++;
		}
		
		
		//Search for Shape by name
		// Returns -1 if not found
		
		public int searchByName(String name){
			for(int i =0; i<count; i++)
				if(name.equalsIgnoreCase(shapes[i].getshape()))
					return i;
			
			return -1;
		}
		
		
		
		//Search for Shape by coordinates
		// Returns -1 if not found
				public int searchByCoordinates(double xcor, double ycor){
					for(int i =0; i<count; i++)
						if(shapes[i].xcoordinate == xcor && shapes[i].ycoordinate == ycor)
							return i;
					
					return -1;
				}
		
				
		// Return a Shape from the array
		public Shape search(int place){
			return shapes[place];
		}
		
		
		
		// Delete a shape from the array by using its coordinates
		
		public boolean delete(double xcor, double ycor){
			int loc = searchByCoordinates(xcor, ycor);
			if(loc<0)
				return false;
			
			else{
				count--;
				shapes[loc] = shapes[count];
				shapes[count] = null;
				return true;
			}
		}
		
		// Display all the elements of the array
		public void displayAll() {
			for(int i = 0; i<count; i++)
		      System.out.println(shapes[i] + "\n");
		}
		
		public int getNumberOfShapes() {
			return count;
		}
		
		public Shape getShape(int location) {
			return shapes[location];
		}
}
