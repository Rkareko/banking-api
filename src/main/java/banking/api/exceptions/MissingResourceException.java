package banking.api.exceptions;

import org.apache.ibatis.javassist.bytecode.CodeAttribute.RuntimeCopyException;

public class MissingResourceException extends RuntimeCopyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7148389799253782390L;

	public MissingResourceException(String msg) {
		super(msg);
	}

}
