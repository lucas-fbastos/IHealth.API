package com.tcc.DTO;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;

import com.tcc.domain.Especializacao;
import com.tcc.domain.Medico;
import com.tcc.domain.Usuario;

public class MedicoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String crm;
	private Set<Especializacao> especializacoes;
	private String nome;
	private String telefone;
	private String cpf;
	private Long idUser;
	private LocalTime hrEntrada;
	private LocalTime hrSaida;
	

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
	public Set<Especializacao> getEspecializacoes() {
		return especializacoes;
	}
	public void setEspecializacoes(Set<Especializacao> especializacoes) {
		this.especializacoes = especializacoes;
	}
	
	public MedicoDTO() {
	}
	
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public LocalTime getHrEntrada() {
		return hrEntrada;
	}
	public void setHrEntrada(LocalTime hrEntrada) {
		this.hrEntrada = hrEntrada;
	}
	public LocalTime getHrSaida() {
		return hrSaida;
	}
	public void setHrSaida(LocalTime hrSaida) {
		this.hrSaida = hrSaida;
	}
	public MedicoDTO(Long id,Set<Especializacao> especializacoes,String crm, Usuario usuario) {
		this.id = id;
		this.nome = usuario.getNome();
		this.crm = crm;
		this.especializacoes = especializacoes;
		this.cpf = usuario.getCpf();
		this.telefone = usuario.getTelefone();
	}
	
	public MedicoDTO(Medico medico) {
		this.crm = medico.getCrm();
		this.especializacoes = medico.getEspecializacoes();
		this.id = medico.getId();
		this.nome = medico.getUsuario().getNome();
		this.telefone = medico.getUsuario().getTelefone();
		this.hrEntrada = medico.getHrEntrada();
		this.hrSaida = medico.getHrSaida();
	}
	
	
	
}
