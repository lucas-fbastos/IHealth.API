package com.tcc.service.exceptions;

public class PerfilInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PerfilInvalidoException(String message) {
		super(message);
	}
	
	
	public PerfilInvalidoException(String message, Throwable cause) {
		super(message,cause);
	}

}
