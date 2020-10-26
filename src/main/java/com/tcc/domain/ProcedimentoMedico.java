package com.tcc.domain;

import java.io.Serializable;
import java.util.Date;

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
	
	@Column(name="titulo_procedimeto")
	private String titulo;
	
	@Column(name="desc_local")
	private String descLocal;
	
	@Column(name="desc_procedimeto")
	private String descricao;
	
	@Column(name="dt_registro")
	private Date dtRegistro;
	
	@Column(name="dt_retorno")
	private Date dtRetorno;
	
	@Column(name="dt_procedimento")
	private java.sql.Date dtProcedimento;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	@JsonManagedReference
	private User user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public ProcedimentoMedico(Long id, String titulo, String descLocal, String descricao, Date dtRegistro,
			Date dtRetorno, java.sql.Date dtProcedimento, User user, TipoProcedimento tipoProcedimento) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descLocal = descLocal;
		this.descricao = descricao;
		this.dtRegistro = dtRegistro;
		this.dtRetorno = dtRetorno;
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