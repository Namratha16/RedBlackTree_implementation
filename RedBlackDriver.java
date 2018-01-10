//@ Namratha, Pranathi, Nithin , Keerthimanu
import java.util.Iterator;
import java.util.Scanner;



public class RedBlackDriver {

	public static void main(String[] args){
		 RedBlackTree<Integer> rbt = new RedBlackTree<>();
		/* int n = 1000;
		 Integer[] A = new Integer[n];
			for(int i =0; i<n;i++)
				A[i] = i;
			Shuffle s = new Shuffle();
			Timer t = new Timer();
			s.shuffle(A);
			
			for(int i=0;i<n;i++){
				rbt.add(A[i]);
			}
			
			t.start();
			rbt.add(new Integer(500));
			t.end();
			
			System.out.println("Addition time "+t);*/
		  
		 Scanner in = new Scanner(System.in);
	      while(in.hasNext()) {
	          int x = in.nextInt();
	          if(x>0){
	           System.out.print("Add " + x + " : ");
	              rbt.add(x);
	             rbt.printTree();
	              }
	          else if(x == 0){
	        	  Comparable[] arr = rbt.toArray();
	                System.out.print("Final: ");
	                for(int i=0; i<arr.length; i++) {
	                	if(arr[i] != null){ //does not print the nil nodes
	                    System.out.print(arr[i] + " ");}
	                }
	                System.out.println();
	          }
	          else {return;}
	          }
	      //iterator
          Iterator<Integer> it = rbt.iterator();
        System.out.println("Running iterator");
          while(it.hasNext())
          	System.out.println(it.next());
			
	      in.close();
			
	}
}
