package com.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcc.DTO.PacienteDTO;
import com.tcc.DTO.PacienteFilter;
import com.tcc.domain.Paciente;
import com.tcc.domain.Usuario;
import com.tcc.repository.PacienteRepository;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;

@Service
public class PacienteService {

	@Autowired 
	private PacienteRepository repository;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	
	public List<PacienteDTO> filter(PacienteFilter filter) {
		List<Paciente> pacientes = this.repository.findByUsuario_nomeContainsIgnoreCase(filter.getNomePaciente());
		if(pacientes!=null && !pacientes.isEmpty())
			return pacientes.stream().map(p -> new PacienteDTO(p)).collect(Collectors.toList());
		else
			throw new NoElementException("Paciente não encontrado");
	}
	
	public Paciente getById(Long id) {
		try {
			return this.repository.findById(id).orElseThrow();			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Paciente não encontrado");
		}
	}
	
	public PacienteDTO getByIdDTO(Long id) {
		try {
			Paciente p = getById(id);
			return new PacienteDTO(p);			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Paciente não encontrado");
		}
	}
	
	public Paciente save(PacienteDTO dto) {
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Paciente p = u.getPaciente();
		p.setDescConvenio(dto.getDescConvenio());
		p.setNuInscricaoConvenio(dto.getNuInscricaoConvenio());
		u = this.usuarioService.removePendente(u);
		p.setCompartilhaDados(dto.isCompartilhaDados());
		try {
			return this.repository.save(p);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao atualizar paciente");
		}
	}
	
	public void update(PacienteDTO dto) {
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Paciente p = getById(dto.getIdPaciente());
		p.setCompartilhaDados(dto.isCompartilhaDados());
		p.setDescConvenio(dto.getDescConvenio());
		p.setNuInscricaoConvenio(dto.getNuInscricaoConvenio());
		u = this.usuarioService.removePendente(u);
		try {
			this.repository.save(p);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao atualizar paciente");
		}
	}

	public Page<PacienteDTO> findAll(Pageable p) {
		Page<Paciente> pacientes = this.repository.findAll(p); 
		return toPageDTO(p,pacientes);
	}
		
	private Page<PacienteDTO> toPageDTO(Pageable p, Page<Paciente> pacientes){
		 if(!pacientes.isEmpty()) {
		    return new PageImpl<PacienteDTO>(
		    		pacientes.getContent().stream().map(paciente -> new PacienteDTO(paciente)).collect(Collectors.toList()), p, pacientes.getTotalElements());
		}else {
		  	throw new NoElementException("Não existem pacientes cadastrados para os parametros informados");
		}
	}
	
}
