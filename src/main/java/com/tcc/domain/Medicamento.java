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

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="medicamento_user", schema="public")
public class Medicamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="desc_medicamento")
	private String descMedicamento;

	@ManyToOne
	@JoinColumn(name="id_dados_medicos")
	@JsonBackReference("medicamento-dados")
	private DadosMedicos dadosMedicos;

	@Column(name="dt_registro")
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
	private Date dtRegistro;
	
	@Column(name="desc_duracao_tratamento")
	private String duracaoTratamento;
	
	@Column(name="desc_dosagem")
	private String dosagem;
	
	@ManyToOne
	@JoinColumn(name="id_prontuario")
	@JsonBackReference("medicamento-prontuario")
	private Prontuario prontuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescMedicamento() {
		return descMedicamento;
	}

	public void setDescMedicamento(String descMedicamento) {
		this.descMedicamento = descMedicamento;
	}

	public DadosMedicos getDadosMedicos() {
		return dadosMedicos;
	}

	public void setDadosMedicos(DadosMedicos dadosMedicos) {
		this.dadosMedicos = dadosMedicos;
	}

	public Medicamento() {
		super();
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	
	public String getDuracaoTratamento() {
		return duracaoTratamento;
	}

	public void setDuracaoTratamento(String duracaoTratamento) {
		this.duracaoTratamento = duracaoTratamento;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Medicamento(Long id, String descMedicamento, DadosMedicos dadosMedicos, Date dtRegistro) {
		super();
		this.id = id;
		this.descMedicamento = descMedicamento;
		this.dadosMedicos = dadosMedicos;
		this.dtRegistro = dtRegistro;
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
		Medicamento other = (Medicamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
