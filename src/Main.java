package tree;

import java.util.Random;
import java.util.function.Consumer;


public class Main {
	public static void main(String[] args) {

		BinaryTree<Integer> t = new BinaryTree<>();

		Random r = new Random();
		
		//Riempio l'albero con valori casuali
		for (int i = 0; i < 10; i++) {
			t.insert(r.nextInt(100));
		}
		
		//Stampa in ordine metodo 1
		t.inOrder((x) -> System.out.print(x.toString() + " "));
		
		System.out.println();
		
		//Stampa in ordine metodo 2
		t.inOrder();
		
		System.out.println();
		
		int numDaTrovare = 5;
		
		//Ricerca metodo 1
		System.out.println(t.search(numDaTrovare));

		//Ricerca metodo 2
		Cerca<Integer> c = new Cerca<>(numDaTrovare);
		t.inOrder(c);
		System.out.println("Trovato? " + c.result);
		
		//Ricerca metodo 3
		//Con stream
		System.out.println("Risultati trovati: " + t.stream()
			.filter((x) -> x.equals(numDaTrovare)? true :  false)
			.count());
		
		
		
		Max<Integer> max = new Max<>();
		
		t.inOrder(max);
		
		System.out.println(max.max);
	}
}

class Cerca<E> implements Consumer<E>{

	int obj;
	boolean result = false;
	
	public Cerca(int o) {
		obj = o;
	}
	
	@Override
	public void accept(E o) {
		Integer x = Integer.parseInt(o.toString());
		if(x.equals(obj))
			result = true;
			
	}	
}

class Max<E> implements Consumer<E>{

	int max = Integer.MIN_VALUE;

	@Override
	public void accept(E o) {
		Integer x = Integer.parseInt(o.toString());
		if(x.compareTo(max) > 0)
			max = x;
			
	}	
}