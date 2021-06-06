package com.library.management.exception;

/**
 * This application exception indicates that saving user is failing.
 * @author hsuwai
 *
 */
public class RegisterFailureException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterFailureException() {
    }

    public RegisterFailureException(String msg) {
        super(msg);
    }
}
