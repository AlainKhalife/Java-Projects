package lab6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphAdjacencyMatrix {
	
	private int[][] edges;
	private int[] vertices;
	int count = 0;
	
	public GraphAdjacencyMatrix(int nbrOfVertices) {
		if(nbrOfVertices<=0) {
			vertices = new int[1];
			edges = new int[1][1];
		}
		
		else {
		vertices = new int[nbrOfVertices];
		edges = new int[nbrOfVertices][nbrOfVertices];
		}
	}
	
	private void increaseCapacity() {
		int[] temp = new int[vertices.length+1];
		int[][] temp2 = new int[vertices.length+1][vertices.length+1];
		for(int i=0; i<vertices.length; i++) {
			temp[i] = vertices[i];
		}
		
		for(int i=0; i<edges.length; i++) {
			for(int j=0; j<edges.length; j++) {
				temp2[i][j] = edges[i][j];
			}
		}
		edges = temp2;
		vertices = temp;
	}
	
	private int findIndex(int vertex) {
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
		edges[index1][index2] = 1;
		edges[index2][index1] = 1;
		}
	}
	
	public void deleteEdge(int vertex1, int vertex2) {
		int index1 = findIndex(vertex1);
		int index2 = findIndex(vertex2);
		
		if(index1>=0 && index2>=0) {
		edges[index1][index2] = 0;
		edges[index2][index1] = 0;
		}
	}
	
	public int degree(int vertex) {
		int index = findIndex(vertex);
		
		if(index<0)
			return 0;
		
		int degree = 0;
		for(int i=0; i<edges.length; i++) {
			if(edges[index][i]==1)
				degree++;
		}
		return degree;
	}
	
	public boolean deleteVertex(int vertex) {
		int index = findIndex(vertex);
		if(index<0)
			return false;
		
		else {
			int pos = 0;
			int[] newvertices = new int[count-1];
			int[][] temp = new int[count-1][count-1];
			
			for(int i=0; i<count; i++) {
				if(i!=index) {
					newvertices[pos] = vertices[i];
					pos++;
				}
			}
			vertices = newvertices;
			
			pos=0;
			int pos2=0;
			for(int i=0;i<count;i++) {
				if(i!=index) {
					for(int j=0; j<count; j++) {
						if(j!=index) {
						temp[pos][pos2] = edges[i][j];
						pos2++;
						}
					}
					pos2=0;
					pos++;
				}
			}
			edges = temp;
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
			for(int i=0; i<count; i++) {
				if(edges[index][i]==1 && !isVisited(visited, (int)findVertex(i))) {
					int neighbor = findVertex(i);
					s.push(neighbor);
					pos++;
					visited[pos] = neighbor;
					index = findIndex((int)s.peek());
					i=0;
				}
			}
			System.out.print((int)s.pop() + ", ");
		}
		System.out.println();
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
			for(int i=0; i<count; i++) {
				if(edges[index][i]==1 && !isVisited(visited, (int)findVertex(i))) {
					int neighbor = findVertex(i);
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
	
	private int findVertex(int index) {
		return vertices[index];
	}

}
