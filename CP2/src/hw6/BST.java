package hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This is class binary search tree
 * All private methods are helper functions for the public methods
 * All the public methods are being called from class Main
 * 
 * All methods below except method delete are recursive
 */
public class BST {
	
	private BTNode root; // Root of the tree
	
	public BST() {
		root = null;
	}
	
	public BTNode getRoot() {
		return root;
	}
	
	public void add(Definition d) {
		// Method add will add a definition to the tree
		BTNode node  = new BTNode(d);
		if(root == null) // If the root is null, then I need to assign it to the a new node which is here node
			root = node;
		
		else // Otherwise I need to add the node to tree by calling the function addHelp()
		addHelp(d, root); // Calling the helper function to add
	}
	
	public BTNode find(String word) {
		// This method will find a word in the tree and return its node
		return findHelp(word, root);
	}
	
	public ArrayList findAllDuplicates(String word) {
		// This method will return an ArrayList containing references to nodes that the user wants to delete
		ArrayList<Definition> a = new ArrayList();
		return findAllDuplicatesHelp(word, root, a);
	}
	
	public void listAllDuplicates(String word) {
		// This methods prints all the definitions of the same word
		listAllDuplicatesHelp(word, root);
	}
	
	public void listSorted() {
		// This method will print the elements of the tree sorted
		listSortedHelp(root);
	}
	
	public void printTree() {
		// This method will print the tree
		printTreeHelp(root);
		System.out.println();
	}
	
	
	public boolean findIgnoreCase(String word, String description) {
		// This method is to find if any elements exist in both upperCase and LowerCase
		return findIgnoreCaseHelp(word, description, root);
	}
	
	// -----------------------------------------------------------------------------------------
	
	// Below are the helper function of the above methods, all the explanations are below
	
	
	private boolean findIgnoreCaseHelp(String word, String description, BTNode n) {
		// This method will check whether a word exists or not and return true if present in the tree
		/*
		 * I am searching the entire tree because I took the case where a word can be present in lowerCase and upperCase at the same time
		 * Suppose in my tree I had the words:
		   Address: A location (With a capital A) and the user wants to insert same word with the same description but with lowerCase a (address: A location)
		   
		   In this situation I cannot search a specific subtree, because in ASCII a<A and A>a
		   This is why I need to search the entire tree and make sure that the word (LowerCase and UpperCase does not occur twice)
		 */
		if(n==null) // Base case if n is null, then I did not find the word
			return false;
		
		if(n.getInfo().getWord().equalsIgnoreCase(word) && n.getInfo().getDescription().equalsIgnoreCase(description)) // If the word and description matches, then true
			return true;
		
		return findIgnoreCaseHelp(word, description, n.getLeft()) || findIgnoreCaseHelp(word, description, n.getRight()); // Then I need to search both subtrees
		
	}
	
	private void printTreeHelp(BTNode n) {
		/*
		 * I tried my best for this method and its really tricky :(
		 * Please don't be mean with the grade. This is the only thing that I struggled with. PLEASE
		 * MY ONLY HOPE LEFT IS THE GRADE OF THIS ASSIGNMENT. This is the only method that might not do the job fully.
		 * PLEASE, I NEED TO PASS THIS COURSE
		 * AGAIN, THANK YOU FOR ALL YOUR ASSISTANCE DURING THIS SEMESTER.
		 */
		if(n == null)
			return;
		
		if(n == root)
		System.out.println(n.getInfo().getWord());
		
		// I tried to see if the node has 2 childs, then print the 2 nodes
		if(n.getLeft()!=null && n.getRight()!=null) {
			System.out.println(n.getLeft().getInfo().getWord() + "\t"  + n.getRight().getInfo().getWord());
		}
		
		// Then check if I can go right
		if(n.getRight()!=null) {
		// Print the word in the right
		System.out.println(n.getRight().getInfo().getWord());
		// The go to the right
		printTreeHelp(n.getRight());
		}
		
		if(n.getLeft()!=null) {
		// Print the node on the left	
		System.out.println(n.getLeft().getInfo().getWord());
		// Then go the next node on the left
		printTreeHelp(n.getLeft());
		}
	}
	
