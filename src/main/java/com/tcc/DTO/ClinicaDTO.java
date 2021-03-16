package com.tcc.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class ClinicaDTO {

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo nome")
	@Size( max=255, message="o nome da clinica deve ter no máximo 255 caracteres")
	private String nome;
	
	private String dtAbertura;
	
	private String dtEncerramento;
	
	private EnderecoDTO endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtAbertura() {
		return dtAbertura;
	}

	public void setDtAbertura(String dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	public String getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(String dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public ClinicaDTO(Long id,	String nome, String dtAbertura, String dtEncerramento, EnderecoDTO endereco) {
		this.id = id;
		this.nome = nome;
		this.dtAbertura = dtAbertura;
		this.dtEncerramento = dtEncerramento;
		this.endereco = endereco;
	}

	public ClinicaDTO() {	}
	
	
}
