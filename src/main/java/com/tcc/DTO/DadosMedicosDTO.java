package com.tcc.DTO;

import java.io.Serializable;

public class DadosMedicosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer tipoSanguineo;
	
	private Double altura;
	
	private Double peso;
	
	private String imc;
	
	private Double imcValue;
	
	private String code;
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public Double getImcValue() {
		return imcValue;
	}

	public void setImcValue(Double imcValue) {
		this.imcValue = imcValue;
	}

	public Integer getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(Integer tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	
	
}
