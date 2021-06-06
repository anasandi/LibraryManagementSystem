package com.library.management.exception;

/**
 * This application exception indicates that books are not found or 
 * something occurs while fetching books
 * @author hsuwai
 *
 */
public class BooksNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BooksNotFoundException() {
    }

    public BooksNotFoundException(String msg) {
        super(msg);
    }
}
