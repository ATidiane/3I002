package pobj.motx.tme2;

/**
 * An interface of constraint
 */
public interface IContrainte {
	/**
	 * @param grille a grid
	 * @return the number of the removed words
	 */
	int reduce(GrillePotentiel grille);
}
