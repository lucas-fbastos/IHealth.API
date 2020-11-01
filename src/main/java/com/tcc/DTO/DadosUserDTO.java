package com.tcc.DTO;

import java.util.ArrayList;
import java.util.List;

import com.tcc.domain.Alergia;
import com.tcc.domain.DoencaCronica;
import com.tcc.domain.Medicamento;
import com.tcc.domain.TipoSanguineo;

public class DadosUserDTO{
	static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private TipoSanguineo tipoSanguineo;
	private Double altura;
	private Double peso;
	private Double vlImc;
	private String descImc;
	private Integer idade;
	private List<Alergia> alergias = new ArrayList<>();
	private List<Medicamento> medicamentos= new ArrayList<>();
	private List<DoencaCronica> doencasCronicas = new ArrayList<>();
	private String nomeProfissionalSaude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getNomeProfissionalSaude() {
		return nomeProfissionalSaude;
	}
	public void setNomeProfissionalSaude(String nmProfissionalSaude) {
		this.nomeProfissionalSaude = nmProfissionalSaude;
	}
	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Alergia> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<Alergia> alergias) {
		this.alergias = alergias;
	}
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public List<DoencaCronica> getDoencasCronicas() {
		return doencasCronicas;
	}
	public void setDoencasCronicas(List<DoencaCronica> doencasCronicas) {
		this.doencasCronicas = doencasCronicas;
	}
	
	public void addDoencasCronicas(DoencaCronica doencasCronica) {
		this.doencasCronicas.add(doencasCronica);
	}
	
	public void addAlergia(Alergia alergia) {
		this.alergias.add(alergia);
	}
	
	public void addMedicamento(Medicamento medicamento) {
		this.medicamentos.add(medicamento);
	}

	public DadosUserDTO() {
		super();
	}
	
	
}
