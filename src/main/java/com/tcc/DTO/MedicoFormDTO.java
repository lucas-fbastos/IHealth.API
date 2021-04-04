package com.tcc.DTO;

import java.time.LocalTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicoFormDTO {
	private Long id;
	private String crm;
	private Set<Long> especializacoes;
	private String nome;
	private String telefone;
	private String cpf;
	private Long idUser;
	@JsonFormat(pattern="HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime hrEntrada;
	@JsonFormat(pattern="HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime hrSaida;
	
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
	public Set<Long> getEspecializacoes() {
		return especializacoes;
	}
	public void setEspecializacoes(Set<Long> especializacoes) {
		this.especializacoes = especializacoes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public MedicoFormDTO(Long id, String crm, Set<Long> especializacoes, String nome, String telefone, String cpf,
			Long idUser, LocalTime hrEntrada, LocalTime hrSaida) {
		
		this.id = id;
		this.crm = crm;
		this.especializacoes = especializacoes;
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.idUser = idUser;
		this.hrEntrada = hrEntrada;
		this.hrSaida = hrSaida;
	}
	public MedicoFormDTO() {
	
	}
	
	
}
