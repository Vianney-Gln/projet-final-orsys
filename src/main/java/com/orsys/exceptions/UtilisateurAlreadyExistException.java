package com.orsys.exceptions;

public class UtilisateurAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilisateurAlreadyExistException(String message) {
		super(message);
	}

}
