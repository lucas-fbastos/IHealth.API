package com.tcc.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.domain.Consulta;
import com.tcc.domain.TipoProcedimento;
import com.tcc.enums.StatusConsultaEnum;

public class ConsultaDTO {

	private Long id;
	private PacienteDTO paciente;
	private MedicoDTO medico;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtInicio;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtFim;
	private TipoProcedimento procedimento;
	private String observacao;
	private Long idTipoProcedimento;
	private Long idPaciente;
	private Long idMedico;
	private String status;
	private ProntuarioDTO prontuario;
	
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
	
	
	public Long getIdTipoProcedimento() {
		return idTipoProcedimento;
	}
	
	public void setIdTipoProcedimento(Long idTipoProcedimento) {
		this.idTipoProcedimento = idTipoProcedimento;
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
		
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public ProntuarioDTO getProntuario() {
		return prontuario;
	}

	public void setProntuario(ProntuarioDTO prontuario) {
		this.prontuario = prontuario;
	}
	
	public ConsultaDTO() {
		
	}
	


	public ConsultaDTO(Long id, PacienteDTO paciente, MedicoDTO medico, LocalDateTime dtInicio, LocalDateTime dtFim,
			TipoProcedimento procedimento, String observacao, Long idTipoProcedimento) {
		
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.procedimento = procedimento;
		this.observacao = observacao;
		this.idTipoProcedimento = idTipoProcedimento;
	}
	
	
	public ConsultaDTO(Long id, PacienteDTO paciente, MedicoDTO medico, LocalDateTime dtInicio, LocalDateTime dtFim,
			TipoProcedimento procedimento, String observacao, StatusConsultaEnum status, ProntuarioDTO prontuario) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.procedimento = procedimento;
		this.observacao = observacao;
		this.procedimento = procedimento;
		if(status!=null)
			this.status = status.getDescStatus();
		this.prontuario = prontuario;
	}
	
	public ConsultaDTO(Long id, PacienteDTO paciente, MedicoDTO medico, LocalDateTime dtInicio, LocalDateTime dtFim,
			TipoProcedimento procedimento, String observacao, Long idTipoProcedimento, Long idPaciente, Long idMedico,
			StatusConsultaEnum status, ProntuarioDTO prontuario) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.procedimento = procedimento;
		this.observacao = observacao;
		this.idTipoProcedimento = idTipoProcedimento;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		if(status!=null)
			this.status = status.getDescStatus();
		this.prontuario = prontuario;
	}
	
	public ConsultaDTO(Consulta c) {
		this.id = c.getId();
		this.medico = new MedicoDTO(c.getMedico());
		this.paciente = new PacienteDTO(c.getPaciente());
		this.dtFim = c.getDtFim();
		this.dtInicio = c.getDtIncio();
		this.observacao = c.getObservacao();
		this.procedimento = c.getTipoProcedimento();
		if(c.getStatusConsulta()!=null)
			this.status = c.getStatusConsulta().getDescStatus();
		if(c.getProntuario()!=null)
			this.prontuario = new ProntuarioDTO(c.getProntuario());
	}
	
	
	
	
}
