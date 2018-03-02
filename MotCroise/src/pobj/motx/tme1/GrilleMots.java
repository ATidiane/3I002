package pobj.motx.tme1;
import java.util.*;

/**
 * Representation class of a grid with its words 
 */
public class GrilleMots {
	/** Grid of GrilleMots */
	private Grille g;
	/** words of the grid */
	private List<Mot> mots;
	/** number of horizontal words in the grid */
	private int nbHorizontal;
	
	/**
	 * Adds the founded words to the list of words and
	 * calculates the number of horizontal words in the grid
	 * @param grille initial grid of GrilleMots
	 */
	public GrilleMots(Grille grille) {
		mots = new ArrayList<Mot>();
		g = grille;
		for (int i=0; i<g.nbLig(); i++) {
			chercheMots(getLig(i));
		}		
		nbHorizontal = mots.size();
		for (int i=0; i<g.nbCol(); i++) {
			chercheMots(getCol(i));
		}
	}
	
	/**
	 * Accesses the list of words of this grid
	 * @return the list of words of this grid
	 */
	public List<Mot> getMots() {
		return mots;
	}
	
	/**
	 * Accesses the number of horizontal words of this grid
	 * @return the number of horizontal words of this grid
	 */
	public int getNbHorizontal() {
		return nbHorizontal;
	}
	
	@Override
	public String toString() {
		//for (Mot m : mots) {
		//	m.toString();
		//	System.out.println();
		//}
	
		return g.toString();
	}
	
	/**
	 * Reaches a line
	 * @param lig index of the line
	 * @return the line of index lig of this grid
	 */
	private List<Case> getLig(int lig) {
		List<Case> ligne = new ArrayList<Case>();
		for (int i = 0; i<g.nbCol(); i++) {
			ligne.add(g.getCase(lig, i));
		}
		return ligne;
	}
	
	/**
	 * Reaches a column
	 * @param col index of the column
	 * @return the column of index col of this grid
	 */
	private List<Case> getCol(int col) {
		List<Case> colonne = new ArrayList<Case>();
		for (int i = 0; i<g.nbLig(); i++) {
			colonne.add(g.getCase(i, col));
		}
		return colonne;
	}
	
	/**
	 * searches the words in a line or a column
	 * @param cases list of cases (can be a line or a column)
	 */
	private void chercheMots(List<Case> cases) {
		Mot m = new Mot();
		for (int i=0; i<cases.size(); i++) {
			if (!(cases.get(i)).isPleine()) {
				m.ajoutCase(cases.get(i));
			} else {
				if (m.size() >= 2) {
					mots.add(m.copy());
				}	
				m.clear();
			}
		}
		if (m.size() >= 2) {
			mots.add(m.copy());
			m.clear();
		}
	}
	
	public GrilleMots fixer(int m, String soluce) {
		Grille gc = g.copy();
		Mot mot = mots.get(m);
		for (int i=0; i<mot.size(); i++) {
			int line = mot.getLettres().get(i).getLig();
			int column = mot.getLettres().get(i).getCol();
			gc.getCase(line, column).setChar(soluce.charAt(i));
			
		}
		
		return new GrilleMots(gc);		
	}
}