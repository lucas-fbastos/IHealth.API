package com.tcc.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tcc.DTO.UserDTO;
import com.tcc.enums.PerfilEnum;

@Entity
@Table(name="user", schema="public")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(name="dt_nascimento")
	private LocalDate dtNascimento;
	
	@Column(name="dt_cadastro")
	private java.util.Date dtCadastro;
	
	@JsonIgnore
	private String password;
	
	private String telefone;
	
	private char sexo;
	
	@Column(unique=true)
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@OneToOne(mappedBy="usuarioEndereco")
	@JsonManagedReference
	private Endereco endereco;
	
	@OneToOne(mappedBy="usuario")
	@JsonManagedReference
	private Medico medico;
	
	@OneToOne(mappedBy="usuario")
	@JsonManagedReference
	private Paciente paciente;
	
	@Column(name="nu_cpf")
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

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

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public java.util.Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(java.util.Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(p -> PerfilEnum.toEnum(p)).collect(Collectors.toSet());
	}
	
	public void addPerfil(PerfilEnum perfil) {
		perfis.add(perfil.getId());
	}
	
	public void removePerfil(PerfilEnum perfil) {
		perfis.removeIf(p -> p == perfil.getId());
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario(Long id, String nome, LocalDate dtNascimento, Date dtCadastro, String password, String telefone, char sexo,
			String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.dtCadastro = dtCadastro;
		this.password = password;
		this.telefone = telefone;
		this.sexo = sexo;
		this.email = email;
	}

	public Usuario() {	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public Usuario(UserDTO dto) {
		this.dtNascimento = dto.getDtNascimento();
		this.email= dto.getEmail();
		this.password = dto.getSenha();
		this.sexo =  dto.getSexo().charAt(0);
		this.telefone= dto.getTelefone();
		this.nome= dto.getNome();
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}