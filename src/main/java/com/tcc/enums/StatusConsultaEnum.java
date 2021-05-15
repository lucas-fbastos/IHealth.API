package com.tcc.enums;

public enum StatusConsultaEnum {
	
	PENDENTE(1,"Pendente"),
	CONFIRMADA(2,"Confirmada"),
	CANCELADA(3,"Cancelada"),
	EM_ANDAMENTO(4, "Em Andamento");
	
	private Integer id;
	private String descStatus;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescStatus() {
		return descStatus;
	}

	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	private StatusConsultaEnum(Integer id,String descStatus) {
		this.id = id;
		this.descStatus = descStatus;
	}
	
	public static StatusConsultaEnum toEnum(Integer id) {
		
		if(id==null)
			return null;
		
		for(StatusConsultaEnum p : StatusConsultaEnum.values()){
			if(id.equals(p.getId())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
