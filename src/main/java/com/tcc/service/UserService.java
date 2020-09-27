package com.tcc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.DTO.UserDTO;
import com.tcc.domain.User;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.UserRepository;
import com.tcc.service.exceptions.DataIntegrityException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public User saveUser(UserDTO dto) {
		try {
			User user = new User(dto);
			user.setId(null);
			user.setSexo(dto.getSexo().charAt(0));
			user.setNome(dto.getNome());
			user.setPassword(encoder.encode(user.getPassword()));
			user.addPerfil(PerfilEnum.PENDENTE);
			Date date = new Date();  
			user.setDtCadastro(date);
			this.userRepository.save(user);
			return user;
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Já existe um usuário com esse E-mail cadastrado. E-mail: "+dto.getEmail());
		}
	}
}
