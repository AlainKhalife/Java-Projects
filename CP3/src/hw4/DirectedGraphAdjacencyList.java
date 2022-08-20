package hw4;

public class DirectedGraphAdjacencyList {
	
	private Node[] vertices;
	private LinkedList[] edges;
	private int count = 0;
	
	
	public DirectedGraphAdjacencyList(int nbrOfVertices) {
		if(nbrOfVertices<=0) {
			vertices = new Node[1];
			edges = new LinkedList[1];
		}
		
		else {
		vertices = new Node[nbrOfVertices];
		edges = new LinkedList[nbrOfVertices];
		for(int i=0; i<edges.length; i++) {
			LinkedList l = new LinkedList();
			edges[i]=l;
		}
		}
	}
	
	private void increaseCapacity() {
		Node[] temp = new Node[vertices.length+1];
		LinkedList[] temp2 = new LinkedList[vertices.length+1];
		for(int i=0; i<vertices.length; i++) {
			temp[i] = vertices[i];
			temp2[i] = edges[i];
		}
		LinkedList l = new LinkedList();
		temp2[temp2.length-1] = l;
		edges = temp2;
		vertices = temp;
	}
	
	private int findIndex(String vertex) {
		// This will return the index of a specified vertex in my vertices array
		for(int i=0; i<vertices.length; i++) {
			if(vertices[i].getInfo().equals(vertex)) {
				return i;
			}
		}
		return -9;
	}
	
	public void addVertex(Node vertex) {
		if(count>=vertices.length)
			increaseCapacity();
		
		vertices[count] = vertex;
		count++;
		
	}
	
	public void addEdge(String source, String destination, int weight) {
		int index1 = findIndex(source);
		int index2 = findIndex(destination);
		vertices[index2].setWeight(weight);
		
		if(index1>=0) {
			if(!edges[index1].contains(destination)) {
			edges[index1].add(destination);
			edges[index1].find(destination).setWeight(weight);
			}
		}
	}
	
	public void deleteEdge(String vertex1, String vertex2) {
		int index1 = findIndex(vertex1);
		int index2 = findIndex(vertex2);
		
		if(index1>=0 && index2>=0) {
			edges[index1].delete(vertex2);
		}
	}
	
	public int degree(String vertex) {
		int index = findIndex(vertex);
		
		if(index<0)
			return 0;
		
		else
			return edges[index].size();
	}
	
	public boolean deleteVertex(String vertex) {
		int index = findIndex(vertex);
		
		if(index<0)
			return false;
		
		else {
			LinkedList[] temp = new LinkedList[edges.length-1];
			int pos = 0;
			Node[] newvertices = new Node[count-1];

			for(int i=0; i<edges.length; i++) {
				if(i!=index) {
					newvertices[pos] = vertices[i];
					edges[i].delete(vertex);
					temp[pos] = edges[i];
					pos++;
				}
			}
			edges = temp;
			vertices = newvertices;	
			count--;
			return true;
		}
	}
	
