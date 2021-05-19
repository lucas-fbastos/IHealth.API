package com.tcc.DTO;

import java.util.List;

public class ProntuarioDTO {

	private Long id;
	private Long idConsulta;
	private String desc;
	private String sumario;
	private Boolean concordaTermos;
	private Boolean temAlergia;
	private Boolean temDoencaCronica;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
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
	public ProntuarioDTO() {}
	
	
	
	
}
