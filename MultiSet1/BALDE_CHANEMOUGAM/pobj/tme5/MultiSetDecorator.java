package pobj.tme5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.AbstractCollection;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T> {

	MultiSet<T> decorated;

	public MultiSetDecorator(MultiSet<T> decorated) {
		this.decorated = decorated;
	}
	
	public boolean add(T e, int count) {
		if (decorated.add(e, count)) {
			assert isConsistent();
			return true;
		}
		assert isConsistent();
		return false;
	}

	public boolean add(T e) {
		if (decorated.add(e)) {
			assert isConsistent();
			return true;
		}
		assert isConsistent();
		return false;
	}

	public boolean remove(Object e) {
		if (decorated.remove(e)) {
			assert isConsistent();
			return true;
		}
		assert isConsistent();
		return false;
	}

	public boolean remove(Object e, int count) {
		if (decorated.remove(e, count)) {
			assert isConsistent();
			return true;
		}
		assert isConsistent();
		return false;
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

	@Override
	public boolean isConsistent() {
		return decorated.isConsistent();
	}

	/*public boolean isConsistent() {
		List<T> elem = decorated.elements();
		int som = 0;
		for(T e : elem) {
			int val = decorated.count(e);
			if (val > 0) {
				som += val;
				continue;
			}
			return false;
		}
		if (som == size())
			return true;
		
		return false;
		
	}*/
	
	
}
