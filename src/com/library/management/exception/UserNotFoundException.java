package com.library.management.exception;

/**
 * This application exception indicates that provided user is not found.
 * @author hsuwai
 *
 */
public class UserNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
