package com.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		List<Paciente> pacientes = this.repository.findByUsuario_nomeContains(filter.getNomePaciente());
		if(pacientes!=null && !pacientes.isEmpty())
			return pacientes.stream().map(p -> new PacienteDTO(p)).collect(Collectors.toList());
		else
			throw new NoElementException("Paciente não encontrado");
	}
	
	
	public PacienteDTO getById(Long id) {
		try {
			Paciente p = this.repository.findById(id).orElseThrow();
			return new PacienteDTO(p);			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Paciente não encontrado");
		}
	}
	
	public Paciente save(PacienteDTO dto) {
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Paciente p = new Paciente();
		p.setDescConvenio(dto.getDescConvenio());
		p.setNuInscricaoConvenio(dto.getNuInscricaoConvenio());
		u = this.usuarioService.removePendente(u);
		p.setUsuario(u);
		p.setCompartilhaDados(dto.isCompartilhaDados());
		try {
			return this.repository.save(p);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar paciente");
		}
	}
	
	public void update(PacienteDTO dto) {
		Paciente p = this.repository.findById(dto.getIdPaciente()).orElseThrow();
		p.setCompartilhaDados(dto.isCompartilhaDados());
		p.setDescConvenio(dto.getDescConvenio());
		p.setNuInscricaoConvenio(dto.getNuInscricaoConvenio());
		try {
			this.repository.save(p);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao atualizar paciente");
		}
	}
	
}
