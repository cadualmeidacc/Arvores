package ArvoreBinaria;
public class Main {

	public static void main(String[] args) {
		Tree arvore = new Tree ();
		No no = new No();
		
		arvore.insert(10);
		arvore.insert(30);
		arvore.insert(8);
		arvore.insert(2);
		arvore.insert(50);
		
		//boolean existe = arvore.seach(10);
		//System.out.println(existe);
		
		no = arvore.seach(10);
		System.out.println(no.item);
		
		//arvore.walk();
		
		//double profundidade =  arvore.depth(8);
		//System.out.println(profundidade);
		
		//double nivel =  arvore.levels(8);
		//System.out.println(nivel);
		
		//arvore.remove(10);
		
		//arvore.walk();
		
		
		
	}

}
