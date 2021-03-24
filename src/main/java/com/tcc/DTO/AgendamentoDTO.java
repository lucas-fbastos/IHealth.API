package com.tcc.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgendamentoDTO {

	@JsonFormat(pattern="dd-MM-yyyy")
	private String data;

	private Long idMedico;

	private Long idTipoProcedimento;
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
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

	public AgendamentoDTO(String data, Long idMedico, Long idTipoProcedimento) {
		super();
		this.data = data;
		this.idMedico = idMedico;
		this.idTipoProcedimento = idTipoProcedimento;
	}

}