	public void Dijkstra(Node v) {
		// Running time is O( V.log(V) )
		int indexofsource = findIndex(v.getInfo()); // This will reference the index of the source i am starting with
		
		String[] parents = new String[count]; // This will reference the parent of each node when printing the results
		MinHeap allvertex = new MinHeap(); // This heap will be used when 
		LinkedList s = new LinkedList(); // This will act as the set of the visited vertices
		int[] d = new int[count]; // This array will store the estimated distance from a node to the source
		String[] p = new String[count]; // This will indicate the predecessor of v on current estimated shortest path from the source to v
		
		// Initialization
		s.add(v.getInfo()); // Adding source as done
		d[indexofsource]=0; // Setting distance of source to 0
		for(int i=0; i<count; i++) {
			// Setting all other distances to a big number and all parents to null and adding them to the min heap
			// This takes O(V)
			if(i!=indexofsource) {
				d[i]=99999999; 
				p[i]=null;
				Node temp = new Node(vertices[i].getInfo());
				temp.setWeight(99999999);
				allvertex.insert(temp);
			}
		}
		
		for(int i=0; i<edges[indexofsource].size(); i++) {
			// Setting all neighbors of the source to the correct parent and distance
			d[findIndex(edges[indexofsource].get(i))]= edges[indexofsource].getNode(i).getWeight();
			p[findIndex(edges[indexofsource].get(i))]= v.getInfo();
			allvertex.find(edges[indexofsource].get(i)).setWeight(edges[indexofsource].getNode(i).getWeight());
			parents[findIndex(edges[indexofsource].get(i))] = v.getInfo();
		}
		allvertex.reheapify();
		// End of Initialisation
		
		while(!allvertex.isEmpty()) {
			// This loop will repeat V times
			// Running time is O( V.log(V) )
			Node visited = allvertex.deleteMin(); // Pop the root the min heap and reheapifying O(log V) 
			s.addWithWeight(visited.getInfo(), visited.getWeight()); // Add the edge to the visited set
			int indexOfParent = findIndex(visited.getInfo());
			for(int i=0; i<edges[indexOfParent].size(); i++) {
				// Here I am relaxing all edges that don't belong to the set S
				// O(E)
				indexofsource = findIndex(edges[indexOfParent].get(i));
				// Setting all neighbors of the source to the correct parent and distance
				if(!s.contains(edges[indexOfParent].get(i)) && d[findIndex(edges[indexOfParent].get(i))]>(edges[indexOfParent].getNode(i).getWeight()+d[indexOfParent])) {
				// As long as the set s does not contain the node I am trying to check and as long as the distance from the previous path with weight of the current node
				// is less than the previous one, I update the parent and the distance of the node
				parents[indexofsource] = visited.getInfo(); // setting the parent of the node to be used when printing the spt
				d[findIndex(edges[indexOfParent].get(i))]= d[indexOfParent] + edges[indexOfParent].getNode(i).getWeight(); // Updating distance of the node
				p[findIndex(edges[indexOfParent].get(i))]= vertices[indexOfParent].getInfo(); // updating the parent of the node to the new one it should come from
				allvertex.find(edges[indexOfParent].get(i)).setWeight(d[indexOfParent] + edges[indexOfParent].getNode(i).getWeight()); // Updating the weight of the vertex in the min heap
				}
			}
			allvertex.reheapify(); // reheapifying because I updated the weight inside the min heap
		}
		
		// Printing results:
		// Here I am printing the shortest path tree from the source node to all other nodes
		System.out.println("Shortest path tree rooted at " + v.getInfo() + ":");
		for(int j=0; j<vertices.length; j++) {
			if(!vertices[j].getInfo().equals(v.getInfo())) {
			String ans = v.getInfo() + " to " + vertices[j].getInfo() + "\t| Shortest Path: " + v.getInfo();
			String nodeparent = parents[findIndex(vertices[j].getInfo())];
			LinkedList spt = new LinkedList(); // This LinkedList will be used to print the shortest path tree from the source to all other nodes in the graph
			spt.add(vertices[j].getInfo()); // Adding source node to the shortest path tree
			
			if(nodeparent==null) {
				// Taking care of the case when the parent node is null, so it has no path to take
				ans = v.getInfo() + " to " + vertices[j].getInfo() + "\t| Shortest Path: " + "No path from " + v.getInfo() + " to " + vertices[j].getInfo();
				System.out.println(ans);
				continue;
			}
			
			while(!nodeparent.equals(v.getInfo())) {
				// Here I am looping through the parents of the nodes until I get to the source node.
				int indexofparent = findIndex(nodeparent);  // finding the index of the parent of the node
				spt.add(vertices[indexofparent].getInfo()); // Adding node to the spt
				nodeparent = parents[findIndex(nodeparent)]; // setting the nodeparent to the parent of the node
			}
			ans+= "-->" + spt.printReverse();
			System.out.println(ans);
			}
		}
		
		// Printing the shortest path distance from the source node to all the other nodes
		System.out.println("\nShortest distance from " + v.getInfo() + " to all other nodes\n");
		for(int i=1; i<d.length; i++) {
			System.out.println(v.getInfo() + "--->" + vertices[i].getInfo() + ": " + ((d[i]>99999998)? "No path" : d[i]));
		}
		System.out.println("-----------------------------------------------");
	}

}
