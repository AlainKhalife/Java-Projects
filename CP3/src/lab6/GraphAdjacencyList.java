package lab6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphAdjacencyList {
	
	private int[] vertices;
	private LinkedList[] edges;
	private int count = 0;
	
	
	public GraphAdjacencyList(int nbrOfVertices) {
		if(nbrOfVertices<=0) {
			vertices = new int[1];
			edges = new LinkedList[1];
		}
		
		else {
		vertices = new int[nbrOfVertices];
		edges = new LinkedList[nbrOfVertices];
		for(int i=0; i<edges.length; i++) {
			LinkedList l = new LinkedList();
			edges[i]=l;
		}
		}
	}
	
	private void increaseCapacity() {
		int[] temp = new int[vertices.length+1];
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
	
	private int findIndex(int vertex) {
		// This will return the index of a specified vertex in my vertices array
		for(int i=0; i<vertices.length; i++) {
			if(vertices[i]==vertex) {
				return i;
			}
		}
		return -9;
	}
	
	public void addVertex(int vertex) {
		if(count>=vertices.length)
			increaseCapacity();
		
		vertices[count] = vertex;
		count++;
		
	}
	
	public void addEdge(int vertex1, int vertex2) {
		int index1 = findIndex(vertex1);
		int index2 = findIndex(vertex2);
		
		if(index1>=0 && index2>=0) {
			if(!edges[index1].contains(vertex2))
			edges[index1].addFirst(vertex2);
			
			if(!edges[index2].contains(vertex1))
			edges[index2].addFirst(vertex1);
		}
	}
	
	public void deleteEdge(int vertex1, int vertex2) {
		int index1 = findIndex(vertex1);
		int index2 = findIndex(vertex2);
		
		Integer v1 = vertex1;
		Integer v2 = vertex2;
		if(index1>=0 && index2>=0) {
			edges[index1].remove(v2);
			edges[index2].remove(v1);
		}
	}
	
	public int degree(int vertex) {
		int index = findIndex(vertex);
		
		if(index<0)
			return 0;
		
		else
			return edges[index].size();
	}
	
	public boolean deleteVertex(int vertex) {
		int index = findIndex(vertex);
		
		if(index<0)
			return false;
		
		else {
			LinkedList[] temp = new LinkedList[edges.length-1];
			Integer v = vertex;
			int pos = 0;
			int[] newvertices = new int[count-1];

			for(int i=0; i<edges.length; i++) {
				if(i!=index) {
					newvertices[pos] = vertices[i];
					edges[i].remove(v);
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
	
	
	public void DFS() {
		Stack s = new Stack();
		int[] visited = new int[count];
		int pos = 0;
		s.push(vertices[pos]);
		visited[pos] = vertices[0];
		while(!s.empty()) {
			int index = findIndex((int)s.peek());
			for(int i=0; i<edges[index].size(); i++) {
				if(edges[index].get(i)!=null && !isVisited(visited, (int)edges[index].get(i))) {
					int neighbor = (int) edges[index].get(i);
					s.push(neighbor);
					pos++;
					visited[pos] = neighbor;
					index = findIndex((int)s.peek());
					i=-1;
				}
			}
			System.out.print((int)s.pop() + ", ");
		}
		System.out.println();
	}
	
	public boolean isTwoColorable() {
		Stack s = new Stack();
		int[] visited = new int[count]; // This array will keep track of the visited vertices
		int[] color = new int[count]; // 1 will be red, 2 will be green and the position of the vertex will be the same as the index of the color array
		
		// Below I am applying DFS
		int pos = 0; // This is will be used as an index to insert in the visited array
		s.push(vertices[pos]); // Push the first vertex to the stack
		visited[pos] = vertices[0]; // Mark it as visited
		color[0]=1; // Color it red
		while(!s.empty()) {
			// Here I am performing DFS
			int index = findIndex((int)s.peek()); // Since all of vertices are in the array vertices[]. I need to find the index of the vertex that is pushed on the stack
			for(int i=0; i<edges[index].size(); i++) {
				// Loop through all of the edges of that vertex
				if(color[index]==color[findIndex((int)edges[index].get(i))]) {
					// If I find any vertex with same color as the vertex I came from. Then there I return false
					return false;
				}

				if(edges[index].get(i)!=null && !isVisited(visited, (int)edges[index].get(i))) {
					// otherwise, check if the neighbor is not visited
					int neighbor = (int) edges[index].get(i);
					// If not visited I need to change its color
					
					// If the parent node (the node I came from) is green, then color the neighbor red
					if(color[index]==2)
						color[findIndex(neighbor)]=1;
					
					// Otherwise, color it green
					else
						color[findIndex(neighbor)]=2;
					
					s.push(neighbor); // Push the neighbor on the stack
					pos++; // Increment the index of the visited array
					visited[pos] = neighbor; // Mark the neighbor as visited
					index = findIndex((int)s.peek()); // My parent node is now the neighbor
					i=-1; // reset the index of the for loop to check all the neighbors of the neighbor.
				}
			}
			s.pop(); // Once everything is checked pop from the stack in order to backtrack
		}
		
		return true;
	}
	
	public void BFS() {
		Queue q = new LinkedList();
		int pos = 0;
		int[] visited = new int[count];
		q.add(vertices[pos]);
		visited[pos] = vertices[0];
		while(!q.isEmpty()) {
			int index = findIndex((int)q.poll());
			System.out.print(vertices[index] + ", ");
			for(int i=0; i<edges[index].size(); i++) {
				if(edges[index].get(i)!=null && !isVisited(visited, (int)edges[index].get(i))) {
					int neighbor = (int)edges[index].get(i);
					q.add(neighbor);
					pos++;
					visited[pos] = neighbor;
				}
			}
		}
		System.out.println();
	}
	
	
	private boolean isVisited(int[] a, int n) {
		for(int i=0; i<a.length; i++) {
			if(a[i]==n)
				return true;
		}
		
		return false;
	}

}
