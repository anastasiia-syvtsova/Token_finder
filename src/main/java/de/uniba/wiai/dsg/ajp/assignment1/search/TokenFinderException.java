package de.uniba.wiai.dsg.ajp.assignment1.search;

/**
 * Wrapper Exception for all errors occurring during search.
 */
public class TokenFinderException extends Exception {

	private static final long serialVersionUID = 1L;

	public TokenFinderException() {
		super();
	}

	public TokenFinderException(String message) {
		super(message);
	}

	public TokenFinderException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenFinderException(Throwable cause) {
		super(cause);
	}

	protected TokenFinderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
