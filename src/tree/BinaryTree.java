package tree;

import java.util.ArrayList;
import java.util.stream.Stream;

public class BinaryTree<E extends Comparable<? super E>> {

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

	public void insert(E key) {
		if (_root == null)
			_root = new _Node(key);
		else
			_insert(key, _root);
	}

	private void _preOrder(_Node root, Operation o) {
		if (root == null)
			return;
		o.perform(root);
		_preOrder(root.__left, o);
		_preOrder(root.__right, o);
	}

	public void preOrder(Operation operation) {
		_preOrder(_root, operation);
	}

	private void _postOrder(_Node root, Operation o) {
		if (root == null)
			return;
		_postOrder(root.__left, o);
		_postOrder(root.__right, o);
		o.perform(root);
	}

	public void postOrder(Operation operation) {
		_postOrder(_root, operation);
	}

	private void _inOrder(_Node root, Operation o) {
		if (root == null)
			return;
		_inOrder(root.__left, o);
		o.perform(root);
		_inOrder(root.__right, o);
	}

	public void inOrder(Operation operation) {
		_inOrder(_root, operation);
	}

	private void _toStream(_Node root, ArrayList<E> s) {

		if (root == null)
			return;

		_toStream(root.__left, s);
		s.add(root.__key);
		_toStream(root.__right, s);
	}

	public Stream<E> stream() {
		ArrayList<E> s = new ArrayList<>();
		_toStream(_root, s);
		return s.stream();
	}

	public Object[] toArray() {
		ArrayList<E> r = new ArrayList<>();
		_toStream(_root, r);
		
		return r.toArray();
	}

	public E getMin() {
		return null;
		// TODO
	}

	public E getMax() {
		return null;
		// TODO
	}

	public int getSum() {
		return 0;
		// TODO
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

	public boolean search(E key) {
		return _search(_root, key);
	}
}
