package AVL;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		
		try {
			for(int n = 0; n < 1; n++){
				test2();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void test(){
		int[] list = new int[100];
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		for(int n = 0; n < 90; n++){
			list[n] = n+10;
		}
		
		AVLTree tree = new AVLTree();
		
		System.out.println("Adicionando------------------------------");
		for(int n = 0; n < 30; n++){
			int a = list[(int)(Math.random()*89)];
			list2.add(a);
			tree.insert(a);
		}
		System.out.println("list2=="+list2.toString());
		
		System.out.println("Excluindo------------------------------");
		for(int n = 0; n < 90; n++){
			int a = list[(int)(Math.random()*89)];
			list3.add(a);
			tree.deleteNode(a);
		}
		System.out.println("list3=="+list3.toString());
		
		printTree(tree,1);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
	}

	
	private static void test2() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		printTree(tree, 1);
		tree.insert(15);
		printTree(tree, 1);
		tree.insert(25);
		printTree(tree, 1);
		tree.insert(12);
		printTree(tree, 1);
		tree.insert(17);
		printTree(tree, 1);
		tree.insert(24);
		printTree(tree, 1);
		tree.insert(30);
		printTree(tree, 1);
		tree.insert(14);
		printTree(tree, 1);
		tree.insert(13);
		printTree(tree, 1);
		
		// fase 01
		tree.deleteNode(17);
		printTree(tree, 1);
		tree.deleteNode(20);
		printTree(tree, 1);
		
		System.out.print(tree.get2(13));
		
	}
	
	
	private static void printTree(AVLTree tree,int item){
		System.out.println("altura da arvore "+tree.treeLength()+" Modifique o nó para： "+item);
		System.out.println("Imprimindo a Arvore-----------------------------------------");
		tree.printByPhoto();
		System.out.println("Fim da Arvore-----------------------------------------");
		System.out.println();
		
	}
	
	
	
}