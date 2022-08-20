package hw4;

public class Main {
	
	public static void main(String[] args) {
		// Question 2 First graph
		System.out.println("Question 2 First Graph:");
		DirectedGraphAdjacencyList graph = new DirectedGraphAdjacencyList(3);
		Node a = new Node("A"); // Creating nodes
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		
		graph.addVertex(a); // Adding nodes to the graph
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		
		graph.addEdge("A", "B", 2); // Adding an edge from A to B with a weight of 2 where A is the source and B is the destination
		graph.addEdge("B", "C", 4); // same here and below
		graph.addEdge("C", "D", 5);
		graph.addEdge("D", "C", 10);
		graph.addEdge("D", "B", 1);
		graph.addEdge("A", "E", 2);
		graph.addEdge("A", "F", 1);
		graph.addEdge("F", "E", 11);
		graph.addEdge("E", "A", 1);
		graph.Dijkstra(a);
		System.out.println();
		
		
		// Question 2 Second graph
		// Here because we have a negative weight edge. Dijkstra Fails
		System.out.println("Question 2 Second Graph:");
		DirectedGraphAdjacencyList graph2 = new DirectedGraphAdjacencyList(3);
		Node a2 = new Node("A");
		Node b2 = new Node("B");
		Node c2 = new Node("C");
		Node d2 = new Node("D");
		Node e2 = new Node("E");
		Node f2 = new Node("F");
		
		graph2.addVertex(a2);
		graph2.addVertex(b2);
		graph2.addVertex(c2);
		graph2.addVertex(d2);
		graph2.addVertex(e2);
		graph2.addVertex(f2);
		
		graph2.addEdge("A", "B", 3);
		graph2.addEdge("A", "C", 5);
		graph2.addEdge("B", "D", 4);
		graph2.addEdge("A", "D", 2);
		graph2.addEdge("C", "D", -4);
		graph2.addEdge("D", "E", 1);
		graph2.addEdge("E", "F", -1);
		graph2.addEdge("F", "A", 4);
		graph2.addEdge("A", "F", 10);
		graph2.Dijkstra(a2);
		System.out.println();
		
		// Question 2 Third Graph
		// Here Dijkstra Fails because we have a negative weight edge
		System.out.println("Question 2 Third Graph:");
		DirectedGraphAdjacencyList graph3 = new DirectedGraphAdjacencyList(3);
		Node a3 = new Node("A");
		Node b3 = new Node("B");
		Node c3 = new Node("C");
		Node d3 = new Node("D");
		Node e3 = new Node("E");
		Node f3 = new Node("F");
		Node g3 = new Node("G");
		
		graph3.addVertex(a3);
		graph3.addVertex(b3);
		graph3.addVertex(c3);
		graph3.addVertex(d3);
		graph3.addVertex(e3);
		graph3.addVertex(f3);
		
		graph3.addEdge("A", "B", 13);
		graph3.addEdge("A", "C", 12);
		graph3.addEdge("C", "A", 11);
		graph3.addEdge("B", "C", 11);
		graph3.addEdge("C", "B", 10);
		graph3.addEdge("C", "D", 15);
		graph3.addEdge("D", "B", 4);
		graph3.addEdge("B", "D", 2);
		graph3.addEdge("D", "E", 3);
		graph3.addEdge("D", "F", 5);
		graph3.addEdge("F", "B", 1);
		graph3.addEdge("F", "C", 2);
		graph3.addEdge("E", "B", 4);
		graph3.addEdge("G", "F", 2);
		graph3.addEdge("G", "B", 3);
		graph3.addEdge("G", "C", 12);
		graph3.Dijkstra(a3);
		System.out.println();
	}

}
