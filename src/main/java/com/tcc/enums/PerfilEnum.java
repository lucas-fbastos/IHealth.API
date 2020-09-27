package com.tcc.enums;

public enum PerfilEnum {

	PENDENTE(1,"ROLE_PENDENTE"),
	ATIVO(1,"ROLE_ATIVO");
	
	private int id;
	private String desc;
	
	private PerfilEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static PerfilEnum toEnum(Integer id) {
		
		if(id==null)
			return null;
		
		for(PerfilEnum p : PerfilEnum.values()){
			if(id.equals(p.getId())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
	
}
