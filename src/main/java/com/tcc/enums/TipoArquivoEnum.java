package com.tcc.enums;

public enum TipoArquivoEnum {

	LAUDO(1,"Laudo médico"),
	RESULTADO_EXAME(2,"Resultado de exame"),
	SOLICITACAO_EXAME(3,"Solicitação de exame"),
	RECEITA(4, "Receita");
	
	private Integer tipo;
	private String descricao;
	
	TipoArquivoEnum(Integer tipo, String desc) {
		this.tipo = tipo;
		this.descricao = desc;
	}

	public Integer getTipo() {
		return tipo;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoArquivoEnum toEnum(Integer id) {
			
			if(id==null)
				return null;
			
			for(TipoArquivoEnum e : TipoArquivoEnum.values()){
				if(id.equals(e.getTipo())) {
					return e;
				}
			}
			
			throw new IllegalArgumentException("tipo inválido: " + id);
		}
}
