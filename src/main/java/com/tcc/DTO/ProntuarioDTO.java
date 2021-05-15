package com.tcc.DTO;

public class ProntuarioDTO {

	private Long id;
	private Long idConsulta;
	private String desc;
	private String sumario;
	private Boolean concordaTermos;
	private Boolean temAlergia;
	private Boolean temDoencaCronica;
	
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

	public ProntuarioDTO(Long id, Long idConsulta, String desc, String sumario, Boolean concordaTermos,
			Boolean temAlergia, Boolean temDoencaCronica) {
		this.id = id;
		this.idConsulta = idConsulta;
		this.desc = desc;
		this.sumario = sumario;
		this.concordaTermos = concordaTermos;
		this.temAlergia = temAlergia;
		this.temDoencaCronica = temDoencaCronica;
	}
	public ProntuarioDTO() {}
	
	
	
	
}
