package com.library.management.exception;

public class UserRoleNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRoleNotFoundException() {
    }

    public UserRoleNotFoundException(String msg) {
        super(msg);
    }
}
