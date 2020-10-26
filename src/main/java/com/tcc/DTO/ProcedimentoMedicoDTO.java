package com.tcc.DTO;

import java.io.Serializable;
import java.util.Date;

public class ProcedimentoMedicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String desc_local;
	private String descricao;
	private Date dtRegistro;
	private Date dtRetorno;
	private java.sql.Date dtProcedimento;
	private String nomeUsuario;
	private String descTipoProcedimento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDesc_local() {
		return desc_local;
	}
	public void setDesc_local(String desc_local) {
		this.desc_local = desc_local;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDtRegistro() {
		return dtRegistro;
	}
	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
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
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getDescTipoProcedimento() {
		return descTipoProcedimento;
	}
	public void setDescTipoProcedimento(String descTipoProcedimento) {
		this.descTipoProcedimento = descTipoProcedimento;
	}
	
	public ProcedimentoMedicoDTO(Long id, String titulo, String desc_local, String descricao, Date dtRegistro,
			Date dtRetorno, java.sql.Date dtProcedimento, String nomeUsuario, String descTipoProcedimento) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.desc_local = desc_local;
		this.descricao = descricao;
		this.dtRegistro = dtRegistro;
		this.dtRetorno = dtRetorno;
		this.dtProcedimento = dtProcedimento;
		this.nomeUsuario = nomeUsuario;
		this.descTipoProcedimento = descTipoProcedimento;
	}
	
	public ProcedimentoMedicoDTO() {
		super();
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
		ProcedimentoMedicoDTO other = (ProcedimentoMedicoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
