package com.tcc.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MedicamentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Preenchimento obrigatório para o campo descrição do medicamento")
	@Size(max=255,min=1,message="Formato inválido para o campo descrição do medicamento")
	private String desc;
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public MedicamentoDTO() {
		super();
	}
	
	
}
