package com.tcc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="dados_medicos")
public class DadosMedicos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String tipoSanguineo;
	
	private Date dt_atualizacao;
	
	@OneToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy="dadosMedicos")
	private Set<DoencasCronicas> doencasCronicas = new HashSet<>();
	
	@OneToMany(mappedBy="dadosMedicos")
	private Set<Medicamento> medicamentos = new HashSet<>();
	
	@OneToMany(mappedBy="dadosMedicos")
	private Set<Alergia> alergias = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public Date getDt_atualizacao() {
		return dt_atualizacao;
	}

	public void setDt_atualizacao(Date dt_atualizacao) {
		this.dt_atualizacao = dt_atualizacao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<DoencasCronicas> getDoencasCronicas() {
		return doencasCronicas;
	}

	public void setDoencasCronicas(Set<DoencasCronicas> doencasCronicas) {
		this.doencasCronicas = doencasCronicas;
	}

	public Set<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(Set<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Set<Alergia> getAlergias() {
		return alergias;
	}

	public void setAlergias(Set<Alergia> alergias) {
		this.alergias = alergias;
	}

	public DadosMedicos() {
		super();
	}

	public DadosMedicos(Long id, String tipoSanguineo, Date dt_atualizacao, User user) {
		super();
		this.id = id;
		this.tipoSanguineo = tipoSanguineo;
		this.dt_atualizacao = dt_atualizacao;
		this.user = user;
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
		DadosMedicos other = (DadosMedicos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
