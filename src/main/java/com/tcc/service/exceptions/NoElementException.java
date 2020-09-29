package com.tcc.service.exceptions;

public class NoElementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoElementException(String message) {
		super(message);
	}
	
	
	public NoElementException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
