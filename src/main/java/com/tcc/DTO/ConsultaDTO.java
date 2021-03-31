package com.tcc.DTO;

import java.time.LocalDateTime;

import com.tcc.domain.TipoProcedimento;

public class ConsultaDTO {

	private Long id;
	private PacienteDTO paciente;
	private MedicoDTO medico;
	private LocalDateTime dtInicio;
	private LocalDateTime dtFim;
	private TipoProcedimento procedimento;
	private String observacao;
	
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
	public ConsultaDTO() {
		
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
	}
	
	
	
	
}