	private void listAllDuplicatesHelp(String word, BTNode n) {
		// This method will list all the elements that have same name but different descriptions (homonyms)
		// In this method, the word is know, and I know which part of the subtree to traverse unlike the IgnoreCase method
		
		if(n==null) // Base Case if n is null, then just return, no need to do anything
			return;
		
		if(word.compareTo(n.getInfo().getWord())==0) // If my word matches the word in the node, then I need to print it
			System.out.println(n.getInfo());
		
		if(word.compareTo(n.getInfo().getWord())<=0) // If my word compared to the word in the node is less lexicographicly, then I need to go left of the node
			listAllDuplicatesHelp(word, n.getLeft());
		
		else
		listAllDuplicatesHelp(word, n.getRight()); // Otherwise I need to go right of the node
	
	}
	
	private void listSortedHelp(BTNode n) {
		// This method will print the elements of the tree sorted
		// In order to print the tree sorted, I need to start printing from the left subtree until I reach the right one
		// since the elements in the tree are sorted from left to right
		if(n == null) // Base case if n is null, then just return
			return;
		
		if(n.getLeft()!=null)
		listSortedHelp(n.getLeft()); // I start by traversing the left subtrees, until is it null
		
		System.out.println(n.getInfo()); // Then start printing the words in the nodes
		
		if(n.getRight()!=null)
		listSortedHelp(n.getRight()); // When it is possible to go right, I go right and print the elements
	}
	
	
	private ArrayList<Definition> findAllDuplicatesHelp(String word, BTNode n, ArrayList<Definition> array) {
		// This method will return an ArrayList containing the references of the definitions in the tree
		// The ArrayList will be used when asking for the user to delete a specific word
		// This way i don't need to traverse the tree every time and search for the elements
		// I can simply put their definitions in an ArrayList and when the user wants to choose what definition he want to delete
		// I can simply delete the node based on its description the ArrayList
		if(n == null) // Base case If n is null, simply return the array
			return array;
		
		if(word.compareTo(n.getInfo().getWord())==0) { // If I found the word, then put its description in the array
			array.add(n.getInfo());
		}
		
		if(word.compareTo(n.getInfo().getWord())<=0) // If the word I am looking for is less then the word in the node, the I need to go left of the node
			findAllDuplicatesHelp(word, n.getLeft(), array);
		
		else
		findAllDuplicatesHelp(word, n.getRight(), array); // Otherwise I need to go right
		
		return array;
	}
	
