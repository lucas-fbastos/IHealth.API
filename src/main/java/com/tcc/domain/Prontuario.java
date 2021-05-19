package com.tcc.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="prontuario",schema="public")
public class Prontuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Consulta consulta;

	@Column(name="desc_prontuario")
	private String descProntuario;
	
	@Column(name="alergia")
	private Boolean temAlergia;
	
	@Column(name="doenca_cronica")
	private Boolean temDoencaCronica;
	
	@Column(name="concordou_termos")
	private Boolean concordouTermos;
	
	@Column(name="desc_sumario")
	private String descSumario;

	@OneToMany(mappedBy="prontuario")
	@JsonManagedReference
	@JsonIgnore
	private List<DocumentoMedico> documentos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getDescProntuario() {
		return descProntuario;
	}

	public void setDescProntuario(String descProntuario) {
		this.descProntuario = descProntuario;
	}

	public Boolean getTemAlergia() {
		return temAlergia;
	}

	public void setTemAlergia(Boolean temAlergia) {
		this.temAlergia = temAlergia;
	}

	public Boolean getTemDoencaCronica() {
		return temDoencaCronica;
	}

	public void setTemDoencaCronica(Boolean temDoencaCronica) {
		this.temDoencaCronica = temDoencaCronica;
	}

	public Boolean getConcordouTermos() {
		return concordouTermos;
	}

	public void setConcordouTermos(Boolean concordouTermos) {
		this.concordouTermos = concordouTermos;
	}

	public String getDescSumario() {
		return descSumario;
	}

	public void setDescSumario(String descSumario) {
		this.descSumario = descSumario;
	}

	public List<DocumentoMedico> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoMedico> documentos) {
		this.documentos = documentos;
	}
	
	
}
