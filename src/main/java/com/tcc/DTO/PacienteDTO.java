package com.tcc.DTO;

import java.time.LocalDate;

import com.tcc.domain.Paciente;



public class PacienteDTO extends UserDTO {

	private static final long serialVersionUID = 1L;

	private Long idPaciente;
	private LocalDate dtNascimento;
	private String descConvenio;
	private String nuInscricaoConvenio;
	private String nuTelefone;
	private EnderecoDTO endereco;
	
	
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
	
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
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
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
	public PacienteDTO() {
		
	}
	
	
	public PacienteDTO(Paciente paciente) {
		this.descConvenio = paciente.getDescConvenio();
		this.dtNascimento = paciente.getDtNascimento();
		this.endereco = new EnderecoDTO(paciente.getEndereco());
		this.idPaciente = paciente.getId();
		this.nome = paciente.getUsuario().getNome();
		this.nuInscricaoConvenio = paciente.getNuInscricaoConvenio();
		this.nuTelefone = paciente.getNuTelefone();
		this.idUser = paciente.getUsuario().getId();
		this.cpf = paciente.getUsuario().getCpf();
		this.telefone = paciente.getUsuario().getTelefone();
		this.sexo = String.valueOf(paciente.getUsuario().getSexo());
	}
	
	
	
}
