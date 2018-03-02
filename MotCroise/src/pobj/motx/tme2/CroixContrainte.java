package pobj.motx.tme2;


/**
 * A constraint
 */
public class CroixContrainte implements IContrainte {
	/**Fist word index*/
	private int m1;
	/**index of crossover character for the first word*/
	private int c1;
	/**Second word index*/
	private int m2;
	/**index of crossover character for the second word*/
	private int c2;
	
	/**
	 * Builds a two-word constraint
	 * @param m1 index of word 1
	 * @param c1 crossover character for word 1
	 * @param m2 index of word 2
	 * @param c2 crossover character for word 2
	 */
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1 = m1;
		this.c1 = c1;
		this.m2 = m2;
		this.c2 = c2;
	}
	
	
	public int reduce(GrillePotentiel grille) {
		int sum = 0;
		Dictionnaire dico1 = grille.getMotsPot().get(m1);
		Dictionnaire dico2 = grille.getMotsPot().get(m2);
		EnsembleLettre l1 = dico1.calculeEL(c1);
		EnsembleLettre l2 = dico2.calculeEL(c2);
		EnsembleLettre s = l1.intersectionEL(l2);
		
		if (l1.size() > s.size()) {
			sum += dico1.filtreParIndexEL(c1, s);
		}
		
		if (l2.size() > s.size()) {
			sum += dico2.filtreParIndexEL(c2, s);
		}
		return sum;
	}
	
}
