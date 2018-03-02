package pobj.motx.tme2;
import pobj.motx.tme1.*;
import java.util.*;

/**
 * 
 */

public class GrillePotentiel {
	
	/** a grid that contains a list of all its words*/
	private GrilleMots grille;
	/** a dictionary (initial)*/
	private Dictionnaire dico;
	/** a list of dictionaries that contains the potential words of each
	 * word of the dictionary
	 */ 
	private List<Dictionnaire> motsPot;
	/* A list of constraints*/
	private List<IContrainte> contraintes;
	
	/**
	 * Builds a list of dictionaries that contains all the potential words (dictionary)
	 * of each word of the grid and a list of constraints for all the words of the grid
	 * @param grille a grid
	 * @param dico the original dictionary
	 */
	public GrillePotentiel(GrilleMots grille, Dictionnaire dico) {
		this.grille = grille;
		this.dico = dico;
		motsPot = new ArrayList<Dictionnaire>();
		contraintes = new ArrayList<IContrainte>();
		
		for (int h=0; h<grille.getMots().size(); h++) {
			Mot m = grille.getMots().get(h);
 			Dictionnaire dicopy = dico.copy();
			dicopy.filtreLongueur(m.size());
			filtrage(m, dicopy);
			motsPot.add(dicopy);
			
			if (h >= grille.getNbHorizontal()) { continue; }
			
			fillConstraints(grille, m, h, contraintes);
			
		}
		propage();
	}
	
	public GrillePotentiel(GrilleMots grille, Dictionnaire dico, List<Dictionnaire> listPot) {
		this.grille = grille;
		this.dico = dico;
	    motsPot = new ArrayList<Dictionnaire>();
		contraintes = new ArrayList<IContrainte>();
		
		for (int i=0; i<grille.getMots().size(); i++) {
			Dictionnaire dicopy = listPot.get(i).copy();
			Mot m = grille.getMots().get(i);
			filtrage(m, dicopy);
			motsPot.add(dicopy);
			
			if (i >= grille.getNbHorizontal()) { continue; }
			
			fillConstraints(grille, m, i, contraintes);
			
		}
		propage();
	}
	
	/**
	 * Accesses the list of constraints
	 * @return the list of constraints
	 */
	public List<IContrainte> getContraintes() {
		return contraintes;
	}
	
	/**
	 * Tests whether a word does not have any potential word in the dictionary
	 * @return True or False
	 */
	public boolean isDead() {
		for (Dictionnaire d : motsPot) {
			if(d.size() == 0) { return true; }
		}
		return false;
	}
	
	/**
	 * Accesses the list of dictionaries
	 * @return the list of dictionaries
	 */
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	/**
	 * @param m
	 * @param soluce
	 * @return
	 */
	public GrillePotentiel fixer(int m, String soluce) {
		return new GrillePotentiel(grille.fixer(m, soluce), dico, motsPot);
	}
	
	/**
	 * Accesses to the grid
	 * @return the grid
	 */
	public GrilleMots getGrille() {
		return grille;
	}
	
	/**
	 * Removes all the words that does not respect a constraint 
	 * @return True of False
	 */
	public boolean propage() {
		while(true) {
			int som = 0;
			for (IContrainte ic : contraintes) {
				som += ic.reduce(this);
			}
			
			if (isDead()) {
				return false;
			}
			if (som == 0) {
				return true;
			}
		}
	}
	
	public Dictionnaire getDico() {
		return dico;
	}

	public void filtrage(Mot m, Dictionnaire d) {
		for (int j=0; j<m.size(); j++) {
			Case c = m.getLettres().get(j);
			if (!c.isVide()) {
				d.filtreParLettre(c.getChar(), j);
			}
		}
	}
	
	public void fillConstraints(GrilleMots g, Mot m, int h, List<IContrainte> constraints) {
		for (int v=g.getNbHorizontal(); v<g.getMots().size(); v++) {
			Mot mv = g.getMots().get(v);
			for (int i=0; i<m.size(); i++) {
				boolean stop = false;
				for (int j=0; j<mv.size(); j++) {
					if (m.getLettres().get(i).equals(mv.getLettres().get(j))) {
						if (m.getLettres().get(i).isVide()) {
							constraints.add(new CroixContrainte(h, i, v, j));
							stop = true;
							break;
						}
					}
				}
				if (stop) { break; }
			}
		}
	}
	
	public Dictionnaire makeOneDico (List<Dictionnaire> listPot) {
		Dictionnaire all = new Dictionnaire();
		for (Dictionnaire d : listPot) {
			for (String mot : d.getMots()) {
				all.add(mot);
			}
		}
		return all;
	}
}
