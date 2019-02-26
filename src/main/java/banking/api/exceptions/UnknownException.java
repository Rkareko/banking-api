package banking.api.exceptions;

public class UnknownException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715684216301649597L;
	
	public UnknownException(String msg) {
		super(msg);
	}

}
