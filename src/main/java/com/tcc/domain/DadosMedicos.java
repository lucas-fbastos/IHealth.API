package com.tcc.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="dados_medicos", schema="public")
public class DadosMedicos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_tipo_sanguineo")
	private TipoSanguineo tipoSanguineo;
	
	@Column(name="dt_atualizacao")
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime  dtAtualizacao;
	
	@OneToOne
	@JoinColumn(name="id_paciente", referencedColumnName="id")
	@JsonBackReference("paciente-dados")
	private Paciente paciente;

	@OneToMany(mappedBy="dadosMedicos")
	@JsonManagedReference("doenca-dados")
	private Set<DoencaCronica> doencasCronicas = new HashSet<>();
	
	@OneToMany(mappedBy="dadosMedicos")
	@JsonManagedReference("medicamento-dados")
	private Set<Medicamento> medicamentos = new HashSet<>();
	
	@Column(name="peso")
	private Double peso;
		
	@Column(name="altura")
	private Double altura;
	
	@Column(name="vl_imc")
	private Double vlImc;
	
	@Column(name="situacao_imc")
	private String descImc;

	@OneToMany(mappedBy="dadosMedicos")
	@JsonManagedReference("alergia-dados")
	private Set<Alergia> alergias = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime  getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(LocalDateTime  dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
	
	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public void setAlergias(Set<Alergia> alergias) {
		this.alergias = alergias;
	}

	public DadosMedicos() {
		super();
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getVlImc() {
		return vlImc;
	}

	public void setVlImc(Double vlImc) {
		this.vlImc = vlImc;
	}

	public String getDescImc() {
		return descImc;
	}

	public void setDescImc(String descImc) {
		this.descImc = descImc;
	}

	public DadosMedicos(Long id, TipoSanguineo tipoSanguineo, LocalDateTime  dtAtualizacao, Paciente paciente,
			Set<DoencaCronica> doencasCronicas, Set<Medicamento> medicamentos, Double peso, Double altura, Double vlImc,
			String descImc, Set<Alergia> alergias) {
		super();
		this.id = id;
		this.tipoSanguineo = tipoSanguineo;
		this.dtAtualizacao = dtAtualizacao;
		this.paciente = paciente;
		this.doencasCronicas = doencasCronicas;
		this.medicamentos = medicamentos;
		this.peso = peso;
		this.altura = altura;
		this.vlImc = vlImc;
		this.descImc = descImc;
		this.alergias = alergias;
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
