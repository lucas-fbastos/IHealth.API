package com.tcc.service.exceptions;

public class ObjetoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoInvalidoException(String message) {
		super(message);
	}
	
	
	public ObjetoInvalidoException(String message, Throwable cause) {
		super(message,cause);
	}
}
