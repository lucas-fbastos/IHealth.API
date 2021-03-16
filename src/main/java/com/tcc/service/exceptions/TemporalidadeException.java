package com.tcc.service.exceptions;

public class TemporalidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TemporalidadeException(String message) {
		super(message);
	}
	
	
	public TemporalidadeException(String message, Throwable cause) {
		super(message,cause);
	}
}
