package com.tcc.DTO.report;

public class TipoQuantidade {

	private String descricao;
	private Long quantidade;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public TipoQuantidade() {

	}

	public TipoQuantidade(String descricao, Long quantidade) {
		this.descricao = descricao;
		this.quantidade = quantidade;
	}

	
}
