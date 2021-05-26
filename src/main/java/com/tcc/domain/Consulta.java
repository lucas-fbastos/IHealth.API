package com.tcc.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tcc.enums.StatusConsultaEnum;

@Entity
@Table(name = "consulta", schema = "public")
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "id_tipo_procedimento")
	private TipoProcedimento tipoProcedimento;

	@ManyToOne()
	@JoinColumn(name = "id_medico")
	private Medico medico;

	@ManyToOne()
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Column(name = "dt_consulta_inicio")
	private LocalDateTime dtInicio;

	@Column(name = "dt_consulta_fim")
	private LocalDateTime dtFim;

	@Column(name = "status_consulta")
	private Integer statusConsulta;

	@Column(name = "bol_confirmada")
	private Boolean confirmada;

	@Column(name = "observacao")
	private String observacao;

	@OneToOne(mappedBy="consulta", cascade=CascadeType.ALL)
	@JsonBackReference
	private Prontuario prontuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoProcedimento getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(TipoProcedimento tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getDtIncio() {
		return dtInicio;
	}

	public void setDtIncio(LocalDateTime dtIncio) {
		this.dtInicio = dtIncio;
	}

	public LocalDateTime getDtFim() {
		return dtFim;
	}

	public void setDtFim(LocalDateTime dtFim) {
		this.dtFim = dtFim;
	}

	public Boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Boolean confirmada) {
		this.confirmada = confirmada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public StatusConsultaEnum getStatusConsulta() {
		return StatusConsultaEnum.toEnum(this.statusConsulta);
	}

	public void setStatusConsulta(Integer statusConsulta) {
		this.statusConsulta = statusConsulta;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Consulta() {
		super();
	}

	public Consulta(Long id, TipoProcedimento tipoProcedimento, Medico medico, Paciente paciente,
			LocalDateTime dtInicio, LocalDateTime dtFim, Boolean confirmada, Prontuario p) {
		super();
		this.id = id;
		this.tipoProcedimento = tipoProcedimento;
		this.medico = medico;
		this.paciente = paciente;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.confirmada = confirmada;
		this.prontuario = p;
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
		Consulta other = (Consulta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}