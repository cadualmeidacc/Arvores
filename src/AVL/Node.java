package AVL;

public class Node {
	private Node parent;
	private Node left;
	private Node right;
	private int value;
	private int avl;
	
	public Node (int value) {
		this.value = value;
		this.avl = 0;
	}
	
	// metodos
	
	public int addAVL() {
		avl += 1;
		return avl;
	}
	
	public int subAVL() {
		avl -= 1;
		return avl;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	public boolean hasBrother() {
		if(parent == null) 
			return false;
		else if(this == parent.getLeft() && parent.getRight() != null)
			return true;
		else if (this == parent.getRight() && parent.getLeft() != null)
			return false;
		else
			return false;
	}
	
	public boolean hasChild() {
		if(left != null || right != null)
			return true;
		else
			return false;
	}
	
	public Node getBrother() {
		if(parent == null)
			return null;
		else if(this == parent.getLeft())
			return parent.getRight();
		else 
			return parent.getLeft();
	}
	
	public void deleteChildNode (Node child) {
		if(child == null)
			return;
		else if(child == left)
			left = null;
		else if(child == right)
			right = null;
	}
	
	
	// gets
	
	public int getValue() {
		return value;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	} 
	
	public Node getParent() {
		return parent;
	}
	
	public int getAvl() {
		return avl;
	}
	
	// sets
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public void setAvl(int avl) {
		this.avl = avl;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}
