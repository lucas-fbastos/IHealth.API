package com.tcc.DTO;

import java.util.List;

import com.tcc.DTO.report.TipoQuantidade;

public class AdministradorDashDTO {

	private List<TipoQuantidade> medicoAtendimento;
	private List<TipoQuantidade> pacienteConsulta;
	private List<TipoQuantidade> consultaMensalPorStatus;
	private List<TipoQuantidade> especializacoesMes;
	
	public List<TipoQuantidade> getEspecializacoesMes() {
		return especializacoesMes;
	}
	public void setEspecializacoesMes(List<TipoQuantidade> especializacoesMes) {
		this.especializacoesMes = especializacoesMes;
	}
	public List<TipoQuantidade> getMedicoAtendimento() {
		return medicoAtendimento;
	}
	public void setMedicoAtendimento(List<TipoQuantidade> medicoAtendimento) {
		this.medicoAtendimento = medicoAtendimento;
	}
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
	public AdministradorDashDTO() {
		
	}
	
	
	
}
