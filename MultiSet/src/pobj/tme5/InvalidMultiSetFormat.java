package pobj.tme5;

public class InvalidMultiSetFormat extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5218462857672229237L;

	public InvalidMultiSetFormat(String message) {
		System.out.println("Message" + message);
	}
	
	public InvalidMultiSetFormat(String message, Throwable cause) {
		System.out.println("Message : " + message);
		System.out.println("Cause : " + cause);
	}
}
