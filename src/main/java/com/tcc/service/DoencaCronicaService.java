package com.tcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.DoencaCronicaDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.DoencaCronica;
import com.tcc.domain.User;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.DoencaCronicaRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.NoElementException;

@Service
public class DoencaCronicaService {

	@Autowired
	private DoencaCronicaRepository doencaCronicaRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	public List<DoencaCronica> save(List<DoencaCronicaDTO> listDTO) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<DoencaCronica> list = new ArrayList<>();
			
			try{
				for(DoencaCronicaDTO dto : listDTO) {
					DoencaCronica doenca = new DoencaCronica();
					doenca.setId(null);
					doenca.setDadosMedicos(dados);
					doenca.setDescDoenca(dto.getDescDoenca());
					list.add(doenca);
				}
			return this.doencaCronicaRepository.saveAll(list);
			}catch(NoSuchElementException e) {
				throw new NoElementException("Informação não encontrada");
			}
		}
		throw new NoElementException("Usuário não encontrado");
	}
	
	public DoencaCronica save(DoencaCronicaDTO doencaDTO, User paciente) {
		DadosMedicos dados = this.dadosMedicosRepository.findByUser(paciente).orElseThrow();	
		try{
			UserSecurity logado = UserService.authenticated();
			if(logado!=null) {
				Long id = logado.getId();
				User usuario = this.userRepository.findById(id).orElseThrow();
				DoencaCronica doenca = new DoencaCronica();
				doenca.setId(null);
				doenca.setDadosMedicos(dados);
				doenca.setProfissionalSaude(usuario);
				doenca.setDescDoenca(doencaDTO.getDescDoenca());
				return this.doencaCronicaRepository.save(doenca);
			}else {
				throw new NoElementException("Usuário inválido, tente logar novamente");
			}
		}catch(NoSuchElementException e) {
			throw new NoElementException("Informação não encontrada");
		}
		
	}

	public List<DoencaCronica> updateDoenca(List<DoencaCronicaDTO> doencas) {
		UserSecurity logado = UserService.authenticated();
		try {		
			if(logado!=null) {
				Long id = logado.getId();
				User usuario = this.userRepository.findById(id).orElseThrow();
				DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
				List<DoencaCronica> list = new ArrayList<>();
				for(DoencaCronicaDTO dto : doencas) {
					DoencaCronica doenca = this.doencaCronicaRepository.findById(dto.getId()).orElseThrow();
					doenca.setDadosMedicos(dados);
					doenca.setDescDoenca(dto.getDescDoenca());
					doenca.setProfissionalSaude(null);
					list.add(doenca);
				}
				this.doencaCronicaRepository.saveAll(list);
				return list;
			}else {
				throw new NoElementException("Usuário inválido, tente logar novamente");
			}
		}catch(NoSuchElementException e) {
			throw new NoElementException("Doença não encontrada");
		}
	}

	public void deleteDoenca(Long idDoenca) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<DoencaCronica> list = this.doencaCronicaRepository.findByDadosMedicos(dados);
			if(list!=null && !list.isEmpty()) {
				list = list.stream().filter(m -> m.getId() == idDoenca).collect(Collectors.toList());
				if(list.isEmpty())
					throw new NoElementException("Não foram encontradas doencas para atualizar");
				
				this.doencaCronicaRepository.delete(list.get(0));
			}else {
				throw new NoElementException("Não foram encontradas doencas para atualizar");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
		
	}
	
}
