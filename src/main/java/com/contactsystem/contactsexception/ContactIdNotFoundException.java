package com.contactsystem.contactsexception;

public class ContactIdNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactIdNotFoundException(String message) {
		super(message);
	}

}
