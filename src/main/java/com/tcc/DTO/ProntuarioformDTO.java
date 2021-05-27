package com.tcc.DTO;

import java.util.List;

public class ProntuarioformDTO {

	private Long id;
	private Long idConsulta;
	private String descConsulta;
	private String descSumario;
	private Boolean concordaTermos;
	private Boolean temAlergia;
	private Boolean temDoencaCronica;
	private String diagnostico;
	private List<AlergiaDTO> alergias;
	private List<MedicamentoDTO> medicamentos;
	private List<DoencaCronicaDTO> doencas;
	private List<ResponseFileDTO> arquivos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getDescConsulta() {
		return descConsulta;
	}
	public void setDescConsulta(String desc) {
		this.descConsulta = desc;
	}
	public String getDescSumario() {
		return descSumario;
	}
	public void setDescSumario(String sumario) {
		this.descSumario = sumario;
	}
	public Boolean getConcordaTermos() {
		return concordaTermos;
	}
	public void setConcordaTermos(Boolean concordaTermos) {
		this.concordaTermos = concordaTermos;
	}
	public Boolean getTemAlergia() {
		return temAlergia;
	}
	public void setTemAlergia(Boolean temAlergia) {
		this.temAlergia = temAlergia;
	}
	public Boolean getTemDoencaCronica() {
		return temDoencaCronica;
	}
	public void setTemDoencaCronica(Boolean temDoencaCronica) {
		this.temDoencaCronica = temDoencaCronica;
	}
	
	public List<AlergiaDTO> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<AlergiaDTO> alergias) {
		this.alergias = alergias;
	}
	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public List<DoencaCronicaDTO> getDoencas() {
		return doencas;
	}
	public void setDoencas(List<DoencaCronicaDTO> doencas) {
		this.doencas = doencas;
	}
	
	public List<ResponseFileDTO> getArquivos() {
		return arquivos;
	}
	public void setArquivos(List<ResponseFileDTO> arquivos) {
		this.arquivos = arquivos;
	}
	
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public ProntuarioformDTO() {}
	
	
	
	
}
