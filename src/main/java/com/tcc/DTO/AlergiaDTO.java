package com.tcc.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AlergiaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório para o campo descrição")
	@Size(max=255, min=5, message="Tamanho máximo de 255 caracteres e mínimo de 5" )
	private String descAlergia;
	
	private Long id;
	
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@NotEmpty(message="Preenchimento obrigatório para o campo Tipo de Alergia")
	private Integer idTipoAlergia;
	
	public String getDescAlergia() {
		return descAlergia;
	}
	public void setDescAlergia(String descAlergia) {
		this.descAlergia = descAlergia;
	}
	public Integer getIdTipoAlergia() {
		return idTipoAlergia;
	}
	public void setIdTipoAlergia(Integer idTipoAlergia) {
		this.idTipoAlergia = idTipoAlergia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AlergiaDTO(Long id, Integer idTipoAlergia, String descAlergia) {
		super();
		this.descAlergia = descAlergia;
		this.id = id;
		this.idTipoAlergia = idTipoAlergia;
	}
	
	
	
}
