package com.tcc.DTO;

import java.io.Serializable;

public class DadosMedicosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer tipoSanguineo;

	public Integer getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(Integer tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	
	
}
