package com.tcc.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="prontuario",schema="public")
public class Prontuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="consulta_id", referencedColumnName="id")
	private Consulta consulta;

	@Column(name="desc_prontuario")
	private String descProntuario;
	
	@Column(name="alergia")
	private Boolean temAlergia;
	
	@Column(name="doenca_cronica")
	private Boolean temDoencaCronica;
	
	@Column(name="concordou_termos")
	private Boolean concordouTermos;
	
	@Column(name="desc_diagnostico")
	private String diagnostico;
	
	@Column(name="desc_sumario")
	private String descSumario;

	@OneToMany(mappedBy="prontuario")
	@JsonManagedReference
	@JsonIgnore
	private List<DocumentoMedico> documentos;
	
	@OneToMany(mappedBy="prontuario")
	@JsonManagedReference("doenca-prontuario")
	private Set<DoencaCronica> doencasCronicas = new HashSet<>();
	
	@OneToMany(mappedBy="prontuario")
	@JsonManagedReference("medicamento-prontuario")
	private Set<Medicamento> medicamentos = new HashSet<>();
	
	@OneToMany(mappedBy="prontuario")
	@JsonManagedReference("alergia-prontuario")
	private Set<Alergia> alergias = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getDescProntuario() {
		return descProntuario;
	}

	public void setDescProntuario(String descProntuario) {
		this.descProntuario = descProntuario;
	}

	public Boolean getTemAlergia() {
		return temAlergia;
	}

	public void setTemAlergia(Boolean temAlergia) {
		this.temAlergia = temAlergia;
	}

	public Boolean getTemDoencaCronica() {
		return temDoencaCronica;
	}

	public void setTemDoencaCronica(Boolean temDoencaCronica) {
		this.temDoencaCronica = temDoencaCronica;
	}

	public Boolean getConcordouTermos() {
		return concordouTermos;
	}

	public void setConcordouTermos(Boolean concordouTermos) {
		this.concordouTermos = concordouTermos;
	}

	public String getDescSumario() {
		return descSumario;
	}

	public void setDescSumario(String descSumario) {
		this.descSumario = descSumario;
	}

	public List<DocumentoMedico> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoMedico> documentos) {
		this.documentos = documentos;
	}

	public Set<DoencaCronica> getDoencasCronicas() {
		return doencasCronicas;
	}

	public void setDoencasCronicas(Set<DoencaCronica> doencasCronicas) {
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

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
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
		Prontuario other = (Prontuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
