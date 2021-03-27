package com.tcc.DTO;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.domain.Usuario;
import com.tcc.enums.PerfilEnum;



public class UserDTO extends CredentialsDTO{

	private static final long serialVersionUID = 1L;
	
	private Long idUser;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo Nome")
	@Size(max=255, message="Tamanho máximo de 255 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo Sexo")
	@Size(max=1, message="Tamanho máximo de 1 caracter")
	private String sexo;
	
	@NotEmpty(message="Preenchimento obrigatório para o campo Telefone")
	@Size(min=8, max=11, message="o número de telefone deve ter entre 8 e 11 digitos")
	private String telefone;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dtNascimento;
	
	private Integer perfil;
	
	private Set<PerfilEnum> perfisCadastrados;
	
	private EnderecoDTO endereco;
	
	private String cpf;
	
	private MedicoDTO medico;
	
	private PacienteDTO paciente;
	
	public MedicoDTO getMedico() {
		return medico;
	}
	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}
	public PacienteDTO getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getPerfil() {
		return perfil;
	}
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
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
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public Set<PerfilEnum> getPerfisCadastrados() {
		return perfisCadastrados;
	}
	public void setPerfisCadastrados(Set<PerfilEnum> perfisCadastrados) {
		this.perfisCadastrados = perfisCadastrados;
	}
	public UserDTO(	String nome, String sexo, String telefone, LocalDate dtNascimento, Integer perfil, EnderecoDTO endereco) {
		this.nome = nome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dtNascimento = dtNascimento;
		this.perfil = perfil;
		this.endereco = endereco;
	}
	public UserDTO(String nome, String sexo, String telefone, LocalDate dtNascimento, String email, 
			EnderecoDTO enderecoDTO) {
		super(email,"");
		this.nome = nome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dtNascimento = dtNascimento;
		this.endereco = enderecoDTO;
	}
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Usuario usuario) {
		super();
		this.cpf = usuario.getCpf();
		this.setEmail(usuario.getEmail());
		this.dtNascimento = usuario.getDtNascimento();
		if(usuario.getEndereco()!=null)
			this.endereco = new EnderecoDTO(usuario.getEndereco());
		this.idUser = usuario.getId();
		this.nome = usuario.getNome();
		this.sexo = String.valueOf(usuario.getSexo());
		this.telefone = usuario.getTelefone();
		this.perfisCadastrados = usuario.getPerfis();
	}
	
	
	
	
	
	
	
}
