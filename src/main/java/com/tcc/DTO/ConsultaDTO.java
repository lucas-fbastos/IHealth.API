package com.tcc.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.domain.Consulta;
import com.tcc.domain.TipoProcedimento;

public class ConsultaDTO {

	private Long id;
	private PacienteDTO paciente;
	private MedicoDTO medico;
	private LocalDateTime dtInicio;
	private LocalDateTime dtFim;
	private TipoProcedimento procedimento;
	private String observacao;
	@JsonIgnore
	private String dtConsulta;
	@JsonIgnore
	private Long tipoProcedimentoId;
	@JsonIgnore
	private Long idPaciente;
	@JsonIgnore
	private Long idMedico;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public PacienteDTO getPaciente() {
		return paciente;
	}
	
	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}
	
	public MedicoDTO getMedico() {
		return medico;
	}
	
	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}
	
	public LocalDateTime getDtInicio() {
		return dtInicio;
	}
	
	public void setDtInicio(LocalDateTime dtInicio) {
		this.dtInicio = dtInicio;
	}
	
	public LocalDateTime getDtFim() {
		return dtFim;
	}
	
	public void setDtFim(LocalDateTime dtFim) {
		this.dtFim = dtFim;
	}
	
	public TipoProcedimento getProcedimento() {
		return procedimento;
	}
	
	public void setProcedimento(TipoProcedimento procedimento) {
		this.procedimento = procedimento;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	public Long getTipoProcedimentoId() {
		return tipoProcedimentoId;
	}
	
	public void setTipoProcedimentoId(Long tipoProcedimentoId) {
		this.tipoProcedimentoId = tipoProcedimentoId;
	}
	
	public ConsultaDTO() {
		
	}
	
	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	
	public String getDtConsulta() {
		return dtConsulta;
	}

	public void setDtConsulta(String dtConsulta) {
		this.dtConsulta = dtConsulta;
	}

	public ConsultaDTO(Long id, PacienteDTO paciente, MedicoDTO medico, LocalDateTime dtInicio, LocalDateTime dtFim,
			TipoProcedimento procedimento, String observacao, Long tipoProcedimentoId) {
		
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.procedimento = procedimento;
		this.observacao = observacao;
		this.tipoProcedimentoId = tipoProcedimentoId;
	}
	
	
	public ConsultaDTO(Long id, PacienteDTO paciente, MedicoDTO medico, LocalDateTime dtInicio, LocalDateTime dtFim,
			TipoProcedimento procedimento, String observacao) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.procedimento = procedimento;
		this.observacao = observacao;
		this.procedimento = procedimento;
	}
	
	public ConsultaDTO(Consulta c) {
		this.id = c.getId();
		this.medico = new MedicoDTO(c.getMedico());
		this.paciente = new PacienteDTO(c.getPaciente());
		this.dtFim = c.getDtFim();
		this.dtInicio = c.getDtIncio();
		this.observacao = c.getObservacao();
		this.procedimento = c.getTipoProcedimento();
	}
	
	
	
	
}
