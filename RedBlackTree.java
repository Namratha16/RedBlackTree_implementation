//@ Namratha, Pranathi, Nithin , Keerthimanu
import java.util.Iterator;

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {
    static class Entry<T> extends BST.Entry<T> {
        boolean isRed;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            isRed = true;
        }
    }

    RedBlackTree() {
	super();
    }
    
    //NIL class to represent the leaf nodes
    static class NIL<T> extends Entry<T>{
        NIL() {
			super(null, null, null);
			isRed = false;
		}
     }

    NIL<T> nil = new NIL<>();
    
    //returns the element if present 
    Entry<T> find(T x){
    	return (Entry<T>)super.find(x);
    }

    // returns if the element is present or not in the tree
    public boolean contains(T x){
    	return super.contains(x);
    }
    
    // Is there an element that is equal to x in the tree?
    // Element in tree that is equal to x is returned, null otherwise.
    
    public T get(T x){
    	return super.get(x);
    }
    
    //Adds x to tree. 
    //If tree contains a node with same key, replaces element by x.
    //Returns true if x is a new element added to tree.
    
    public boolean add(T x){
    	BST.Entry<T> z = new Entry<T>(x,nil,nil);
    	if(super.add(z)){
    		Entry<T> t = find(x);
 		    repair(t); //for balancing condition
 			((Entry<T>)root).isRed = false;
    		return true;
    	}
    	return false;
    }
    
    //rotates the node to the right and returns the new node
    Entry<T> rotateRight(Entry<T> t){
    	assert t != nil && ((Entry<T>)t.left).isRed == true;
    	boolean lchild = true; 
    	Entry<T> p_t = (Entry<T>)stack.peek(); //parent of t
    	if(p_t != null){ // if t is not root 
    	if(p_t.right == (Entry<T>)t)
    		lchild = false; // t is the right child of p_t
    	Entry<T> p = (Entry<T>)t.left; //right rotation
    	t.left = p.right;
    	p.right = t;
    	if(lchild)
    		p_t.left = p; //establishing the link with parent of t
    	else
    		p_t.right = p;
    	return p;}
    	else{ //it t is the root
    		Entry<T> p = (Entry<T>)t.left;
        	t.left = p.right;
        	p.right = t;
        	root = p; // assigning the root to p
        	return p;
    	}
    }
    
    //rotated the node to the left and returns the new node
    Entry<T> rotateLeft(Entry<T> t){
    	assert t != nil && ((Entry<T>)t.right).isRed == true;
    	
    	boolean lchild = true;
    	Entry<T> p_t = (Entry<T>)stack.peek(); //parent of t
    	if(p_t != null){ // if t is not root
    	if(p_t.right == (Entry<T>)t)
    		lchild = false;  // t is the right child of p_t
    	Entry<T> p = (Entry<T>)t.right; //left rotation
    	t.right = p.left;
    	p.left = t;
    	if(lchild)
    		p_t.left = p; //establishing the link with parent of t
    	else
    		p_t.right = p;
    	return p;}
    	else{ //it t is the root
    		Entry<T> p = (Entry<T>)t.right;
        	t.right = p.left;
        	p.left = t;
        	root = p;
        	return p;
    	}
    }
   
   void repair(Entry<T> t){
	   if(t == (Entry<T>)root) // t is the root
		   return;
	   Entry<T> p_t = (Entry<T>)stack.pop(); //parent of t
	   
	   if(p_t == root)
		   return;
	   
	   Entry<T> g_t = (Entry<T>)stack.pop(); // grand parent of t
	   Entry<T> u_t; //uncle of t
	   
	    u_t = g_t.left == p_t ? (Entry<T>)g_t.right : (Entry<T>)g_t.left; //assigning the value of uncle
	    
	    while(t.isRed == true ){ 
		 if(p_t == root || p_t.isRed == false)
			 return;
		 else if(u_t.isRed == true){ //case 1
			p_t.isRed = false;
			 u_t.isRed = false;
			 g_t.isRed = true;
			 t = g_t;
			 continue;
		 }
		 else if(u_t.isRed == false){ //case 2 & 3
			 if(g_t.left == p_t && p_t.left == t){ //case 2a
                 p_t = rotateRight(g_t);
				 p_t.isRed = false;
				 ((Entry<T>)(p_t.right)).isRed = true;
				 return;
				 }
			 else if(g_t.right == p_t && p_t.right == t){ //case 2b
					 p_t = rotateLeft(g_t);
					 p_t.isRed = false;
				     ((Entry<T>)(p_t.left)).isRed = true;
				     return;
				 }
			 else if(g_t.left == p_t && p_t.right == t){ //case 3a
				 rotateLeft(p_t);
				 p_t = rotateRight(g_t); //case 2a
				 p_t.isRed = false;
				 ((Entry<T>)(p_t.right)).isRed = true;
				 return;
				 
			     }
			 else { //case 3b
				 rotateRight(p_t);
				 p_t = rotateLeft(g_t); //case 2b
				 p_t.isRed = false;
			     ((Entry<T>)(p_t.left)).isRed = true;
			     return;
			 }
			 }
		 else{ }
	   }
   }
   
   public Iterator<T> iterator() {
		return super.iterator();
   }
   
   
   public void printTree() {
	   
		System.out.print("[" + size + "]");
		printTree((Entry<T>)root);
		System.out.println();
	    }
        
   // Inorder traversal of tree
   void printTree(Entry<T> node) {
	   
   if(node != null) {
		printTree((Entry<T>)node.left);
		if(node == nil){} // does not print the leaf nodes
		else{
		String red = "red";
		String black = "black";
		String color = node.isRed ? red:black;
		System.out.print(" " + node.element + " "+ color+ ",");}//prints the element and the colour of the node
		printTree((Entry<T>)node.right);
		}
	    }
  
}


