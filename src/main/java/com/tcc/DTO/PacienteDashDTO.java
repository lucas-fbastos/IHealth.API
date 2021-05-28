package com.tcc.DTO;

import java.util.ArrayList;
import java.util.List;

import com.tcc.DTO.report.ProntuarioConsultaReport;
import com.tcc.DTO.report.TipoQuantidade;

public class PacienteDashDTO {

	private List<TipoQuantidade> quantitativosPorTipoAlergia = new ArrayList<>();
	private List<TipoQuantidade> quantitativosPorTipoProcedimento = new ArrayList<>();
	private List<ProntuarioConsultaReport> diagnosticosPorData = new ArrayList<>();
	private List<TipoQuantidade> quantitativosPorTipoEspecializacao = new ArrayList<>();
	private Long quantiativoMedicamentos;
	private Long quantiativoAlergia;
	private Long quantiativoDoencaCronica;
	private Long quantitativoProcedimento;

	public List<TipoQuantidade> getQuantitativosPorTipoAlergia() {
		return quantitativosPorTipoAlergia;
	}

	public void setQuantitativosPorTipoAlergia(List<TipoQuantidade> quantitativosPorTipoAlergia) {
		this.quantitativosPorTipoAlergia = quantitativosPorTipoAlergia;
	}

	public List<TipoQuantidade> getQuantitativosPorTipoProcedimento() {
		return quantitativosPorTipoProcedimento;
	}

	public void setQuantitativosPorTipoProcedimento(List<TipoQuantidade> quantitativosPorTipoProcedimento) {
		this.quantitativosPorTipoProcedimento = quantitativosPorTipoProcedimento;
	}

	public List<ProntuarioConsultaReport> getDiagnosticosPorData() {
		return diagnosticosPorData;
	}

	public void setDiagnosticosPorData(List<ProntuarioConsultaReport> diagnosticosPorData) {
		this.diagnosticosPorData = diagnosticosPorData;
	}

	public Long getQuantiativoMedicamentos() {
		return quantiativoMedicamentos;
	}

	public void setQuantiativoMedicamentos(Long quantiativoMedicamentos) {
		this.quantiativoMedicamentos = quantiativoMedicamentos;
	}

	public Long getQuantiativoAlergia() {
		return quantiativoAlergia;
	}

	public void setQuantiativoAlergia(Long quantiativoAlergia) {
		this.quantiativoAlergia = quantiativoAlergia;
	}

	public Long getQuantiativoDoencaCronica() {
		return quantiativoDoencaCronica;
	}

	public void setQuantiativoDoencaCronica(Long quantiativoDoencaCronica) {
		this.quantiativoDoencaCronica = quantiativoDoencaCronica;
	}

	public Long getQuantitativoProcedimento() {
		return quantitativoProcedimento;
	}

	public void setQuantitativoProcedimento(Long quantitativoProcedimento) {
		this.quantitativoProcedimento = quantitativoProcedimento;
	}

	public List<TipoQuantidade> getQuantitativosPorTipoEspecializacao() {
		return quantitativosPorTipoEspecializacao;
	}

	public void setQuantitativosPorTipoEspecializacao(List<TipoQuantidade> quantitativosPorTipoEspecializacao) {
		this.quantitativosPorTipoEspecializacao = quantitativosPorTipoEspecializacao;
	}

	public PacienteDashDTO() {

	}

}
