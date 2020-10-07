package com.tcc.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DoencaCronicaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório para o campo descrição")
	@Size(max=255, min=3, message="Tamanho máximo de 255 caracteres e mínimo de 3" )
	private String descDoenca;
	private Long id;
	public String getDescDoenca() {
		return descDoenca;
	}
	public void setDescDoenca(String descDoenca) {
		this.descDoenca = descDoenca;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DoencaCronicaDTO(String descDoenca,Long id) {
		super();
		this.descDoenca = descDoenca;
		this.id = id;
	}
	public DoencaCronicaDTO() {
		super();
	}
	
	
	
}
