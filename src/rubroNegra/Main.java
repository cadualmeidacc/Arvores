package rubroNegra;

public class Main {
	public static void main(String[] args) {
		BlackRed tree = new BlackRed();
	        
		tree.add(13);
	    tree.add(8);
	    tree.add(1);
	    tree.add(11);
	    tree.add(6);
	    tree.add(17);
	    tree.add(15);
	    tree.add(25);
	    tree.add(22);
	    tree.add(27);
	    
	    System.out.println(tree.search(13));
	    
	    tree.print();
	       
	   
	    
	    tree.delete(13);
	    
	    System.out.println(tree.search(13));
	    
	    tree.print();
	    
	   
	}
}