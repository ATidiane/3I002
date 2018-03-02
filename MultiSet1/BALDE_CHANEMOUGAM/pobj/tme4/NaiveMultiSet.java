package pobj.tme4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * Builds a list
 * @param <T> Generic Object
 */
public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

	/** The list of T elements */
	private List<T> list;

	/**
	 * Initializes this list
	 */
	public NaiveMultiSet() {
		list = new ArrayList<T>();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new NaiveMultiSetIterator();
	}
	
	/**
	 * Builds an iterator for this list
	 */
	private class NaiveMultiSetIterator implements Iterator<T> {
		
		/** the index of the actual element */
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < size();
		}

		@Override
		public T next() {
			return list.get(index++);
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		} 
		
	}
	
	public boolean add(T e, int count) {
		if (count == 0) { return false; }
		for (int i=0; i<count; i++) {
			list.add(e);
		}
		return true;
	}
	
	public boolean add(T e) {
		list.add(e);
		return true;
	}
	
	public boolean remove(Object e) {
		T e1 = (T) e;
		if (count(e1) != 0) {
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).equals(e)) {
					list.remove(i);
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean remove(Object e, int count) {
		T e1 = (T) e;
		if ((count(e1) != 0) && (count != 0) && (count(e1) >= count)) {
			for (int i=0; i<list.size(); i++) {
				if (count != 0) {
					if (list.get(i).equals(e)) {
						list.remove(i);
						count--;
					}
				} else {
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	public int count(T o) {
		int cpt = 0;
		for (T e : list) {
			if (e.equals(o)) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public void clear() {
		list.clear();
	}
	
	public int size() {
		return list.size();
	}
	
	public List<T> elements() {
		Set<T> s = new HashSet<>(list);
		List<T> elements = new ArrayList<T>(s);
		
		return elements;
	}
}