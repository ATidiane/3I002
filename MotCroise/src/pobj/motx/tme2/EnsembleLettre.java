package pobj.motx.tme2;

import java.util.*;

/**
 * A set of letter
 */
public class EnsembleLettre {
	
	/** List of characters*/
	private List<Character> caracteres;
	
	/**
	 * Instantiates the character list
	 */
	public EnsembleLettre() {
		caracteres = new ArrayList<Character>();
	}
	
	/**
	 * Adds a character to the list of characters
	 * @param c a character
	 */
	public void add(char c) {
		if (!contains(c)) {
			caracteres.add(c);
		}
	}
	
	/**
	 * Tests whether the character c belongs to the list of characters
	 * @param c a character
	 * @return True of False
	 */
	public boolean contains(char c) {
		if (!caracteres.contains(c)) { return false; }
		return true;
	}
	
	/**
	 * Calculates a length of the characters list
	 * @return the length of the characters list
	 */
	public int size(){
		return caracteres.size();
	}
	
	/**
	 * Accesses to the list of characters
	 * @return the list of characters
	 */
	public List<Character> getCaracteres() {
		return caracteres;
	}
	
	/**
	 * Builds a list of characters that contains the intersection 
	 * of this list of characters and another list of characters
	 * @param l2 the other list of characters
	 * @return the intersection (list) of the two lists of character
	 */
	public EnsembleLettre intersectionEL(EnsembleLettre l2) {
		EnsembleLettre l1copy = this.copy(); 
	    l1copy.getCaracteres().retainAll(l2.getCaracteres());
		return l1copy;
	}
	
	/**
	 * Builds a copy of the list of characters
	 * @return a copy of the list of characters
	 */
	public EnsembleLettre copy() {
		EnsembleLettre lcopy = new EnsembleLettre();
		for (Character c : caracteres) {
			lcopy.add(c);
		}
		return lcopy;
	}
	
	@Override public String toString() {
		for (Character c : caracteres) {
			System.out.print(c);
		}
		System.out.println();
		return "";
	}
}
