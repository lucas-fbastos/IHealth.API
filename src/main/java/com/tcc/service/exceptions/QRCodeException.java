package com.tcc.service.exceptions;

public class QRCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QRCodeException(String message) {
		super(message);
	}
	
	
	public QRCodeException(String message, Throwable cause) {
		super(message,cause);
	}
}
