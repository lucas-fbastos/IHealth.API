package com.tcc.DTO;

import java.io.Serializable;

public class PacienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomePaciente;
	private String cpf;
	
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public PacienteFilter(String nomePaciente, String cpf) {
		super();
		this.nomePaciente = nomePaciente;
		this.cpf = cpf;
	}
	
	
}
