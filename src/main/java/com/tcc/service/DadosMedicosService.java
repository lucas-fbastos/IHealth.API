package com.tcc.service;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.User;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.NoElementException;

@Service
public class DadosMedicosService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	public DadosMedicos update(DadosMedicosDTO dto) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			try {
				User user = this.userRepository.findById(id).orElseThrow();
				DadosMedicos dados = this.dadosMedicosRepository.findByUser(user).orElseThrow();
				dados.setDt_atualizacao(new Date());
				dados.setTipoSanguineo(dto.getTipoSanguineo());
				this.dadosMedicosRepository.save(dados);
				return dados;
			}catch(NoSuchElementException e) {
				throw new NoElementException("Usuário não encontrado");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
	
}
