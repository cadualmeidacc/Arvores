package AVL;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AVLTree {
	
	private Node root;
	
	public void insert (int item) {
		//boolean balan = true;
		
		if(root == null) 
			root = new Node(item);
		else {
			Node parent = root;
			Node temp = root;
			
			do {
				parent = temp;
				if(item < parent.getValue()) 
					temp = parent.getLeft();
				else if(item > parent.getValue())
					temp = parent.getRight();
				else 
					break;
			}while (temp != null);
			
			if(temp != null)
				temp.setValue(item);
			else {
				Node node = new Node(item);
				node.setParent(parent);
				
				if(item < parent.getValue()) {
					parent.setLeft(node);
					if(parent.getRight() == null) {
						balance (node);
					}
					else 
						parent.subAVL();
				}else {
					parent.setRight(node);
					
					if(parent.getLeft() == null) {
						balance(node);
					} else
						parent.addAVL();
						
				}
			}

		}
	/*	if(balan) {
			System.out.println("Está balanceado!!");
		}else {
			System.out.println("Não Está balanceado !");
		}*/
	}
	
	private void balance (Node node) {
		Node parent = node.getParent();
		Node node_middle = node;
		Node node_prev = node;
		
		Boolean avl = true;
		
		do {
			if(node_middle == parent.getLeft() && (-1 <= parent.getAvl()-1 && parent.getAvl()-1  <= 1)) {
				parent.subAVL();
				node_prev = node_middle;
				node_middle = parent;
				
				if(parent != null && parent.getAvl() == 0) 
					parent = null;
				else
					parent = parent.getParent();
				
			}else if(node_middle == parent.getRight() && (-1 <= parent.getAvl()+1 && parent.getAvl()+1 <= 1)) {
				parent.addAVL();
				node_prev = node_middle;
				node_middle = parent;
				
				if(parent != null && parent.getAvl() == 0)
					parent = null;
				else
					parent = parent.getParent();
				
			} else 
				avl = false;
			
			
		}while(parent != null && avl);
		
		if(parent == null)
			return;
		
		chooseCalculation(parent, node_middle, node_prev);
	}
	
	private int chooseCalculation(Node parent, Node node_middle,Node node_prev) {
		
		int height = 0;
		
		if(node_middle == parent.getLeft() && node_prev == node_middle.getLeft()){
			if(node_middle.getAvl() == -1)
				height = -1;
			LeftLeftRotate(node_middle);
			System.out.print( "Direita   /  ");
		}else if(node_middle == parent.getLeft() && node_prev == node_middle.getRight()){
			height = -1;
			LeftRightRotate(node_middle);
			System.out.print(" Duplamente para Direita   / ");
		}else if(node_middle == parent.getRight() && node_prev == node_middle.getLeft()){
			height = -1;
			RightLeftRotate(node_middle);
			System.out.print(" Duplamente para Esquerda   /");
		}else if(node_middle == parent.getRight() && node_prev == node_middle.getRight()){
			if(node_middle.getAvl() == 1)
				height = -1;
			RightRightRotate(node_middle);
			System.out.print( "Esquerda   /  ");
		}
		
		return height;
		
		
	}
	
	public Node get(int item) {
		if(root == null)
			return null;
		if(item == root.getValue())
			return root;
		else {
			Node node = root;
			
			do {
				if(item < node.getValue())
					node = node.getLeft();
				else if(item > node.getValue())
					node = node.getRight();
				else
					return node;
			}while(node != null);
		}
		
		return null;
	}
	
	public boolean get2(int item) {
		if(root == null)
			return false;
		if(item == root.getValue())
			return true;
		else {
			Node node = root;
			
			do {
				if(item < node.getValue())
					node = node.getLeft();
				else if(item > node.getValue())
					node = node.getRight();
				else
					return true;
			}while(node != null);
		}
		
		return false;
	}
	
	public int treeLength(){
		return getLength(root);
	}
	
	private int getLength(Node node){
		if(node == null)
			return 0;
		int num = 1;
		List<Node> l = new ArrayList<Node>();
		l.add(node);
		num = itrableNode(num, l);
		return num;
	}
	
	private int itrableNode(int num, List<Node> list){
		List<Node> newlist = new ArrayList<Node>();
		
		list.forEach(new Consumer<Node>() {
			@Override
			public void accept(Node t) {
				if(t.getLeft() != null){
					newlist.add(t.getLeft());
				}
				if(t.getRight() != null){
					newlist.add(t.getRight());
				}
			}
		});
		
		if(newlist.size() > 0){
			num += 1;
			num = itrableNode(num,newlist);
		}
		return num;
	}

	public void printByPhoto(){
		
		if(root == null){
			return;
		}
		int length = treeLength();
		int blank = countoutBlank(length, 1);
		List<Node> l = new ArrayList<Node>();
		l.add(root);
		printRow(length,blank,1,l);
	}
	
	private int countoutBlank(int length, int row){
		int half = (int)Math.pow(2, length-1-row);
		int blank = 0;
		if(length == 2){
			blank = 2;
		}else if(length > 2){
			if(half == 2){
				blank = 6;
			}else{
				blank = (int) (Math.pow(2, length-2-row) * 6 + (Math.pow(2, length - 2-row) - 1) * 2);
			}
		}else{
			blank = 0;
		}
		return blank;
	}
	
	private void printRow(int length, int blank, int row, List<Node> list){
		
		for(int n = 0; n < blank; n++){
			System.out.print(" ");
		}
		
		StringBuffer x = new StringBuffer();
		for(int n = 0; n < blank*2; n++){
			x.append(" ");
		}
		x.append("  ");
		StringBuffer y = new StringBuffer();
		for(int n = 0; n < blank*2; n++){
			y.append(" ");
		}
		y.append("  ");
		
		List<Node> newlist = new ArrayList<Node>();
		if(row == 1){
			System.out.print(root.getValue());
			System.out.println("");
			printRow(length,countoutBlank(length, row+1),row+1,list);
		}else{
			for(Node t : list){
				if(t.getLeft() != null){
					newlist.add(t.getLeft());
					System.out.print(t.getLeft().getValue()+x.toString());
				}else if(t.getValue() != 0 || row <= length){
					newlist.add(new Node(0));
					System.out.print("x "+x.toString());
				}
				if(t.getRight() != null){
					newlist.add(t.getRight());
					System.out.print(t.getRight().getValue()+y.toString());
				}else if(t.getValue() != 0 || row <= length){
					newlist.add(new Node(0));
					System.out.print("x "+y.toString());
				}
			}
			System.out.println("");
			if(newlist.size() > 0)
				printRow(length,countoutBlank(length, row+1),row+1,newlist);
		}
	}

	private void LeftLeftRotate(Node node){
		
		Node parent = node.getParent();
		
		if(parent.getParent() != null && parent == parent.getParent().getLeft()){
			node.setParent(parent.getParent());
			parent.getParent().setLeft(node);
		}else if(parent.getParent() != null && parent == parent.getParent().getRight()){
			node.setParent(parent.getParent());
			parent.getParent().setRight(node);
		}else{
			root = node;
			node.setParent(null);
		}
		parent.setParent(node);
		parent.setLeft(node.getRight());
		if(node.getRight() != null)
			node.getRight().setParent(parent);
		node.setRight(parent);
		
		if(node.getAvl() == -1){
			parent.setAvl(0);
			node.setAvl(0);
		}else if(node.getAvl() == 0){
			parent.setAvl(-1);
			node.setAvl(1);
		}
	}
	
	private void RightRightRotate(Node node){
		
		Node parent = node.getParent();
		
		if(parent.getParent() != null && parent == parent.getParent().getLeft()){
			node.setParent(parent.getParent());
			parent.getParent().setLeft(node);
		}else if(parent.getParent() != null && parent == parent.getParent().getRight()){
			node.setParent(parent.getParent());
			parent.getParent().setRight(node);
		}else{
			root = node;
			node.setParent(null);
		}
		parent.setParent(node);
		parent.setRight(node.getLeft());
		if(node.getLeft() != null)
			node.getLeft().setParent(parent);
		node.setLeft(parent);
		
		if(node.getAvl() == 1){
			node.setAvl(0);
			parent.setAvl(0);
		}else if(node.getAvl() == 0){
			parent.setAvl(1);
			node.setAvl(-1);
		}
	}

	private void LeftRightRotate(Node node){
		
		Node parent = node.getParent();
		Node child = node.getRight();
		
		if(!child.hasChild()){
			node.setAvl(0);
			parent.setAvl(0);
		}else if(child.getAvl() == -1){
			node.setAvl(0);
			parent.setAvl(1);
		}else if(child.getAvl() == 1){
			node.setAvl(-1);
			parent.setAvl(0);
		}else if(child.getAvl() == 0){
			node.setAvl(0);
			parent.setAvl(0);
		}
		child.setAvl(0);
		
	
		parent.setLeft(child);
		node.setParent(child);
		node.setRight(child.getLeft());
		if(child.getLeft() != null)
			child.getLeft().setParent(node);
		child.setLeft(node);
		child.setParent(parent);
		
		if(parent.getParent() != null && parent == parent.getParent().getLeft()){
			child.setParent(parent.getParent());
			parent.getParent().setLeft(child);
		}else if(parent.getParent() != null && parent == parent.getParent().getRight()){
			child.setParent(parent.getParent());
			parent.getParent().setRight(child);
		}else{
			root = child;
			child.setParent(null);
		}
		parent.setParent(child);
		parent.setLeft(child.getRight());
		if(child.getRight() != null)
			child.getRight().setParent(parent);
		child.setRight(parent);
		
		
	}

	private void RightLeftRotate(Node node){
		
		Node parent = node.getParent();
		Node child = node.getLeft();
		
		if(!child.hasChild()){
			node.setAvl(0);
			parent.setAvl(0);
		}else if(child.getAvl() == -1){
			node.setAvl(1);
			parent.setAvl(0);
		}else if(child.getAvl() == 1){
			node.setAvl(0);
			parent.setAvl(-1);
		}else if(child.getAvl() == 0){
			parent.setAvl(0);
			node.setAvl(0);
		}
		child.setAvl(0);
		
		parent.setRight(child);
		node.setParent(child);
		node.setLeft(child.getRight()); 
		if(child.getRight() != null)
			child.getRight().setParent(node);
		child.setRight(node);
		child.setParent(parent);
		
		if(parent.getParent() != null && parent == parent.getParent().getLeft()){
			child.setParent(parent.getParent());
			parent.getParent().setLeft(child);
		}else if(parent.getParent() != null && parent == parent.getParent().getRight()){
			child.setParent(parent.getParent());
			parent.getParent().setRight(child);
		}else{
			root = child;
			child.setParent(null);
		}
		parent.setParent(child);
		parent.setRight(child.getLeft());
		if(child.getLeft() != null)
			child.getLeft().setParent(parent);
		child.setLeft(parent);
		
	}

	
	public void deleteNode(int item){
		
		Node node = get(item);
		if(node == null)
			return;
		Node parent = node.getParent();
		if(!node.hasChild()){
			if(parent == null){
				root = null;
				return;
			}
			if(node.hasBrother()){
				if(node == parent.getLeft())
					isBalance(node, 1);
				else
					isBalance(node, -1);
				parent.deleteChildNode(node);
			}else{
				deleteAvl(node);
				parent.deleteChildNode(node);
			}
		}else if(node.getLeft() != null && node.getRight() == null){
			if(parent == null){
				root = node;
				return;
			}
			if(node == parent.getLeft()){
				parent.setLeft(node.getLeft());
			}else{
				parent.setRight(node.getLeft());
			}
			node.getLeft().setParent(parent);
			deleteAvl(node.getLeft());
		}else if(node.getLeft() == null && node.getRight() != null){
			if(parent == null){
				root = node;
				return;
			}
			if(node == parent.getRight()){
				parent.setRight(node.getRight());
			}else{
				parent.setLeft(node.getRight());
			}
			node.getRight().setParent(parent);
			deleteAvl(node.getRight());
		}
		else{
			Node last = findLastNode(node);
			int tmp = last.getValue();
			deleteNode(last.getValue());
			node.setValue(tmp);
		}
		node = null;//GC
	}
	
	private void isBalance(Node node, int avl){
		
		Node parent = node.getParent();
		if(avl == 1){
			if(parent.getAvl() + 1 > 1){
				deleteAvl(node);
			}else{
				parent.addAVL();
			}
		}else if(avl == -1){
			if(parent.getAvl() - 1 < -1){
				deleteAvl(node);
			}else{
				parent.subAVL();
			}
		}
	}
	
	private Node findLastNode(Node n){
		
		Node last = null;
		Node node = n.getLeft();
		if(node != null){
			do{
				last = node;
				node = node.getRight();
			}while(node != null);
		}
		return last;
	}
	
	private void deleteAvl(Node node){
		
		Node node_middle = node;
		Node parent = node_middle.getParent();
		Node node_prev = node_middle;
		boolean avl = true;
		
		do{
			node_prev = node_middle;
			if(node_middle == parent.getLeft() && (parent.getAvl() + 1 <= 1)){
				if(parent.getAvl() == 0){
					parent.addAVL(); 
					return;
				}
				parent.addAVL();
				node_middle = parent;
				parent = node_middle.getParent();
			}else if(node_middle == parent.getRight() && (parent.getAvl() - 1 >= -1)){
				if(parent.getAvl() == 0){
					parent.subAVL();
					return;
				}
				parent.subAVL();
				node_middle = parent;
				parent = node_middle.getParent();
			}else{
				
				Node middle = node_middle.getBrother();
				Node child = middle.getAvl() == -1 ? middle.getLeft() : (middle.getAvl() == 1 ? middle.getRight() : (parent.getAvl() == -1 ? middle.getLeft() : middle.getRight()));
				int height = chooseCalculation(parent, middle, child);
				if(height == 0)
					return;
				node_middle = parent.getParent();
				parent = node_middle.getParent();
			}
		}while(parent != null && avl);
	}

}
