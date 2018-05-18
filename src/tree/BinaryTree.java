/**
 * @author Mazzucchetti Patrick
 * @Matricola 1053212
 * 
 * Implementazione di una struttura dati dinamica ad alberi di ricerca binario
 */

package tree;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

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
			return __key + "";
		}
	}

	/**
	 * Entry root dell'albero
	 */
	private _Node _root;

	private void _insert(E key, _Node root) {
		if (key.compareTo(root.__key) > 0) {
			if (root.__right == null)
				root.__right = new _Node(key);
			else
				_insert(key, root.__right);
		} else {
			if (root.__left == null)
				root.__left = new _Node(key);
			else
				_insert(key, root.__left);
		}
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

	/**
	 * Classe privata che implementa l'interfaccia funzionale Consumer per
	 * permettere la stampa dei nodi
	 */
	private class _stampa implements Consumer<E> {

		@Override
		public void accept(E t) {
			System.out.println(t.toString());
		}
	};

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
		preOrder(new _stampa());
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
		postOrder((new _stampa()));
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
		inOrder(new _stampa());
	}

	private void _toStream(_Node root, ArrayList<E> s) {

		if (root == null)
			return;

		_toStream(root.__left, s);
		s.add(root.__key);
		_toStream(root.__right, s);
	}

	/**
	 * @return L'albero sotto forma di Stream
	 */
	public Stream<E> stream() {
		ArrayList<E> s = new ArrayList<>();
		_toStream(_root, s);
		return s.stream();
	}

	/**
	 * @return L'albero sotto forma di Array
	 */
	public Object[] toArray() {
		ArrayList<E> r = new ArrayList<>();
		_toStream(_root, r);

		return r.toArray();
	}

	private boolean _search(_Node root, E key) {
		if (root != null) {
			if (root.__key.equals(key))
				return true;

			if (root.__key.compareTo(key) < 0)
				return _search(root.__left, key);
			else
				return _search(root.__left, key);
		}
		return false;
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

	/**
	 * @param key
	 *            Elemento da eliminare
	 */
	public void remove(E key) {
		if (!search(key))
			throw new RuntimeException("Elemento non presente");

	}

}
