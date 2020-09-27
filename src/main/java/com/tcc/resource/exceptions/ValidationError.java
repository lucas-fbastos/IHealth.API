package com.tcc.resource.exceptions;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	
	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addFields( String fieldName, String fieldMessage) {
		errors.add(new FieldMessage(fieldName, fieldMessage));
	}

	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
		
	}

}
