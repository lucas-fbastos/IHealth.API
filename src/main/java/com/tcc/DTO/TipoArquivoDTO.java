package com.tcc.DTO;

public class TipoArquivoDTO {

	private Integer id;
	private String descricao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoArquivoDTO() {
		super();
	}
	public TipoArquivoDTO(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	
}
