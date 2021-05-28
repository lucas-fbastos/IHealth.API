package com.tcc.DTO.report;

import java.time.LocalDateTime;

public class ProntuarioConsultaReport {

	private String procedimento;
	private LocalDateTime data;
	private String diagnostico;
	
	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public ProntuarioConsultaReport() {

	}

	public ProntuarioConsultaReport(String procedimento, LocalDateTime data, String diagnostico) {
		super();
		this.procedimento = procedimento;
		this.data = data;
		this.diagnostico = diagnostico;
	}
	
	

}
