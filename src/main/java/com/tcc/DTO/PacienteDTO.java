package com.tcc.DTO;

import java.io.Serializable;

import com.tcc.domain.Paciente;



public class PacienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idPaciente;
	private String descConvenio;
	private String nuInscricaoConvenio;
	private String nuTelefone;
	private String nome;
	private Long idUser;
	private String cpf;
	private String telefone;
	private String sexo;
	private Boolean compartilhaDados;
	
	public Boolean isCompartilhaDados() {
		return compartilhaDados;
	}
	public void setCompartilhaDados(Boolean compartilhaDados) {
		this.compartilhaDados = compartilhaDados;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getDescConvenio() {
		return descConvenio;
	}
	public void setDescConvenio(String descConvenio) {
		this.descConvenio = descConvenio;
	}
	public String getNuInscricaoConvenio() {
		return nuInscricaoConvenio;
	}
	public void setNuInscricaoConvenio(String nuInscricaoConvenio) {
		this.nuInscricaoConvenio = nuInscricaoConvenio;
	}
	public String getNuTelefone() {
		return nuTelefone;
	}
	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}
	
	public PacienteDTO() {
		
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public PacienteDTO(Paciente paciente) {
		this.descConvenio = paciente.getDescConvenio();
		this.idPaciente = paciente.getId();
		this.nome = paciente.getUsuario().getNome();
		this.nuInscricaoConvenio = paciente.getNuInscricaoConvenio();
		this.nuTelefone = paciente.getNuTelefone();
		this.idUser = paciente.getUsuario().getId();
		this.cpf = paciente.getUsuario().getCpf();
		this.telefone = paciente.getUsuario().getTelefone();
		this.sexo = String.valueOf(paciente.getUsuario().getSexo());
		this.compartilhaDados = paciente.isCompartilhaDados();
	}
	
	
	
}
