package com.tcc.DTO;

import java.util.Set;

import com.tcc.domain.Especializacao;
import com.tcc.domain.Medico;
import com.tcc.domain.Usuario;

public class MedicoDTO extends UserDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String crm;
	private Set<Especializacao> especializacoes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<Especializacao> getEspecializacoes() {
		return especializacoes;
	}
	public void setEspecializacoes(Set<Especializacao> especializacoes) {
		this.especializacoes = especializacoes;
	}
	
	public MedicoDTO() {
	}
	
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	
	public MedicoDTO(Long id,Set<Especializacao> especializacoes,String crm, Usuario usuario) {
		super(usuario);
		this.id = id;
		this.nome = usuario.getNome();
		this.crm = crm;
		this.especializacoes = especializacoes;
		this.cpf = usuario.getCpf();
		this.telefone = usuario.getTelefone();
	}
	
	public MedicoDTO(Medico medico) {
		super(medico.getUsuario());
		this.crm = medico.getCrm();
		this.especializacoes = medico.getEspecializacoes();
		this.id = medico.getId();
	}
	
	
	
}
