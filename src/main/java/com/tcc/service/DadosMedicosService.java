package com.tcc.service;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.TipoSanguineo;
import com.tcc.domain.User;
import com.tcc.enums.IMCEnum;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.TipoSanguineoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.NoElementException;

@Service
public class DadosMedicosService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	public DadosMedicos update(DadosMedicosDTO dto) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			try {
				User user = this.userRepository.findById(id).orElseThrow();
				DadosMedicos dados = this.dadosMedicosRepository.findByUser(user).orElseThrow();
				dados.setDtAtualizacao(new Date());
				if(dto.getTipoSanguineo()!=null) {
					TipoSanguineo tipoSanguineo =  this.tipoSanguineoRepository.findById(dto.getTipoSanguineo()).orElseThrow();
					dados.setTipoSanguineo(tipoSanguineo);					
				}
				dados.setAltura(dto.getAltura());
				dados.setPeso(dto.getPeso());
				this.calculaImc(dados);
				this.dadosMedicosRepository.save(dados);
				return dados;
			}catch(NoSuchElementException e) {
				throw new NoElementException("informação não encontrada");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}

	public DadosMedicos getDadosMedicos() {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			try {
				User user = this.userRepository.findById(id).orElseThrow();
				return this.dadosMedicosRepository.findByUser(user).orElseThrow();
			}catch(NoSuchElementException e) {
				throw new NoElementException("Usuário não encontrado");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
	
	public void calculaImc(DadosMedicos dados) {
		if(dados.getAltura() > 0) {
			Double imc = dados.getPeso()/(dados.getAltura() * dados.getAltura());
			IMCEnum descImc = IMCEnum.getImcDesc(imc);
			dados.setDescImc(descImc.getDescricao());
			dados.setVlImc(imc);
		}
	}

	
	
}
