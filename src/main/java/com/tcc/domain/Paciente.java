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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="paciente",schema="public")
public class Paciente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="desc_convenio")
	private String descConvenio;
	
	@Column(name="nu_inscricao_convenio")
	private String nuInscricaoConvenio;
	
	@Column(name="nu_telefone")
	private String nuTelefone;
	
	@OneToOne(mappedBy="paciente")
	@JsonManagedReference
	private DadosMedicos dadosmedicos;
	
	@OneToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	@JsonBackReference
	private Usuario usuario;
	
	@Column(name="bol_compartilha_dados")
	private Boolean compartilhaDados;
	
	public Boolean isCompartilhaDados() {
		return compartilhaDados;
	}

	public void setCompartilhaDados(Boolean compartilhaDados) {
		this.compartilhaDados = compartilhaDados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DadosMedicos getDadosmedicos() {
		return dadosmedicos;
	}

	public void setDadosmedicos(DadosMedicos dadosmedicos) {
		this.dadosmedicos = dadosmedicos;
	}

	public Paciente(Long id, String nomePaciente, String cpf, String descConvenio,
			String nuInscricaoConvenio, String nuTelefone) {
		this.id = id;
		this.descConvenio = descConvenio;
		this.nuInscricaoConvenio = nuInscricaoConvenio;
		this.nuTelefone = nuTelefone;
	}

	public Paciente() {
		
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
