package com.tcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.AlergiaDTO;
import com.tcc.domain.Alergia;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.TipoAlergia;
import com.tcc.domain.User;
import com.tcc.repository.AlergiaRepository;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.TipoAlergiaRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.NoElementException;

@Service
public class AlergiaService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AlergiaRepository alergiaRepository;

	@Autowired
	private TipoAlergiaRepository tipoAlergiaRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	public List<Alergia> addAlergia(List<AlergiaDTO> alergias) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<Alergia> list = new ArrayList<>();
			
			try{
				for(AlergiaDTO dto : alergias) {
					Alergia a = new Alergia();
					a.setId(null);
					a.setDadosMedicos(dados);
					a.setDescAlergia(dto.getDescAlergia());
					TipoAlergia tipoAlergia = this.tipoAlergiaRepository.findById(dto.getIdTipoAlergia()).orElseThrow();
					a.setTipoAlergia(tipoAlergia);
					list.add(a);
				}
			}catch(NoSuchElementException e) {
				throw new NoElementException("Tipo de alergia não encontrado");
			}
			this.alergiaRepository.saveAll(list);
			return list;
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
	
	public List<TipoAlergia> getAllTipoAlergia(){
		try {
			List<TipoAlergia> tipos = this.tipoAlergiaRepository.findAll();
			return tipos;
		}catch(NoSuchElementException e) {
			throw new NoElementException("Tipos de Alergia não encontrados");
		}
		
	}

	public List<Alergia> update(@Valid List<AlergiaDTO> alergias) {
		UserSecurity logado = UserService.authenticated();
		try {		
			if(logado!=null) {
				Long id = logado.getId();
				User usuario = this.userRepository.findById(id).orElseThrow();
				DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
				List<Alergia> list = new ArrayList<>();
				for(AlergiaDTO dto : alergias) {
					Alergia a = this.alergiaRepository.findById(dto.getId()).orElseThrow();
					a.setDadosMedicos(dados);
					a.setDescAlergia(dto.getDescAlergia());
					TipoAlergia tipoAlergia = this.tipoAlergiaRepository.findById(dto.getIdTipoAlergia()).orElseThrow();
					a.setTipoAlergia(tipoAlergia);
					list.add(a);
				}
				this.alergiaRepository.saveAll(list);
				return list;
			}else {
				throw new NoElementException("Usuário inválido, tente logar novamente");
			}
		}catch(NoSuchElementException e) {
			throw new NoElementException("Alergia não encontrada");
		}
	}

	public void deleteAlergia(Long idAlergia) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<Alergia> list = this.alergiaRepository.findByDadosMedicos(dados);
			if(list!=null && !list.isEmpty()) {
				list = list.stream().filter(m -> m.getId() == idAlergia).collect(Collectors.toList());
				if(list.isEmpty())
					throw new NoElementException("Não foram encontradas alergias para atualizar");
				
				this.alergiaRepository.delete(list.get(0));
			}else {
				throw new NoElementException("Não foram encontradas alergias para atualizar");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
		
	}


}
