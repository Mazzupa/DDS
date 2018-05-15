package priorityQueue;

import java.util.Collection;
import java.util.Iterator;

public class PriorityQueue<E> implements java.util.Queue<E> {

	public static final int MAX_PRIORITY = 64;
	public static final int MIN_PRIORITY = 0;

	class Elem implements Comparable<Elem> {

		private E __info;
		private Elem __next;
		private int __priority;

		public Elem(E elem, int priority) {
			__info = elem;
			if (priority < MIN_PRIORITY || priority > MAX_PRIORITY)
				throw new RuntimeException("Priority out of bound [0,64]");
			else
				__priority = priority;
		}

		public Elem(E elem) {
			this(elem, MIN_PRIORITY);
		}
		
		public Elem() {
			
		}

		@Override
		public int compareTo(PriorityQueue<E>.Elem o) {
			return this.__priority - o.__priority;
		}
		
		@Override
		public String toString() {
			return "[info: " + __info.toString() + " PRIORITY: " + __priority + "]";
		}
	}

	private Elem _entry;
	private int _size;

	public PriorityQueue(int size) {
		_size = size;

		for (int i = 0; i < size; i++) {
			add(null);
		}
	}

	public PriorityQueue() {
		// empty constructor
	}

	@Override
	public void clear() {
		_entry = null;
	}

	@Override
	public boolean contains(Object elem) {
		if (isEmpty())
			return false;

		Elem t = _entry;
		while (t.__next != null) {
			if (t.__info.equals(elem))
				return true;
			t = t.__next;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return _size == 0 ? true : false;
	}

	@Override
	public int size() {
		return _size;
	}

	@Override
	public boolean add(E elem) {
		return add(elem, MIN_PRIORITY);
	}

	public boolean add(E elem, int priority) {
		
		if(_entry == null) {
			_entry = new Elem(elem, priority);
			_size++;
			return true;
		}
		
		Elem t = _entry;
		
		while (t.__next != null) {
			t = t.__next;
		}
		t.__next = new Elem(elem, priority);
		_size++;
		return true;
	}

	@Override
	public E remove() {
		
		if(isEmpty()) throw new RuntimeException("Empty queue");
		
		_size--;
		Elem r = _entry;
		_entry = _entry.__next;
		return r.__info;
		
	}
	
	
	/**
	 * TODO UNIMPLEMENTED METHODS
	 */
	@Override
	public boolean remove(Object elem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}
}
