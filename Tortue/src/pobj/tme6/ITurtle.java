package pobj.tme6;

/**
 * Interface pour une tortue logo. 
 *
 */
public interface ITurtle {

	/**
	 * Avance la tortue d'une certaine distance dans la direction courante
	 * Trace une ligne entre le point de départ et le point d'arrivée si le crayon
	 * est baissé.
	 * @param length distance à avancer
	 */
	public void move(int length);

	/**
	 * Tourne la tortue sur elle-même, en chageant la direction courante.
	 * @param angle angle en degrés à ajouter à la direction courante.
	 */
	public void turn(int angle);
	
	/**
	 * Lève le crayon.
	 * Les appels suivants à move ne laisseront pas de trace.
	 */
	public void up();
	
	/**
	 * Baissse le crayon.
	 * Les appels suivants à move laisseront une trace.
	 */
	public void down();
}
