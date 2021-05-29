package com.tcc.DTO;

import java.util.List;

import com.tcc.DTO.report.TipoQuantidade;

public class AuxiliarDashDTO {

	private List<TipoQuantidade> quantitativoUsuarios;
	private List<TipoQuantidade> consultaMensalPorStatus;
	
	public List<TipoQuantidade> getQuantitativoUsuarios() {
		return quantitativoUsuarios;
	}
	public void setQuantitativoUsuarios(List<TipoQuantidade> quantitativoUsuarios) {
		this.quantitativoUsuarios = quantitativoUsuarios;
	}
	
	public List<TipoQuantidade> getConsultaMensalPorStatus() {
		return consultaMensalPorStatus;
	}
	
	public void setConsultaMensalPorStatus(List<TipoQuantidade> consultaMensalPorStatus) {
		this.consultaMensalPorStatus = consultaMensalPorStatus;
	}
	
	public AuxiliarDashDTO() {
		
	}
	
	
}
