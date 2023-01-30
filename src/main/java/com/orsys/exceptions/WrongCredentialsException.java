package com.orsys.exceptions;

public class WrongCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongCredentialsException(String message) {
		super(message);
	}

}
