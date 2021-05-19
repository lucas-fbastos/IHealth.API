package com.tcc.DTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.domain.Consulta;
import com.tcc.domain.Prontuario;
import com.tcc.enums.TipoArquivoEnum;

public class ProntuarioDTO {

	private Long id;
	private Consulta consulta;
	private String descProntuario;
	private Boolean temAlergia;
	private Boolean temDoencaCronica;
	private Boolean concordouTermos;
	private String descSumario;
	private List<ResponseFileDTO> documentos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public String getDescProntuario() {
		return descProntuario;
	}
	public void setDescProntuario(String descProntuario) {
		this.descProntuario = descProntuario;
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
	public Boolean getConcordouTermos() {
		return concordouTermos;
	}
	public void setConcordouTermos(Boolean concordouTermos) {
		this.concordouTermos = concordouTermos;
	}
	public String getDescSumario() {
		return descSumario;
	}
	public void setDescSumario(String descSumario) {
		this.descSumario = descSumario;
	}
	public List<ResponseFileDTO> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<ResponseFileDTO> documentos) {
		this.documentos = documentos;
	}
	
	public ProntuarioDTO(Prontuario p) {
		this.id = p.getId();
		this.consulta = p.getConsulta();
		this.descProntuario = p.getDescProntuario();
		this.temAlergia = p.getTemAlergia();
		this.temDoencaCronica = p.getTemDoencaCronica();
		this.concordouTermos = p.getConcordouTermos();
		this.descSumario = p.getDescSumario();
		if(p.getDocumentos()!=null && !p.getDocumentos().isEmpty())
			this.documentos = p.getDocumentos().stream().map(dbFile -> {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
						.path(dbFile.getId().toString()).toUriString();
				return new ResponseFileDTO(dbFile.getId(),dbFile.getNomeArquivo(), fileDownloadUri, dbFile.getFormato(), dbFile.getData().length, TipoArquivoEnum.toEnum(dbFile.getTipo()).getDescricao());
			}).collect(Collectors.toList());
	}
	
	public ProntuarioDTO() {}
	
	
	
	
}
