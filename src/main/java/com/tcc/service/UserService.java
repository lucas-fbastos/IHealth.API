package com.tcc.service;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.DTO.UserDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.User;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	public User saveUser(UserDTO dto) {
		try {
			User user = new User(dto);
			user.setId(null);
			user.setSexo(dto.getSexo().charAt(0));
			user.setNome(dto.getNome());
			user.setDtNascimento(dto.getDtNascimento());
			user.setPassword(encoder.encode(user.getPassword()));
			user.addPerfil(PerfilEnum.PENDENTE);
			Date date = new Date();  
			user.setDtCadastro(date);			
			this.userRepository.save(user);
			DadosMedicos dados = new DadosMedicos();
			dados.setUser(user);
			dados.setDt_atualizacao(new Date());
			this.dadosMedicosRepository.save(dados);
			return user;
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Já existe um usuário com esse E-mail cadastrado. E-mail: "+dto.getEmail());
		}
	}
	
	public User updateUser(UserDTO dto) {
		try {
			UserSecurity logado = authenticated();
			if(logado!=null) {
				Long id = logado.getId();
				User userSalvo = this.userRepository.findById(id).orElseThrow();
				userSalvo.setEmail(dto.getEmail());
				userSalvo.setNome(dto.getNome());
				userSalvo.setSexo(dto.getSexo().charAt(0));
				userSalvo.setTelefone(dto.getTelefone());
				userSalvo.setDtNascimento(dto.getDtNascimento());
				this.userRepository.save(userSalvo);
				return userSalvo;
			}else {
				throw new NoElementException("Usuário inválido, tente logar novamente");
			}
		}catch(NoSuchElementException e) {
			throw new NoElementException("Usuário não encontrado");
		}
	}
	
	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		}catch(Exception e) {
			return null;
		}
	}
}
