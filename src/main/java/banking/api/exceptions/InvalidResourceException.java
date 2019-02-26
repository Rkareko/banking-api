package banking.api.exceptions;

public class InvalidResourceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4832733464521762940L;

	public InvalidResourceException(String msg) {
		super(msg);
	}

}
