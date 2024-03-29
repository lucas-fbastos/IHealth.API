package com.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tipo_sanguineo", schema="public")
public class TipoSanguineo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="desc_tipo_sanguineo")
	private String descricaoTipoSanguineo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricaoTipoSanguineo() {
		return descricaoTipoSanguineo;
	}

	public void setDescricaoTipoSanguineo(String descricaoTipoSanguineo) {
		this.descricaoTipoSanguineo = descricaoTipoSanguineo;
	}

	public TipoSanguineo(Integer id, String descricaoTipoSanguineo) {
		super();
		this.id = id;
		this.descricaoTipoSanguineo = descricaoTipoSanguineo;
	}

	public TipoSanguineo() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoSanguineo other = (TipoSanguineo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
