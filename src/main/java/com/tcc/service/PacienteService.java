package com.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tcc.DTO.PacienteDTO;
import com.tcc.domain.Paciente;
import com.tcc.domain.Usuario;
import com.tcc.repository.PacienteRepository;
import com.tcc.service.exceptions.DataIntegrityException;

@Service
public class PacienteService {

	@Autowired 
	private PacienteRepository repository;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	
	public PacienteDTO getById(Long id) {
		Paciente p = this.repository.findById(id).orElseThrow();
		return new PacienteDTO(p);
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
