package com.tcc.DTO;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.tcc.domain.Endereco;

public class EnderecoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(max=100, message="Tamanho máximo de 100 caracteres")
	private String descBairro;
	
	@Size(max=100, message="Tamanho máximo de 100 caracteres")
	private String descRua;
	
	@Size(max=150, message="Tamanho máximo de 150 caracteres")
	private String noCidade;
	
	@Size(max=2, message="Tamanho máximo de 2 caracteres")
	private String noEstado;
	
	@Size(max=8, message="Tamanho máximo de 8 caracteres")
	private String nuCep;
	
	private String descComplemento;
	
	private Integer numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescBairro() {
		return descBairro;
	}

	public void setDescBairro(String descBairro) {
		this.descBairro = descBairro;
	}

	public String getDescRua() {
		return descRua;
	}

	public void setDescRua(String descRua) {
		this.descRua = descRua;
	}

	public String getNoCidade() {
		return noCidade;
	}

	public void setNoCidade(String noCidade) {
		this.noCidade = noCidade;
	}

	public String getNoEstado() {
		return noEstado;
	}

	public void setNoEstado(String noEstado) {
		this.noEstado = noEstado;
	}

	public String getNuCep() {
		return nuCep;
	}

	public void setNuCep(String nuCep) {
		this.nuCep = nuCep;
	}

	public String getDescComplemento() {
		return descComplemento;
	}

	public void setDescComplemento(String descComplemento) {
		this.descComplemento = descComplemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public EnderecoDTO(Long id, String descBairro, String descRua, String noCidade, String noEstado, String nuCep, String descComplemento, Integer numero) {
		this.descBairro = descBairro;
		this.descRua = descRua;
		this.noCidade = noCidade;
		this.noEstado = noEstado;
		this.nuCep = nuCep;
		this.descComplemento = descComplemento;
		this.numero = numero;
		this.id = id;
	}

	public EnderecoDTO(Endereco endereco) {
		this.descBairro = endereco.getDescBairro();
		this.descRua = endereco.getDescRua();
		this.noCidade = endereco.getNoCidade();
		this.noEstado = endereco.getNoEstado();
		this.nuCep = endereco.getNuCep();
		this.descComplemento = endereco.getDescComplemento();
		this.numero =  endereco.getNumero();
		this.id = endereco.getId();
	}	
	
	public EnderecoDTO() {	}

	
	
}
