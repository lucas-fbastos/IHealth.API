package com.tcc.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.DTO.UpdatePasswordFormDTO;
import com.tcc.DTO.UserDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Endereco;
import com.tcc.domain.Usuario;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.EnderecoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.UserDeslogadoException;

@Service
public class UsuarioService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Usuario saveUser(UserDTO dto) {
		
		try {
			Usuario user = new Usuario(dto);
			
			user.setId(null);
			user.setSexo(dto.getSexo().charAt(0));
			user.setNome(dto.getNome());
			user.setDtNascimento(dto.getDtNascimento());
			user.setPassword(encoder.encode("12345678"));
			user.addPerfil(PerfilEnum.PENDENTE);
			user.addPerfil(PerfilEnum.values()[dto.getPerfil()]);
			user.setDtCadastro(new Date());			
			user.setCpf(dto.getCpf());
			this.userRepository.save(user);
			
			if(user.getPerfis().contains(PerfilEnum.PACIENTE)) {
				
				DadosMedicos dados = new DadosMedicos();
				dados.setUser(user);
				dados.setDtAtualizacao(LocalDateTime.now());
				this.dadosMedicosRepository.save(dados);
			}
			if(dto.getEndereco()!=null) {
				Endereco e = new Endereco(dto.getEndereco());
				e.setUsuarioEndereco(user);
				this.enderecoRepository.save(e);
				user.setEndereco(e);
			}
			
			return user;
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Já existe um usuário com esse E-mail cadastrado. E-mail: "+dto.getEmail());
		}
	}
	
	public Usuario updateUser(UserDTO dto) {
		try {
			Usuario userSalvo = getUserLogado();
			userSalvo.setNome(dto.getNome());
			userSalvo.setTelefone(dto.getTelefone());
			userSalvo.setDtNascimento(dto.getDtNascimento());
			
			this.userRepository.save(userSalvo);
			
			return userSalvo;
			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Usuário não encontrado");
		}
	}
	
	public void updatePassword(UpdatePasswordFormDTO form) {
		Usuario u = this.getUserLogado();
		if(form.getPassword().equals(form.getPasswordConfirmation())) {
			u.setPassword(this.encoder.encode(form.getPassword()));
			this.userRepository.save(u);
		}
			
		
	}
	
	public static UserSecurity authenticated() {
		UserSecurity userLogado = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		if(userLogado!=null && userLogado.isCredentialsNonExpired()) 
			return userLogado;
		else
			throw new UserDeslogadoException("Usuário inválido, tente logar novamente");
	}
	
	public Usuario getUserLogado() {
		UserSecurity logado = authenticated();
		return this.userRepository.findById(logado.getId()).orElseThrow();
	}

	public Usuario getById(Long userId) {
		return this.userRepository.findById(userId).orElseThrow();
	}
}
