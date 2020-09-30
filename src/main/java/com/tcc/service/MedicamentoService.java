package com.tcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.MedicamentoDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Medicamento;
import com.tcc.domain.User;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.MedicamentoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.NoElementException;

@Service
public class MedicamentoService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	

	public List<Medicamento> addMedicamentos(List<MedicamentoDTO> medicamentos) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<Medicamento> list = new ArrayList<>();
			for(MedicamentoDTO dto : medicamentos) {
				Medicamento m = new Medicamento();
				m.setId(null);
				m.setDadosMedicos(dados);
				m.setDescMedicamento(dto.getDesc());
				list.add(m);
			}
			this.medicamentoRepository.saveAll(list);
			return list;
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}

	public void deleteMedicamento(Long idMed) {
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<Medicamento> list = this.medicamentoRepository.findByDadosMedicos(dados);
			if(list!=null && !list.isEmpty()) {
				list = list.stream().filter(m -> m.getId() == idMed).collect(Collectors.toList());
				this.medicamentoRepository.delete(list.get(0));
			}else {
				throw new NoElementException("Não foram encontrados medicamentos para atualizar");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
	
	
	public List<Medicamento> update(List<MedicamentoDTO> medicamentos){
		UserSecurity logado = UserService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			User usuario = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(usuario).orElseThrow();
			List<Medicamento> list = new ArrayList<>();
			for(MedicamentoDTO dto : medicamentos) {
				Medicamento m = this.medicamentoRepository.findById(dto.getId()).orElseThrow();
				m.setDadosMedicos(dados);
				m.setDescMedicamento(dto.getDesc());
				list.add(m);
			}
			this.medicamentoRepository.saveAll(list);
			return list;
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
}
