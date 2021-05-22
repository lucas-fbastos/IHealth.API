package com.tcc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="alergia", schema="public")
public class Alergia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_dados_medicos")
	@JsonBackReference("alergia-dados")
	private DadosMedicos dadosMedicos;
	
	@Column(name="desc_alergia")
	private String descAlergia;
	
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private TipoAlergia tipoAlergia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DadosMedicos getDadosMedicos() {
		return dadosMedicos;
	}

	public void setDadosMedicos(DadosMedicos dadosMedicos) {
		this.dadosMedicos = dadosMedicos;
	}

	public String getDescAlergia() {
		return descAlergia;
	}

	public void setDescAlergia(String descAlergia) {
		this.descAlergia = descAlergia;
	}

	public TipoAlergia getTipoAlergia() {
		return tipoAlergia;
	}

	public void setTipoAlergia(TipoAlergia tipoAlergia) {
		this.tipoAlergia = tipoAlergia;
	}

	public Alergia(Long id, DadosMedicos dadosMedicos, String descAlergia, TipoAlergia tipoAlergia) {
		super();
		this.id = id;
		this.dadosMedicos = dadosMedicos;
		this.descAlergia = descAlergia;
		this.tipoAlergia = tipoAlergia;
	}

	public Alergia(){ 	}
	
	
	
}
