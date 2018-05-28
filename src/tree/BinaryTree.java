/**
 * @author Mazzucchetti Patrick
 * @Matricola 1053212
 * 
 * Implementazione di una struttura dati dinamica ad albero di ricerca binario
 */

package tree;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class BinaryTree<E extends Comparable<? super E>> {

	/**
	 * Nodo dell'albero con chiave E e puntatori ai sottoalberi destro e sinistro
	 */
	private class _Node {
		private E __key;
		private _Node __left;
		private _Node __right;

		_Node(E key) {
			__key = key;
		}

		public String toString() {
			return __key.toString();
		}
	}

	/**
	 * Entry root dell'albero
	 */
	private _Node _root;

	private void _insert(E key, _Node root) {
		if (key.compareTo(root.__key) < 0)
			if (root.__left == null)
				root.__left = new _Node(key);
			else
				_insert(key, root.__left);

		else if (root.__right == null)
			root.__right = new _Node(key);
		else
			_insert(key, root.__right);
	}

	/**
	 * Inserisce un nuovo nodo nell'albero in maniera ordinata
	 * 
	 * @param key
	 *            Valore da aggiungere
	 */
	public void insert(E key) {
		if (_root == null)
			_root = new _Node(key);
		else
			_insert(key, _root);
	}

	private void _preOrder(_Node root, Consumer<E> o) {
		if (root == null)
			return;
		o.accept(root.__key);
		_preOrder(root.__left, o);
		_preOrder(root.__right, o);
	}

	/**
	 * Visita dell'albero secondo il Pre ordine
	 * 
	 * @param operation
	 *            Operazione(Tramite interfaccia funzionale) da eseguire
	 */
	public void preOrder(Consumer<E> operation) {
		_preOrder(_root, operation);
	}

	/**
	 * Stampa dell'albero secondo il Pre ordine
	 */
	public void preOrder() {
		preOrder((x) -> System.out.print(x.toString() + " "));
	}

	private void _postOrder(_Node root, Consumer<E> o) {
		if (root == null)
			return;
		_postOrder(root.__left, o);
		_postOrder(root.__right, o);
		o.accept(root.__key);
	}

	/**
	 * Visita dell'albero secondo il Post ordine
	 * 
	 * @param operation
	 *            Operazione(Tramite interfaccia funzionale) da eseguire
	 */
	public void postOrder(Consumer<E> operation) {
		_postOrder(_root, operation);
	}

	/**
	 * Stampa dell'albero secondo il Post ordine
	 */
	public void postOrder() {
		postOrder((x) -> System.out.print(x.toString() + " "));
	}

	private void _inOrder(_Node root, Consumer<E> o) {
		if (root == null)
			return;
		_inOrder(root.__left, o);
		o.accept(root.__key);
		_inOrder(root.__right, o);
	}

	/**
	 * Visita dell'albero in ordine
	 * 
	 * @param operation
	 *            Operazione(Tramite interfaccia funzionale) da eseguire
	 */
	public void inOrder(Consumer<E> operation) {
		_inOrder(_root, operation);
	}

	/**
	 * Stampa dell'albero secondo il Post ordine
	 */
	public void inOrder() {
		inOrder((x) -> System.out.print(x.toString() + " "));
	}

	private void _toStream(_Node root, ArrayList<E> s, TipoVisita tipo) {

		if (root == null)
			return;
		
		if(tipo.equals(TipoVisita.IN_ORDER))
			inOrder((x)->s.add(x));
		
		if(tipo.equals(TipoVisita.POST_ORDER))
			postOrder((x)->s.add(x));
		
		if(tipo.equals(TipoVisita.PRE_ORDER))
			preOrder((x)->s.add(x));
		
	}

	/**
	 * @return L'albero sotto forma di Stream
	 */
	public Stream<E> stream(TipoVisita tipo) {
		ArrayList<E> s = new ArrayList<>();
		_toStream(_root, s, tipo);
		return s.stream();
	}
	
	public Stream<E> stream() {
		return stream(TipoVisita.IN_ORDER);
	}

	/**
	 * @return L'albero sotto forma di Array
	 */
	public Object[] toArray(TipoVisita tipo) {
		ArrayList<E> r = new ArrayList<>();
		_toStream(_root, r, tipo);

		return r.toArray();
	}
	
	public Object[] toArray(){
		return toArray(TipoVisita.IN_ORDER);
	}

	private boolean _search(_Node root, E key) {
		if(root == null)
			return false;
		
		int comp = root.__key.compareTo(key);
		if(comp == 0)
			return true;
		else if(comp < 0)
			return _search(root.__right, key);
		else
			return _search(root.__left, key);
	}

	/**
	 * 
	 * @param key
	 *            Elemento da cercare
	 * @return true se key è presente nell'albero, false altrimenti
	 */
	public boolean search(E key) {
		return _search(_root, key);
	}

	/**TODO*/
	/**
	 * @param key
	 *            Elemento da eliminare
	 */
	public void remove(E key) {
		if (!search(key))
			throw new RuntimeException("Elemento non presente");
	}
}
