package com.tcc.service.exceptions;

public class UserDeslogadoException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public UserDeslogadoException(String message) {
		super(message);
	}
	
	
	public UserDeslogadoException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