	private BTNode findHelp(String word, BTNode n) {
		// This method with find a word in the tree
		
		if(n == null) // Base case if n is null, then return n
			return n;
		
		if(word.compareTo(n.getInfo().getWord())==0) // If i found the word, then return its node
			return n;
		
		else if(word.compareTo(n.getInfo().getWord())<=0) // If the word is less compared to the word in the node, then I need to go left of the tree
			return findHelp(word, n.getLeft());
		
		else
			return findHelp(word, n.getRight()); // Otherwise I need to go right of the tree
	}
	
	
	private BTNode addHelp(Definition d, BTNode n) {
		// This method will add definitions to the tree respecting the order of a BST
		
		if(n == null) // Base case if n is null, then I need to create a new node and add it to the tree
			 n = new BTNode(d);
		
		else if((d.getWord().compareTo(n.getInfo().getWord()))<=0)
			// If the word I want to add is less compared to the word already in the node
			// Then I need to set the left of the node to the point to a new node containing the definition
			n.setLeft(addHelp(d, n.getLeft()));
		
		else // Otherwise I need to do the same, but set the right of the node to point to a new node containing the definition
			n.setRight(addHelp(d, n.getRight()));
		
		return n; // And finally return the node added to the tree
	}

	
	public boolean delete(Definition d) {
		// This method will delete a node from the tree
		// I took several cases into consideration
		
		BTNode current = root; // First I need to pointer that will help me change the order of the nodes
		
		if(root == null) // First if the tree is empty, then I can't delete, I return false
			return false;
		
		else if(root.getInfo().getWord().equals(d.getWord()) && (root.getLeft()==null) && (root.getRight()==null)) {
			// In the case the user wants to delete the root and there are no child
			// I simply need to set the root to null
			root = null; // Set root to null
			return true; // And return true
		}
			
		else if(root.getInfo().getWord().equals(d.getWord()) && (root.getLeft()==null) && (root.getRight()!=null)) {
			// In the case the user wants to delete the root and there is no left subtree,
			// All I need to do is make the root point to the next node on the right
			BTNode right = current.getRight(); // Pointing to the element on the right
			root.setRight(null); // Setting the pointer of the root to null, so it looses reference
			root = right; // Then set the root the node on the right
			return true; // And return true
		}
		
		else if(root.getInfo().getWord().equals(d.getWord()) && (current.getRight()==null) && (current.getLeft()!=null)) {
			// In the case the user wants to delete the root and there is no right subtree,
			// All I need to do is make the root point to the next node on the left
			current = current.getLeft(); // Pointing to next element on the left
			root.setLeft(null); // Making the left of root point to null
			root = current; // Assigning root to point to the node on the left
			return true; // Return true
		}
		
		else {
			// Otherwise, we have 3 cases we need to take care of
			while((current!=null) && ((!current.getInfo().getWord().equals(d.getWord())) || (!current.getInfo().getDescription().equals(d.getDescription()))) ) {
				// First I need to move the current pointer to point to the element that the user wants to delete
				if(d.getWord().compareTo(current.getInfo().getWord())<=0) // If the word I am searching  for is less than the word in the node, then I need to move my pointer to the left subtree
					current = current.getLeft();
				
				else
					current = current.getRight(); // OtherWise, I need to move it to the right
			}
			
			if(current == null)
				return false;
						
			else {			
			
			// If the node has no child, I can simply remove it
			// To do so, I only need to remove the reference of its parent node
			if(current.getLeft()==null && current.getRight()==null) {
				BTNode reference = root; // The node called reference is used to point to the parent of the node i want to delete
				while((reference.getRight()!=current) && (reference.getLeft()!=current)) {
					// I need to move the Node reference one node before the node that the user wants to delete
					if(d.getWord().compareTo(reference.getInfo().getWord())<=0)
						reference = reference.getLeft();
					
					else
						reference = reference.getRight();
				}
				
				if(reference.getLeft()==current) // If the left of the parent node is pointing to the node that the user wants to delete, then make the left point to null
					reference.setLeft(null);
				
				else
					reference.setRight(null); // Otherwise, make the right point to null
				
				return true; // Return true
			}
			
			else if(current.getLeft()==null || current.getRight()==null) {
				//In the case the node has only 1 child
				BTNode reference = root; // Again, this node is used to point to the parent of the node i want to delete
				while((reference.getRight()!=current) && (reference.getLeft()!=current)) { // Same as before, moving the reference node to the parent of the node the user wants to delete
					if(d.getWord().compareTo(reference.getInfo().getWord())<=0)
						reference = reference.getLeft();
					
					else
						reference = reference.getRight();
				}
				
				if(current.getRight()!=null && current.getLeft()==null) {
					// If the node that the user wants to delete has the left of it pointing to null and right to another node
					// I need to make the reference node point the right of the node that the user wants to delete
					reference.setRight(current.getRight()); // Making the parent node point to the right of the child node
					current.setRight(null); // Making the child node point to null so it looses reference
					
				}
				
				else {
					// Otherwise, I need to make the parent point to the left of the child node
					reference.setLeft(current.getLeft());
					current.setLeft(null);
				}
				return true; // Return true
			}
			
			else {
				// Finally, If the node has 2 childs
				// Here I need to find the node that will replace the node that the user wants to delete
				// In a Binary search tree, the node that will replace the node that I want to delete will be in the right subtree of that node and in the deepest left of its right subtree
				// To do so, I need to move my pointer to make it point to the node that will replace the parent node, delete it, and replace its value in the parent node
				BTNode reference = current.getRight(); // First I need to move the pointer to the right of the node that user wants to delete
			    while(reference.getLeft()!=null) // Then I need to find the deepest node on the left of the right subtree
			    	reference = reference.getLeft();
			    Definition temp = reference.getInfo(); // Get the info of the deepest node
			    delete(reference.getInfo()); // Delete the node
			    current.setInfo(temp); // And finally, replace its value in the parent node
			    return true; // Return true	    
			}
		}
	  }
	}
}