package com.tcc.resource.exceptions;

import java.io.Serializable;

public class FieldMessage  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeCampo;
	private String mensagem;
	
	public String getNomeCampo() {
		return nomeCampo;
	}
	public void setNomeCampo(String fieldName) {
		this.nomeCampo = fieldName;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String message) {
		this.mensagem = message;
	}
	
	public FieldMessage(String fieldName, String message) {
		super();
		this.nomeCampo = fieldName;
		this.mensagem = message;
	}
	
	public FieldMessage() {
		
	}
	
	
}
