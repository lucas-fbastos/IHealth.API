package com.tcc.DTO;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;



public class UserDTO extends CredentialsDTO{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo Nome")
	@Size(max=255, message="Tamanho máximo de 255 caracteres")
	public String nome;
	
	@NotNull(message="Preenchimento obrigatório para o campo Sexo")
	@Size(max=1,min=1,message="Formato inválido para o campo Sexo")
	public String sexo;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo Telefone")
	@Size(min=8, max=11, message="o número de telefone deve ter entre 8 e 11 digitos")
	public String telefone;
	
	@NotNull(message="Preenchimento obrigatório para o campo Data de Nascimento")
	@JsonFormat(pattern="dd-mm-yyyy")
	public Date dtNascimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	
	public UserDTO(String nome, String sexo, String telefone, Date dtNascimento, String email, String senha) {
		super(email, senha);
		this.nome = nome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dtNascimento = dtNascimento;
	}
	
	public UserDTO() {
		super();
	}
	
	
	
	
	
}
