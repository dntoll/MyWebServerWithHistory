package se.lnu.http;

public class NotADirectoryException extends Exception {

	public NotADirectoryException(String string) {
		super(string);
	}

	public NotADirectoryException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6619732026622856729L;

}
