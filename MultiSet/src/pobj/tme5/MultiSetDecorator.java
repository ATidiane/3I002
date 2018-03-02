package pobj.tme5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T> {

	MultiSet<T> decorated;

	public MultiSetDecorator(MultiSet<T> decorated) {
		this.decorated = decorated;
	}
	
	public boolean isConsistent() {
		List<T> elem = elements();
		int som = 0;
		for(T e : elem) {
			int val = count(e);
			if (val <= 0) {
				return false;
			}
			som += val;
		}
		
		if (som == size())
			return true;
		
		return false;
		
	}
	
	public boolean add(T e, int count) {
		boolean r = decorated.add(e, count);
		assert isConsistent();
		return r;
	}

	public boolean add(T e) {
		boolean r = decorated.add(e);
		assert isConsistent();
		return r;
	}

	public boolean remove(Object e) {
		boolean r = decorated.remove(e);
		assert isConsistent();
		return r;
	}

	public boolean remove(Object e, int count) {
		boolean r = decorated.remove(e, count);
		assert isConsistent();
		return r;
	}

	public int count(T o) {
		return decorated.count(o);
	}

	public void clear() {
		decorated.clear();
	}

	public int size() {
		return decorated.size();
	}

	public List<T> elements() {
		return decorated.elements();
	}
	
	public boolean isEmpty() {
		return decorated.isEmpty();
	}

	public boolean contains(Object o) {
		return decorated.contains(o);
	}

	public Iterator<T> iterator() {
		return decorated.iterator();
	}
	
	public boolean containsAll(Collection<?> c) {
		return decorated.containsAll(c);
	}

	public boolean addAll(Collection<? extends T> c) {
		return decorated.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return decorated.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return decorated.retainAll(c);
	}

	public boolean equals(Object o) {
		return decorated.equals(o);
	}

	public int hashCode() {
		return decorated.hashCode();
	}
	
	public String toString() {
		return decorated.toString();
	}
	
	/*public boolean isConsistent() {
		Iterator<Entry<T, Integer>> it = hash.entrySet().iterator();
		int som = 0;
		while (it.hasNext()) {
			Entry<T, Integer> e= it.next();
			int val = e.getValue();
			if (val > 0) {
				som += val;
				continue;
			}
			return false;
		}
		if (som == size())
			return true;
		
		return false;
	}
	
	public boolean isConsistent() {
		List<T> traitements = new ArrayList<>();
		Iterator<T> it = iterator();
		T data = null;
		int som = 0;
		while (it.hasNext()) {
			if (traitements.contains(data))
				continue;
			
			data = it.next();
			int val = count(data);
			if (val <= 0) {
				return false;
			}
			som += val;
			traitements.add(data);
		}
		if (som == size())
			return true;
		return false;
	}*/
	
}
