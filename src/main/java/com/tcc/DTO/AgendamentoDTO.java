package com.tcc.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgendamentoDTO {

	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate data;

	private Long idMedico;

	private Long idTipoProcedimento;
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	
	public Long getIdTipoProcedimento() {
		return idTipoProcedimento;
	}

	public void setIdTipoProcedimento(Long idTipoProcedimento) {
		this.idTipoProcedimento = idTipoProcedimento;
	}

	public AgendamentoDTO() {}

	public AgendamentoDTO(LocalDate data, Long idMedico, Long idTipoProcedimento) {
		super();
		this.data = data;
		this.idMedico = idMedico;
		this.idTipoProcedimento = idTipoProcedimento;
	}

}
