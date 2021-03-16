package com.tcc.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class CredentialsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo E-mail")
	private String email;
	
	private String senha;
	
	public CredentialsDTO() {}
	
	public CredentialsDTO(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
