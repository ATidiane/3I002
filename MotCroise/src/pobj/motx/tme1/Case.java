package pobj.motx.tme1;

/**
 * Representation class of a box in a grid
 */
public class Case {
	
	/** line of the box */
	private int lig;
	/** column of the box */ 
	private int col;
	/** Character of the box ('*', ' ' or a letter) */
	private char c;
	
	/**
	 * Builds a box with the specified coordinates
	 * @param lig index of the line
	 * @param col index of the column
	 * @param c character of the box
	 */
	public Case(int lig, int col, char c){
		this.lig = lig;
		this.col = col;
		this.c = c;
	}
	
	/**
	 * Accesses the index of the line of the box
	 * @return the index of the line of the box
	 */
	public int getLig() { return lig; }
	
	/**
	 * Accesses the index of the column of the box
	 * @return the index of the column of the box
	 */
	public int getCol() { return col; }
	
	/**
	 * Accesses the character of the box
	 * @return the character of the box
	 */
	public char getChar() { return c; }
	
	/**
	 * Assigns a character to this box
	 * @param c Character
	 */
	public void setChar(char c) { this.c = c; }
	
	/**
	 * Tests whether the character is empty (box is white)
	 * @return true or false
	 */
	public boolean isVide() { return (c == ' '); }
	
	/**
	 * Tests whether the character is full (box is black)
	 * @return true or false
	 */
	public boolean isPleine() { return (c == '*'); }
	
	/**
	 * Makes a copy of this box
	 * @return a copy of this box
	 */
	public Case copy() {
		return new Case(lig ,col , c);
	}
	
	@Override public boolean equals(Object o) {
		if (o == null) { return false; }
		if (o.getClass() != this.getClass()) { return false; }
		Case box = (Case) o;
		if ((lig == box.getLig()) && (col == box.getCol()) && (c == box.getChar())) {
			return true;
		}
		return false;
	}
	
}