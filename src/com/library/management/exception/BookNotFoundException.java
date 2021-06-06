package com.library.management.exception;

/**
 * This application exception indicates that a book is not found.
 * @author hsuwai
 *
 */
public class BookNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException() {
    }

    public BookNotFoundException(String msg) {
        super(msg);
    }
}
