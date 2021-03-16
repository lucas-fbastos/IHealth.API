package com.tcc.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcedimentoMedicoFormDTO {
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo descrição do procedimento")
	@Size(max=255,min=1,message="Formato inválido para o campo descrição do procedimento")
	private String descricao;
	
	@NotNull(message="Preenchimento obrigatório para o campo data do procedimento")
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dtProcedimento;
	
	@NotNull(message="Preenchimento obrigatório para o campo  tipo do procedimento")
	private Long idTipoProcedimento;
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDtProcedimento() {
		return dtProcedimento;
	}

	public void setDtProcedimento(LocalDate dtProcedimento) {
		this.dtProcedimento = dtProcedimento;
	}

	public Long getIdTipoProcedimento() {
		return idTipoProcedimento;
	}

	public void setIdTipoProcedimento(Long idTipoProcedimento) {
		this.idTipoProcedimento = idTipoProcedimento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProcedimentoMedicoFormDTO(Long id, String descricao, LocalDate dtProcedimento, Long idTipoProcedimento) {
		this.id = id;
		this.descricao = descricao;
		this.dtProcedimento = dtProcedimento;
		this.idTipoProcedimento = idTipoProcedimento;
	}

	public ProcedimentoMedicoFormDTO() {
		super();
	}
	
	
}
