package com.tcc.DTO;

import java.util.List;

import com.tcc.DTO.report.TipoQuantidade;

public class MedicoDashDTO {


	private List<TipoQuantidade> pacienteConsulta;
	private List<TipoQuantidade> consultaMensalPorStatus;
	
	public List<TipoQuantidade> getPacienteConsulta() {
		return pacienteConsulta;
	}
	public void setPacienteConsulta(List<TipoQuantidade> pacienteConsulta) {
		this.pacienteConsulta = pacienteConsulta;
	}
	public List<TipoQuantidade> getConsultaMensalPorStatus() {
		return consultaMensalPorStatus;
	}
	public void setConsultaMensalPorStatus(List<TipoQuantidade> consultaMensalPorStatus) {
		this.consultaMensalPorStatus = consultaMensalPorStatus;
	}
	
	public MedicoDashDTO() {
		
	}
	
	
}
