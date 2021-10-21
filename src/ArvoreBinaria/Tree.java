package ArvoreBinaria;

public class Tree {
	
	private No root;
	
	public Tree() {
		this.root = null;
	}
	
	public void insert (double value) {
		No neww = new No();
		
		neww.item = value;
		neww.right = null;
		neww.left = null;
		
		if(root == null)
			root = neww;
		else {
			No current = root;
			No previos;
			while(true) {
				previos = current;
				if(value <= current.item) {
					current = current.left;
					if(current == null) {
						previos.left = neww;
						return;
					}
				}else {
					current = current.right;
					if(current == null) {
						previos.right = neww;
						return;
					}
				}
			}
			
		} 
		
	}
	
	public No seach (double key) {

		if(root == null)
			return null;
		
		No current = root;
		while(current.item != key) {
			if(key < current.item)
				current = current.left;
			else
				current = current.right;
			if(current == null)
				return null;
		}
		return current;
	}
	
	public int grauNo (No current) {
		 if(current == null) 
		    return 0;
		 if((current.left != null && current.right == null) ||  current.left == null && current.right != null)
			 return 1;
		 if(current.left != null && current.right != null) 
			 return 2;
		 
		 return 0;
	}
	
	public int depth (double key) {
		int value = 0;
		
		if(root == null)
			return -1;
		
		No current = root;
		while(current.item != key) {
			if(key < current.item) {
				current = current.left;
				value ++;
			}
			else {
				current = current.right;
				value ++;
			}
		}
		
		return value;
		
	}
	
	public int levels (double key) {
		int value = 0;
		
		if(root == null)
			return -1;
		
		No current = root;
		while(current.item != key) {
			if(key < current.item) {
				current = current.left;
				value ++;
			}
			else {
				current = current.right;
				value ++;
			}
		}
		
		return value;
		
	}
	
	public No inversor () {
		
		
		
		
		return root;
	}
	
	public boolean remove (double value) {
		if(root == null)
			return false;
		
		No current = root;
		No dad = root;
		boolean son_left = true;
		
		
		while(current.item != value ) {
			dad = current;
			if(value < current.item) {
				current = current.left;
				son_left = true;
			}else {
				current = current.right;
				son_left = false;
			}
			if(current == null)
				return false;
		}
		
		if(current.left == null && current.right == null) {
			if(current == root)
				root = null;
			else if (son_left)
				dad.left = null;
			else dad.right = null;
		}
		
		else if (current.left == null ) {
			if(current == root)
				root = current.right;
			else if(son_left)
				dad.left = current.right;
			else
				dad.right = current.right;
		}else {
			No successor = no_successor(current);
			
			if(current == root)
				root = successor;
			else if (son_left)
				dad.left  = successor;
			else 
				dad.right = successor;
			
			successor.left = current.left;
		}
		return true;
	}
	
	// Metodo para ajudar na remoção
	public No no_successor(No delete) { 
		No dadSuccessor = delete;
	    No successor = delete;
	    No current = delete.right; 

	    while (current != null) { 
	    	dadSuccessor = successor;
	    	successor = current;
	    	current = current.left; 
	    } 
	   
	    if (successor != delete.right) { 
	    	dadSuccessor.left = successor.right; 

	    	successor.right = delete.right; 
	    }
	     	return successor;
	  }

	public int height (No current) {
		if(current == null || (current.left == null && current.right == null)) 
			return 0;
		else {
			if(height(current.left)> height(current.right)) 
				return (1 + height(current.left));
			else 
				return (1 + height(current.right));
			
		}
		
	}
	
	public int sheets(No current) {
	    if(current == null) 
	    	return 0;
	    if(current.left == null && current.right == null) 
	    	return 1;
	    return sheets(current.left) + sheets(current.right);
	  }
	
	public int countNo(No current) {
		if(current == null)
			return 0;
		else 
			return(1  + countNo(current.left) + countNo(current.right));
	}
		
	
	
	
	// Navegar pela arvore
	
	public void inOrdem(No current) {	
		if(current != null) {
			inOrdem(current.left);
			System.out.println(current.item + "");
			inOrdem(current.right);
		}
	}
	
	public void preOrdem(No current) {	
		if(current != null) {
			System.out.println(current.item + "");
			preOrdem(current.left);
			preOrdem(current.right);
		}
	}
	
	public void posOrdem(No current) {	
		if(current != null) {
			posOrdem(current.left);
			posOrdem(current.right);
			System.out.println(current.item + "");
		}
	}
	
	public void walk () {
		System.out.println("Em Ordem: ");
		inOrdem(root);
		System.out.println("Pre-Ordem: ");
		preOrdem(root);
		System.out.println("Pos-Ordem: ");
		posOrdem(root);
			
	}
}
