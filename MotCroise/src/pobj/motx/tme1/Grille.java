package pobj.motx.tme1;

/**
 * Representation class of a grid 
 */
public class Grille {
	/** number of lines of the grid  */
	private int hauteur;
	/** number of columns of the grid */
	private int largeur;
	/** table of grid cells*/
	private Case[][] c;
	
	/**
	 * Builds a grid with specified coordinates
	 * @param hauteur initial number of lines of the grid
	 * @param largeur initial number of columns of the grid
	 */
	public Grille(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		c = new Case[hauteur][largeur];
		for(int i=0; i<hauteur; i++) {
			for(int j=0; j<largeur; j++) {
				c[i][j] = new Case(i,j,' ');
			}
		}
	}
	
	/**
	 * Accesses a box in the grid
	 * @param lig line of the box
	 * @param col column of the box
	 * @return the compartment of coordinate lig ad col
	 */
	public Case getCase(int lig, int col) {
		return c[lig][col];
	}
	
	@Override 
	public String toString() {
		return GrilleLoader.serialize(this,false);
	}
	
	/**
	 * @return the number of lines of the grid
	 */
	public int nbLig() { return hauteur; }
	
	/**
	 * @return the number of columns of the grid
	 */
	public int nbCol() { return largeur; }
	
	/**
	 * @return a copy of the grid
	 */
	public Grille copy() {
		Grille g = new Grille(hauteur, largeur);
		for (int i=0; i<hauteur; i++) {
			for(int j=0; j<largeur; j++) {
				g.c[i][j] = getCase(i,j).copy();
			}
		}
		return g;
	}
}