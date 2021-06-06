package com.library.management.exception;

/**
 * This application exception indicates that logged user is invalid for the operation.
 * @author hsuwai
 *
 */
public class UnauthorizedException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
