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
@Table(name="procedimento_medico")
public class ProcedimentoMedico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="titulo_procedimeto")
	private String titulo;
	
	@Column(name="desc_local")
	private String desc_local;
	
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
	
	
	
	
}
