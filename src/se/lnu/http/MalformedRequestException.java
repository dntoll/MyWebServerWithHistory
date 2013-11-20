package se.lnu.http;

public class MalformedRequestException extends Exception {

	public MalformedRequestException(String requestString) {
		super(requestString);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 347832735672941517L;

}
