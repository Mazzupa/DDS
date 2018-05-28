import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

import tree.*;

public class Main {
	public static void main(String[] args) {

		BinaryTree<Integer> t = new BinaryTree<>();

		Random r = new Random();
		
		//Riempio l'albero con valori casuali
		for (int i = 0; i < 10; i++) {
			t.insert(r.nextInt(10));
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
		Esempio<Integer> c = new Esempio<>(numDaTrovare);
		t.inOrder(c);
		System.out.println("Trovato? " + c.result);
		
		//Ricerca metodo 3
		//Con stream
		System.out.println("Risultati trovati: " + t.stream()
			.filter((x) -> x.equals(numDaTrovare)? true :  false)
			.count());
	}
}

class Esempio<E> implements Consumer<E>{

	int obj;
	boolean result = false;
	
	public Esempio(int o) {
		obj = o;
	}
	
	@Override
	public void accept(E o) {
		Integer x = Integer.parseInt(o.toString());
		if(x.equals(obj))
			result = true;
			
	}	
}
