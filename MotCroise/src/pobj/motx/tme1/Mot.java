package pobj.motx.tme1;
import java.util.*;

/**
 * Representation class of a word 
 */
public class Mot {
	/** letters of the word */
	private List<Case> lettres;

	/**
	 * Builds an empty word 
	 */
	public Mot() {
		lettres = new ArrayList<Case>();
	}	
	
	@Override
	public String toString() {
		for (Case c : lettres) {
			System.out.print(c.getChar());
		}
		return "";
	}
	
	/**
	 * Calculates a length of this word
	 * @return the length of this word
	 */
	public int size() {
		return lettres.size();
	}
	
	/**
	 * Reaches this word
	 * @return this word
	 */
	public List<Case> getLettres() {
		return lettres;
	}
	
	/**
	 * Adds a copy of a box(letter or empty box) to a word
	 * @param uneCase a box
	 */
	public void ajoutCase(Case uneCase) {
		lettres.add(uneCase.copy());
	}
	
	/**
	 * Reinitializes this word
	 */
	public void clear() {
		lettres.clear();
	}
	
	/**
	 * Makes a copy of this word
	 * @return a copy of this word
	 */
	public Mot copy() {
		Mot m = new Mot();
		for (Case c : lettres) {
			m.ajoutCase(c);
		}
		return m;
	}
}