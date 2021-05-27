package com.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="doencas_cronicas", schema="public")
public class DoencaCronica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="desc_doenca")
	private String descDoenca;
	
	@ManyToOne
	@JoinColumn(name="id_dados_medicos")
	@JsonBackReference("doenca-dados")
	private DadosMedicos dadosMedicos;
	
	@ManyToOne
	@JoinColumn(name="id_prontuario")
	@JsonBackReference("doenca-prontuario")
	private Prontuario prontuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescDoenca() {
		return descDoenca;
	}

	public void setDescDoenca(String descDoenca) {
		this.descDoenca = descDoenca;
	}

	public DadosMedicos getDadosMedicos() {
		return dadosMedicos;
	}

	public void setDadosMedicos(DadosMedicos dadosMedicos) {
		this.dadosMedicos = dadosMedicos;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public DoencaCronica() {
		super();
	}

	public DoencaCronica(Long id, String descDoenca, DadosMedicos dadosMedicos) {
		super();
		this.id = id;
		this.descDoenca = descDoenca;
		this.dadosMedicos = dadosMedicos;
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
		DoencaCronica other = (DoencaCronica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
