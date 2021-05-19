package com.tcc.DTO;


public class ResponseFileDTO {
	private String nome;
	private String url;
	private String formato;
	private long size;
	private String tipo;


	public ResponseFileDTO(String nome, String url, String formato, long size, String tipo) {
		super();
		this.nome = nome;
		this.url = url;
		this.formato = formato;
		this.size = size;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
