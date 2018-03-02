package pobj.tme5;

import java.util.Collection;
import java.util.List;

/**
 * An interface!
 * @param <T> Generic Object
 */
public interface MultiSet<T> extends Collection<T> {
	/**
	 * @param e the element T to add
	 * @param count the number of occurrences of the element e to add
	 * @return True if the collection has been modified or False
	 */
	public boolean add(T e, int count);
	
	public boolean add(T e);
	
	public boolean remove(Object e);
	
	/**
	 * @param e the Object to remove
	 * @param count the number of occurrences of the object e to remove
	 * @return True if the collection has been modified or false
	 */
	public boolean remove(Object e, int count);
	
	/**
	 * @param o the element to count
	 * @return the number of occurrences of this element o in the map
	 */
	public int count(T o);
	
	public void clear();
	
	public int size();
	
	/**
	 * @return a list of elements(unique) of the map
	 */
	public List<T> elements();
	
	//public boolean isConsistent();
}
