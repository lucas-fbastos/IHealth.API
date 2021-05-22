package com.tcc.domain;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="clinica", schema="public")
public class Clinica {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column(name="hr_abertura")
	private LocalTime dtAbertura;
	
	@Column(name="hr_encerramento")
	private LocalTime dtEncerramento;
	
	@Column(name="desc_visao")
	private String descVisao;
	
	@Column(name="desc_missao")
	private String descMissao;
	
	@Column(name="desc_valores")
	private String descValores;
	
	
	@OneToOne(mappedBy="clinicaEndereco",cascade=CascadeType.ALL)
	@JsonManagedReference("clinica-endereco")
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalTime getDtAbertura() {
		return dtAbertura;
	}

	public void setDtAbertura(LocalTime dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	public LocalTime getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(LocalTime dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getDescVisao() {
		return descVisao;
	}

	public void setDescVisao(String descVisao) {
		this.descVisao = descVisao;
	}

	public String getDescMissao() {
		return descMissao;
	}

	public void setDescMissao(String descMissao) {
		this.descMissao = descMissao;
	}

	public String getDescValores() {
		return descValores;
	}

	public void setDescValores(String descValores) {
		this.descValores = descValores;
	}

	public Clinica(Long id, String nome, LocalTime dtAbertura, LocalTime dtEncerramento, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.dtAbertura = dtAbertura;
		this.dtEncerramento = dtEncerramento;
		this.endereco = endereco;
	}
	
	public Clinica(Long id, String nome, LocalTime dtAbertura, LocalTime dtEncerramento, String descVisao,
			String descMissao, String descValores, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.dtAbertura = dtAbertura;
		this.dtEncerramento = dtEncerramento;
		this.descVisao = descVisao;
		this.descMissao = descMissao;
		this.descValores = descValores;
		this.endereco = endereco;
	}

	public Clinica() {}
	
	
}
