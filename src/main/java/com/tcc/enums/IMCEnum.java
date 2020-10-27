package com.tcc.enums;

public enum IMCEnum {

	
	ABAIXO_PESO(null,18.4,"Abaixo do peso"),
	PESO_NORMAL(18.5,24.9,"Peso normal"),
	SOBREPESO(25.0,29.9,"Sobrepeso"),
	OBESIDADE_1(30.0,34.9,"Obesidade grau I"),
	OBESIDADE_2(35.0,39.9,"Obesidade grau II"),
	OBESIDADE_3(40.0,null,"Obesidade grau III");
	
	private Double limiteMin;
	private Double limiteMax;
	private String descricao;
	
	private IMCEnum(Double min, Double max, String desc) {
		this.descricao = desc;
		this.limiteMax = max;
		this.limiteMin = min;
	}
	
	public Double getLimiteMin() {
		return limiteMin;
	}
	public void setLimiteMin(Double limiteMin) {
		this.limiteMin = limiteMin;
	}
	public Double getLimiteMax() {
		return limiteMax;
	}
	public void setLimiteMax(Double limiteMax) {
		this.limiteMax = limiteMax;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static IMCEnum getImcDesc(Double imc) {
		if(imc <=18.4) {
			return IMCEnum.ABAIXO_PESO;
		}else if(imc >=18.5 && imc <= 24.9) {
			return IMCEnum.PESO_NORMAL;
		}else if(imc >=25.0 && imc <= 29.9) {
			return IMCEnum.SOBREPESO;
		}else if(imc >= 30.0 && imc <= 34.9) {
			return IMCEnum.OBESIDADE_1;
		}else if(imc >= 35.0 && imc <= 39.9) {
			return IMCEnum.OBESIDADE_2;
		}else{
			return IMCEnum.OBESIDADE_3;
		}
	}
	
}
