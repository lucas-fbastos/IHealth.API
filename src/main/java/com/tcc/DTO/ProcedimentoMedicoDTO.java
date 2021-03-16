package com.tcc.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.domain.ProcedimentoMedico;

public class ProcedimentoMedicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dtRegistro;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dtRetorno;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dtProcedimento;
	private String nomeUsuario;
	private String descTipoProcedimento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDtRegistro() {
		return dtRegistro;
	}
	public void setDtRegistro(LocalDate dtRegistro) {
		this.dtRegistro = dtRegistro;
	}
	public LocalDate getDtRetorno() {
		return dtRetorno;
	}
	public void setDtRetorno(LocalDate dtRetorno) {
		this.dtRetorno = dtRetorno;
	}
	public LocalDate getDtProcedimento() {
		return dtProcedimento;
	}
	public void setDtProcedimento(LocalDate dtProcedimento) {
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
	
	public ProcedimentoMedicoDTO(Long id,  String descricao, LocalDate dtRegistro,
			 LocalDate dtProcedimento, String nomeUsuario, String descTipoProcedimento) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dtRegistro = dtRegistro;
		this.dtProcedimento = LocalDate.of(dtProcedimento.getYear(), dtProcedimento.getMonth(),
				dtProcedimento.getDayOfMonth());
		this.nomeUsuario = nomeUsuario;
		this.descTipoProcedimento = descTipoProcedimento;
	}
	
	public ProcedimentoMedicoDTO() {
		super();
	}
	
	public ProcedimentoMedicoDTO(ProcedimentoMedico p) {
		this.descricao = p.getDescricao();
		this.descTipoProcedimento = p.getTipoProcedimento().getDescTipoProcedimento();
		this.dtProcedimento = p.getDtProcedimento();
		this.dtRegistro = p.getDtRegistro();
		this.id = p.getId();
		this.nomeUsuario = p.getUser().getNome();
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
