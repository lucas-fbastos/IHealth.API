package com.tcc.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="procedimento_medico", schema="public")
public class ProcedimentoMedico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="desc_procedimeto")
	private String descricao;
	
	@Column(name="dt_registro")
	private LocalDate dtRegistro;
	
	@Column(name="dt_procedimento")
	private LocalDate dtProcedimento;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	@JsonManagedReference
	private Usuario user;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_procedimento")
	@JsonManagedReference
	private TipoProcedimento tipoProcedimento;

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


	public LocalDate getDtProcedimento() {
		return dtProcedimento;
	}

	public void setDtProcedimento(LocalDate dtProcedimento) {
		this.dtProcedimento = dtProcedimento;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public TipoProcedimento getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(TipoProcedimento tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public ProcedimentoMedico() {
		super();
	}

	public ProcedimentoMedico(Long id, String descricao, LocalDate dtRegistro,
			LocalDate dtProcedimento, Usuario user, TipoProcedimento tipoProcedimento) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dtRegistro = dtRegistro;
		this.dtProcedimento = dtProcedimento;
		this.user = user;
		this.tipoProcedimento = tipoProcedimento;
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
		ProcedimentoMedico other = (ProcedimentoMedico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
