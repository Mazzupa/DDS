import java.util.Random;
import java.util.function.Predicate;

import tree.*;

public class Main {
	public static void main(String[] args) {

		BinaryTree<Integer> t = new BinaryTree<>();

		Random r = new Random();
		
		for (int i = 0; i < 5; i++) {
			t.insert(r.nextInt(10));
		}
		
		System.out.println(t.search(5));
		
		t.inOrder((x) -> System.out.println(x.toString()));
		
		o o = new o(5);
		t.inOrder(o);
		System.out.println("Trovato? " + o.result);
		
		int y = 5;
		Predicate<Integer> search = (x) -> x.equals(y)? true :  false;
		
		System.out.println("Risultati trovati: " + t.stream()
			.filter(search)
			.count());
		
		Object[] a = t.toArray();
		
		for(Object i : a)
			System.out.println(i.toString());
		
	}
}

class o implements Operation{

	int obj;
	boolean result = false;
	
	public o(int o) {
		obj = o;
	}
	
	@Override
	public void perform(Object o) {
		Integer x = Integer.parseInt(o.toString());
		if(x.equals(obj))
			result = true;
			
	}	
}
