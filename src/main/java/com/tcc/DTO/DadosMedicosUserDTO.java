package com.tcc.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.domain.Alergia;
import com.tcc.domain.DoencaCronica;
import com.tcc.domain.Medicamento;
import com.tcc.domain.TipoSanguineo;

public class DadosMedicosUserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private TipoSanguineo tipoSanguineo;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime  dtAtualizacao;
	
	private Set<DoencaCronica> doencasCronicas = new HashSet<>();
	private Set<Medicamento> medicamentos = new HashSet<>();
	private Double peso;
	private Double altura;
	private Double vlImc;
	private String descImc;
	private Set<Alergia> alergias = new HashSet<>();
	private Integer idade;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	public LocalDateTime  getDtAtualizacao() {
		return dtAtualizacao;
	}
	public void setDtAtualizacao(LocalDateTime  dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	public Set<DoencaCronica> getDoencasCronicas() {
		return doencasCronicas;
	}
	public void setDoencasCronicas(Set<DoencaCronica> doencasCronicas) {
		this.doencasCronicas = doencasCronicas;
	}
	public Set<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(Set<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getVlImc() {
		return vlImc;
	}
	public void setVlImc(Double vlImc) {
		this.vlImc = vlImc;
	}
	public String getDescImc() {
		return descImc;
	}
	public void setDescImc(String descImc) {
		this.descImc = descImc;
	}
	public Set<Alergia> getAlergias() {
		return alergias;
	}
	public void setAlergias(Set<Alergia> alergias) {
		this.alergias = alergias;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public DadosMedicosUserDTO() {
		super();
	}
	
	
}
