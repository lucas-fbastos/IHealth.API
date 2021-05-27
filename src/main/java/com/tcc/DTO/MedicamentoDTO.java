package com.tcc.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tcc.domain.Medicamento;

public class MedicamentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo descrição do medicamento")
	@Size(max=255,min=1,message="Formato inválido para o campo descrição do medicamento")
	private String desc;
	
	private Long id;
	
	private String duracaoTratamento;
	
	private String dosagem;

	public String getDuracaoTratamento() {
		return duracaoTratamento;
	}

	public void setDuracaoTratamento(String duracaoTratamento) {
		this.duracaoTratamento = duracaoTratamento;
	}

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
	
	

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public MedicamentoDTO() {
		super();
	}

	public MedicamentoDTO(Medicamento m) {
		this.desc = m.getDescMedicamento();
		this.id = m.getId();
		this.dosagem = m.getDosagem();
	}
	
	
}
