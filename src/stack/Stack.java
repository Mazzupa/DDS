package stack;

public class Stack<E> {
	
	private class _Elem{
		private E __info;
		private _Elem __next;
		
		public _Elem(E info) {
			__info = info;
		}
	}
	
	int _size;
	int _sizeMax;
	_Elem _entry;
	
	public Stack() {
		_size = 0;
		_sizeMax = Integer.MAX_VALUE;
	}
	
	public Stack(int sizeMax) {
		_size = 0;
		_sizeMax = sizeMax;
	}
	
	public void push(E info) {
		if(isFull()) throw new RuntimeException("Stack Overflow");
		_Elem t = new _Elem(info);
		t.__next = _entry;
		_entry = t;
		_size++;
	}
	
	public E pop() {
		if(isEmpty()) throw new RuntimeException("Empty Stack");
		_size--;
		_Elem r = _entry;
		_entry = r.__next;
		return r.__info;
	}
	
	public int size() {
		return _size;
	}
	
	public boolean isEmpty() {
		return _size==0? true : false;
	}
	
	public boolean isFull() {
		return _size==_sizeMax? true : false;
	}
}
