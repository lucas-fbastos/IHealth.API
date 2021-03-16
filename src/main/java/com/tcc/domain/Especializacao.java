package com.tcc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="especializacao",schema="public")
public class Especializacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="desc_especializacao")
	private String descEspecializacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescEspecializacao() {
		return descEspecializacao;
	}

	public void setDescEspecializacao(String descEspecializacao) {
		this.descEspecializacao = descEspecializacao;
	}

	public Especializacao() {
		
	}
	
	

}
