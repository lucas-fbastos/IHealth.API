package com.tcc.resource.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.ObjetoInvalidoException;
import com.tcc.service.exceptions.PerfilInvalidoException;
import com.tcc.service.exceptions.TemporalidadeException;
import com.tcc.service.exceptions.UserDeslogadoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityException e, HttpServletRequest req){
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NoElementException.class)
	public ResponseEntity<StandardError> NoElementException(NoElementException e, HttpServletRequest req){
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest req){
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação dos dados",System.currentTimeMillis());
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addFields(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(UserDeslogadoException.class)
	public ResponseEntity<StandardError> StandardError(UserDeslogadoException e, HttpServletRequest req){
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),"Usuário deslogado",System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(TemporalidadeException.class)
	public ResponseEntity<StandardError> StandardError(TemporalidadeException e, HttpServletRequest req){
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ObjetoInvalidoException.class)
	public ResponseEntity<StandardError> StandardError(ObjetoInvalidoException e, HttpServletRequest req){
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(PerfilInvalidoException.class)
	public ResponseEntity<StandardError> StandardError(PerfilInvalidoException e, HttpServletRequest req){
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	

}
