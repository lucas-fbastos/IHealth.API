package com.tcc.DTO.filter;

import com.tcc.domain.TipoProcedimento;

public class ConsultaFilter {

	private Long id;
	private TipoProcedimento tipoProcedimento;
	private Long medico ;
	private Long paciente;
	private String nmMedico;
	private String nmPaciente;
	private Long idEspecializacao;
	private String dtInicio;
	private String dtFim;
	private String statusConsulta;
	private Boolean confirmada;
	private String observacao;
	
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
	public Long getMedico() {
		return medico;
	}
	public void setMedico(Long medico) {
		this.medico = medico;
	}
	public Long getPaciente() {
		return paciente;
	}
	public void setPaciente(Long paciente) {
		this.paciente = paciente;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}
	public String getDtFim() {
		return dtFim;
	}
	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}
	public String getStatusConsulta() {
		return statusConsulta;
	}
	public void setStatusConsulta(String statusConsulta) {
		this.statusConsulta = statusConsulta;
	}
	public Boolean getConfirmada() {
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
	
	
	
	public String getNmMedico() {
		return nmMedico;
	}
	public void setNmMedico(String nmMedico) {
		this.nmMedico = nmMedico;
	}
	public String getNmPaciente() {
		return nmPaciente;
	}
	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}
	public Long getIdEspecializacao() {
		return idEspecializacao;
	}
	public void setIdEspecializacao(Long idEspecializacao) {
		this.idEspecializacao = idEspecializacao;
	}
	public ConsultaFilter(Long id, TipoProcedimento tipoProcedimento, Long medico, Long paciente, String dtInicio,
			String dtFim, String statusConsulta, Boolean confirmada, String observacao) {
		super();
		this.id = id;
		this.tipoProcedimento = tipoProcedimento;
		this.medico = medico;
		this.paciente = paciente;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.statusConsulta = statusConsulta;
		this.confirmada = confirmada;
		this.observacao = observacao;
	}

	
}
