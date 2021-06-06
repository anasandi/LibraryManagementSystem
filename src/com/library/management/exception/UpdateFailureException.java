package com.library.management.exception;

/**
 * This application exception indicates that adding new book is failing.
 * @author hsuwai
 *
 */
public class UpdateFailureException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateFailureException() {
    }

    public UpdateFailureException(String msg) {
        super(msg);
    }
}
