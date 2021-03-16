package com.tcc.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="medico",schema ="public")
public class Medico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nu_crm")
	private String crm;
	
	@OneToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	@JsonManagedReference
	private Usuario usuario;
	
	@ManyToMany
	private Set<Especializacao> especializacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Especializacao> getEspecializacoes() {
		return especializacoes;
	}

	public void setEspecializacoes(Set<Especializacao> especializacoes) {
		this.especializacoes = especializacoes;
	}

	public Medico(Long id, String crm, Usuario usuario, Set<Especializacao> especializacoes) {
		this.id = id;
		this.crm = crm;
		this.usuario = usuario;
		this.especializacoes = especializacoes;
	}
	
	public Medico() {}
	
}
