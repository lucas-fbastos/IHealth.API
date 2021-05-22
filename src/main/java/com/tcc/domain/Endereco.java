package com.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tcc.DTO.EnderecoDTO;

@Entity
@Table(name="endereco", schema="public")
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	@JsonBackReference("usuario-endereco")
	private Usuario usuarioEndereco;
	
	@OneToOne
	@JoinColumn(name="id_clinica", referencedColumnName="id")
	@JsonBackReference("clinica-endereco")
	private Clinica clinicaEndereco;
			
	@Column(name="nu_cep")
	private String nuCep;
	
	@Column(name="desc_bairro")
	private String descBairro;
	
	@Column(name="desc_rua")
	private String descRua;
	
	@Column(name="desc_complemento")
	private String descComplemento;
	
	@Column(name="numero")
	private Integer numero;
	
	@Column(name="no_cidade")
	private String noCidade;
	
	@Column(name="no_estado")
	private String noEstado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNuCep() {
		return nuCep;
	}

	public void setNuCep(String nuCep) {
		this.nuCep = nuCep;
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
	
	public Usuario getUsuarioEndereco() {
		return usuarioEndereco;
	}

	public void setUsuarioEndereco(Usuario usuarioEndereco) {
		this.usuarioEndereco = usuarioEndereco;
	}

	public Clinica getClinicaEndereco() {
		return clinicaEndereco;
	}

	public void setClinicaEndereco(Clinica clinicaEndereco) {
		this.clinicaEndereco = clinicaEndereco;
	}

	public Endereco(Long id, String nuCep, String descBairro, String descRua, String descComplemento, Integer numero,
			String noCidade, String noEstado) {
		this.id = id;
		this.nuCep = nuCep;
		this.descBairro = descBairro;
		this.descRua = descRua;
		this.descComplemento = descComplemento;
		this.numero = numero;
		this.noCidade = noCidade;
		this.noEstado = noEstado;
	}

	public Endereco() {
		
	}

	public Endereco(EnderecoDTO dto) {
		this.id = dto.getId();
		this.descBairro = dto.getDescBairro();
		this.descComplemento = dto.getDescComplemento();
		this.descRua = dto.getDescRua();
		this.noCidade = dto.getNoCidade();
		this.nuCep = dto.getNuCep();
		this.numero = dto.getNumero();
		this.noEstado = dto.getNoEstado();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
