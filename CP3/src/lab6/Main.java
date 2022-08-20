package lab6;

public class Main {
	/*
	 	Hello Manuella :)
	 	This is the graph we implemented in the lab.
	 	I am sorry if it lacks comments, but the function isTwoColorable contains explanation of how I approched the two clorability problem
	 	and how I implemented it.
	 	
	 	I remember that you told us that hashing is not required, this is why I did not use an hash functions in my implementation.
	 	Also, since this is just an implementation, I used the java defined linked-list and stack in it.
	 	
	 	Important note: The graph is implemented to use integers only.
	 	--------------- So please, when testing and adding vertices and edges, make sure you add them of type int.
	 	
	 */
	
	public static void main(String[] args) {
		GraphAdjacencyList graph = new GraphAdjacencyList(2);
		
		// Only add vertices of type int //
		
		graph.addVertex(1); // Adding vertex 1
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);
		
		graph.addEdge(1, 2); // Adding edge between 1 and 2
		graph.addEdge(1, 6); // //      //     //   1 and 6
		graph.addEdge(2, 3); // Same here and below
		graph.addEdge(6, 5);
		graph.addEdge(3, 6);
		graph.addEdge(3, 4);
		
		boolean is2colorable = graph.isTwoColorable(); // Checking if graph is 2 colorables
		if(is2colorable) {
			System.out.println("Graph is 2 colorable");
		}
		
		else
			System.out.println("Graph is not 2 colorable");
	}

}
