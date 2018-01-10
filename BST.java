/** @ Namratha, Pranathi, Nithin , Keerthimanu
 *  Binary search tree
 **/

//package cs6301.g00;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
	    this.left = left;
	    this.right = right;
        }
    }
    
    Entry<T> root;
    int size;
    Stack<Entry<T>> stack;

    public BST() {
	root = null;
	size = 0;
    }

    
    Entry<T> find(T x){
    	stack = new Stack<Entry<T>>(); //stack for storing the predecessor of x
    	stack.push(null);
    	return find(root, x);
    }
    
    //helper method
    Entry<T> find(Entry<T> t, T x){
    	if(t == null || t.element == null ||t.element.equals(x))
    		return t;
    	
    	while(true){
    		if(x.compareTo(t.element) < 0) //x is less than the present element
    		{
    			if(t.left == null || t.left.element == null) //the element is not present in the tree
    				break;
    			else{
    				stack.push(t);
    				t = t.left;  //traversing to the left sub tree of present element
    				}
    		}
    		else if(x.compareTo(t.element) == 0) //x is the present element
    			return t;
    		else // x is greater than the present element
    		{
    			if(t.right == null || t.right.element == null)
    				break;
    			else{ 
    				stack.push(t);
    				t = t.right; // traversing to the right sub tree of present element
    				}
    		}
    	}
    	return t;
    }

    // returns if the element is present in the tree or not.
    public boolean contains(T x) {
	  Entry<T> t = find(x);
	  return t != null && t.element.compareTo(x) == 0;
    }

    // Is there an element that is equal to x in the tree?
    // Element in tree that is equal to x is returned, null otherwise.
     
    public T get(T x) {
	   Entry<T> t = find(x);
	   if(t != null && t.element.compareTo(x) == 0) //element is present in the tree
	       return t.element;
	   else return null;
	   
    }

    //Adds x to tree. 
    //If tree contains a node with same key, replaces element by x.
    //Returns true if x is a new element added to tree.
    
    public boolean add(T x) {
	  return add(new Entry<T>(x,null,null));
    }
    
    //helper function
    boolean add(Entry<T> z){
    	if(root == null)
  	  {
  		  root = z;
  		  size++;
  		  return true;
  	  }
  	  Entry<T> t = find(z.element); //returns the position where z is to be inserted(present element)
  	  if(z.element.compareTo(t.element) == 0) //z is already present in the tree
  	  {
  		  t.element = z.element;
  		  return false;
  	  }
  	  else if(z.element.compareTo(t.element) < 0) // z is less than the present element so add to the left of the tree
  	  {
  		  t.left = z;
  		  size++;
  		  return true;
  	  }
  	  else // z is greater than the present element so add to the right of present element
  	  {
  		  t.right = z;
  		  size++;
  		  return true;
  	  }
    }

    
    
    public T remove(T x){
    	if(root == null) 
	    	return null;
    	Entry<T> t = find(x); 
    	if(t.element.compareTo(x) != 0) //the element is not present in the tree
	    	return null;
	    T result = t.element;
	    if(t.left == null || t.right == null) // one of the children is null
	    	bypass(t);
	    	
	    else {   //both the children are present so replacing it with minimum element from right subtree
	    	stack.push(t);
	    	Entry<T> minRight = find(t.right, t.element);
	    	t.element = minRight.element;
	    	bypass(minRight);
	    	
	    }
	    size--;
	    return result;
    }

    
     
    public class BFSIterator implements Iterator<T>{
    	Stack<Entry<T>> st = new Stack<Entry<T>>();
		
		public BFSIterator(){
		pushAll(root);}
		
		
		public boolean hasNext() {
			return !st.isEmpty();
		}

		
		public T next() {
			Entry<T> t = st.pop();
			if(t.right != null)
			pushAll(t.right);
			return t.element;
		}
		
		void pushAll(Entry<T> t){ //pushes the left subtree into the tree
             while(t != null){
				st.push(t);
				t = t.left;
			}
		}
		
    }
    //returns the iterator to the tree
    public Iterator<T> iterator() {
	
    	Iterator<T> it = new BFSIterator();
    	return it;
    }
    
   
    //replaces the node with one of it's children
    void bypass(Entry<T> t){
    	Entry<T> pt = stack.peek();
    	Entry<T> c = t.left == null?t.right:t.left;
    	
    	if(pt == null)
    		root = c;
    	else if(pt.left == t)
    		pt.left = c;
    	else
    		pt.right = c;
    }

    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
	
	
	/*int n = 10000;
	 Integer[] A = new Integer[n];
		for(int i =0; i<n;i++)
			A[i] = i;
		Shuffle s = new Shuffle();
		Timer ti = new Timer();
		s.shuffle(A);
		
		for(int i=0;i<n;i++){
			t.add(A[i]);
		}
		
		ti.start();
		t.get(1);
		ti.end();
		
		System.out.println("Addition time "+ti);*/
		
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            } 
            
        }
        
        //iterator
        Iterator<Integer> it = t.iterator();
        System.out.println("Running iterator");
        while(it.hasNext())
        	System.out.println(it.next());
       in.close();
    }


   void inOrder(Entry<T> t, ArrayList<Comparable> arr){
	   if(t != null){
		   inOrder(t.left,arr);
		   arr.add(t.element);
		   inOrder(t.right,arr);
	   }
   }
    
    public Comparable[] toArray() {
	Comparable[] arr = new Comparable[size];
	ArrayList<Comparable> arr1 =new ArrayList<Comparable>();
	inOrder(root, arr1);
	arr = arr1.toArray(arr);
	return arr;
    }

    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }

}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
