package pobj.tme5;

import java.util.Collection;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Builds a map
 * @param <T> Generic Object
 */
public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	
	/** The map */
	Map<T, Integer> hash;
	
	/** 
	 * The number of elements of the map includes
	 * the number of occurrences of each element of the map
	 * */
	private int size;
	
	/**
	 * Initializes the map named hash and its size
	 */
	public HashMultiSet () {
		hash = new HashMap<T, Integer>();
		size=0;
	}
	
	/**
	 * Builds a map that contains the specified collection
	 * @param c a collection
	 */
	public HashMultiSet(Collection<T> c) {
		hash = new HashMap<T, Integer>();
		for (T e : c) {
			add(e);
		}
	}
	
	public Iterator<T> iterator() {
		return new HashMultiSetIterator ();
	}
	
	/**
	 * Builds an iterator for this map
	 */
	private class HashMultiSetIterator implements Iterator<T> {
		/** The actual element */
		private T data ;
		
		/** the number of occurrences of the actual element data*/
		private int count;
		
		/** the index of the actual element */
		private int index;
		
		/** An iterator */
		private Iterator<Entry<T, Integer>> it;
		
		/**
		 * Initializes the iterator, count and the index.
		 */
		public HashMultiSetIterator() {
			it = hash.entrySet().iterator();
			count = 0;
			index = 0;
		}
		
		@Override
		public boolean hasNext() {
			return index < size();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		} 
			
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
		
			if (count == 0) {
				Map.Entry<T, Integer> entry = it.next();
				data = entry.getKey();
				count = entry.getValue();
			}
			index++;
			count--;
		
			return data;
		}
	}
	
	public boolean add(T e, int count) {
		if (count < 0) 
			throw new IllegalArgumentException("Argument Negatif, Must be positif!");
		
		if (count != 0) {
			if (hash.containsKey(e)) {
				hash.put(e, count(e)+count);
			} else {
				hash.put(e,  count);
			}
			size += count;
			return true;
		}
		return false;
	}
	
	public boolean add(T e) {
		if (hash.containsKey(e)) {
			hash.put(e, count(e)+1);
		} else {
			hash.put(e, 1);
		}
		size++;
		return true;
	}
	
	public boolean remove(Object e) {
		T e1 = (T) e;
		if (hash.containsKey(e1)) {
			hash.put((T) e, count(e1)-1);
			size--;
			if (count(e1) == 0) {
				hash.remove(e1);
			}
			return true;
		}
		return false;
	}
	
	public boolean remove(Object e, int count) {
		T e1 = (T) e;
		if (count < 0)
			throw new IllegalArgumentException("Negatif argument, Must be positif!");
		if (count(e1) < count)
			throw new IllegalArgumentException("The removal will lead to a negatif number!");
		
		if ((count(e1) != 0) && (count != 0)) {
			hash.put((T) e, count(e1)-count);
			size -= count;
			if (count(e1) == 0) {
				hash.remove(e1);
			}
			return true;
		}
		return false;
	}
	
	public int count(T o) {
		if (hash.containsKey(o)) 
			return hash.get(o);
		return 0;
	}
	
	public void clear() {
		hash.clear();
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public Map<T, Integer> getHash() {
		return hash;
	}
	
	public List<T> elements() {
		/*List<T> elements = new ArrayList(this);
		Set<T> s = new HashSet<>(elements);
		elements = new ArrayList(s);*/
		List<T> elements = new ArrayList<>(hash.keySet());
		
		return elements;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[");
		Iterator<Entry<T, Integer>> it = hash.entrySet().iterator();
	    while(it.hasNext()){
	    	Entry<T, Integer> e = it.next();
	    	T data = e.getKey();
	    	int val = e.getValue();
	    	b.append(data + ":" + val + "; ");
	    }
	    b.deleteCharAt(b.length()-2);
	    b.deleteCharAt(b.length()-1);
	    b.append("]");
		return b.toString();
	}
	
}
