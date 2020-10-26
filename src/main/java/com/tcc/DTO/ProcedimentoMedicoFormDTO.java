package com.tcc.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProcedimentoMedicoFormDTO {
	
	private Long id;
	
	@NotNull(message="Preenchimento obrigatório para o campo título")
	@Size(max=255,min=1,message="Formato inválido para o campo título")
	private String titulo;
	
	
	private String descLocal;
	
	@NotNull(message="Preenchimento obrigatório para o campo descrição do procedimento")
	@Size(max=255,min=1,message="Formato inválido para o campo descrição do procedimento")
	private String descricao;
	
	private Date dtRetorno;
	
	@NotNull(message="Preenchimento obrigatório para o campo data do procedimento")
	private java.sql.Date dtProcedimento;
	
	@NotNull(message="Preenchimento obrigatório para o campo  tipo do procedimento")
	private Long idTipoProcedimento;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescLocal() {
		return descLocal;
	}

	public void setDescLocal(String descLocal) {
		this.descLocal = descLocal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtRetorno() {
		return dtRetorno;
	}

	public void setDtRetorno(Date dtRetorno) {
		this.dtRetorno = dtRetorno;
	}

	public java.sql.Date getDtProcedimento() {
		return dtProcedimento;
	}

	public void setDtProcedimento(java.sql.Date dtProcedimento) {
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

	public ProcedimentoMedicoFormDTO(Long id, String titulo, String descLocal, String descricao, Date dtRetorno, java.sql.Date dtProcedimento, Long idTipoProcedimento) {
		this.id = id;
		this.titulo = titulo;
		this.descLocal = descLocal;
		this.descricao = descricao;
		this.dtRetorno = dtRetorno;
		this.dtProcedimento = dtProcedimento;
		this.idTipoProcedimento = idTipoProcedimento;
	}

	public ProcedimentoMedicoFormDTO() {
		super();
	}
	
	
}
