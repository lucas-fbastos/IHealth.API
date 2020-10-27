package com.tcc.DTO;

import java.util.List;

import com.tcc.domain.Alergia;

public class DadosUserDTO{
	static final long serialVersionUID = 1L;
	
	private String nome;
	private String tipoSanguineo;
	private Double altura;
	private Double peso;
	private Double imc;
	private String imcDesc;
	private List<ProcedimentoMedicoDTO> procedimentos;
	private List<Alergia> alergias;
	private List<MedicamentoDTO> medicamentos;
	private List<DoencaCronicaDTO> doencasCronicas;
	
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
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
	public Double getImc() {
		return imc;
	}
	public void setImc(Double imc) {
		this.imc = imc;
	}
	public String getImcDesc() {
		return imcDesc;
	}
	public void setImcDesc(String imcDesc) {
		this.imcDesc = imcDesc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<ProcedimentoMedicoDTO> getProcedimentos() {
		return procedimentos;
	}
	public void setProcedimentos(List<ProcedimentoMedicoDTO> procedimentos) {
		this.procedimentos = procedimentos;
	}
	public List<Alergia> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<Alergia> alergias) {
		this.alergias = alergias;
	}
	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public List<DoencaCronicaDTO> getDoencasCronicas() {
		return doencasCronicas;
	}
	public void setDoencasCronicas(List<DoencaCronicaDTO> doencasCronicas) {
		this.doencasCronicas = doencasCronicas;
	}
	
	public void addDoencasCronicas(DoencaCronicaDTO doencasCronica) {
		this.doencasCronicas.add(doencasCronica);
	}
	
	public void addAlergia(Alergia alergia) {
		this.alergias.add(alergia);
	}
	
	public void addMedicamento(MedicamentoDTO medicamento) {
		this.medicamentos.add(medicamento);
	}
	
	public void addProcedimentoMedico(ProcedimentoMedicoDTO procedimento) {
		this.procedimentos.add(procedimento);
	}
	
	
	public DadosUserDTO() {
		super();
	}
	
	
}
