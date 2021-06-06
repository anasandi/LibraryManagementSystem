package com.library.management.exception;

/**
 * This application exception indicates that adding new record is failing
 * @author hsuwai
 *
 */
public class PersistenceFailureException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceFailureException() {
    }

    public PersistenceFailureException(String msg) {
        super(msg);
    }
}
