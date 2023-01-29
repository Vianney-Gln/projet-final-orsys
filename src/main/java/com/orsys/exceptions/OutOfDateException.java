package com.orsys.exceptions;

public class OutOfDateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfDateException(String message) {
		super(message);
	}
}
