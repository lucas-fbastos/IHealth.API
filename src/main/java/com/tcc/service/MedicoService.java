package com.tcc.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcc.DTO.MedicoDTO;
import com.tcc.DTO.MedicoFormDTO;
import com.tcc.domain.Clinica;
import com.tcc.domain.Especializacao;
import com.tcc.domain.Medico;
import com.tcc.domain.Usuario;
import com.tcc.repository.EspecializacaoRepository;
import com.tcc.repository.MedicoRepository;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.ObjetoInvalidoException;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;
	
	@Autowired
	private EspecializacaoRepository especializacaoRepository;
	
	@Autowired
	private ClinicaService clinicaService;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	public List<MedicoDTO> getByEspecializacao(Long id) {
		List<Medico> list = this.repository.findByEspecializacoes_id(id);
		List<MedicoDTO> dto = new ArrayList<>();
		if(list!=null && !list.isEmpty())
			list.forEach(m -> dto.add(new MedicoDTO(m.getId(),m.getEspecializacoes(),m.getCrm(),m.getUsuario())));
		else
			throw new NoElementException("Não existem médicos cadastrados para essa especialização");
		return dto;
	}

	public List<MedicoDTO> getAll(){
		List<Medico> list = this.repository.findAll();
		List<MedicoDTO> dto = new ArrayList<>();
		if(list!=null && !list.isEmpty())
			list.forEach(m -> dto.add(new MedicoDTO(m.getId(),m.getEspecializacoes(),m.getCrm(),m.getUsuario())));
		else
			throw new NoElementException("Não existem médicos cadastrados");
		return dto;
	}
	
	public MedicoDTO getDTOById(Long id){
		Medico m = getById(id);
		return new MedicoDTO(m.getId(),m.getEspecializacoes(),m.getCrm(),m.getUsuario());
	}

	public Medico getById(Long id){
		try {
			return this.repository.findById(id).orElseThrow();			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Médico não encontrado");
		}
		
	}
	
	public Medico save(MedicoFormDTO dto) {
		validaHorario(dto.getHrEntrada(),dto.getHrSaida());
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Medico m = new Medico();
		m.setCrm(dto.getCrm());
		List<Especializacao> especializacoes = this.especializacaoRepository.findByIdIn(dto.getEspecializacoes());
		Set<Especializacao> especializacoesSet = especializacoes.stream().collect(Collectors.toSet());
		m.setEspecializacoes(especializacoesSet);
		u = this.usuarioService.removePendente(u);
		m.setUsuario(u);
		m.setHrEntrada(dto.getHrEntrada());
		m.setHrSaida(dto.getHrSaida());
		try {
			return this.repository.save(m);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar Médico");
		}
	}

	private void validaHorario(LocalTime hrEntrada, LocalTime hrSaida) {
		if(hrEntrada!=null && hrSaida !=null) {
			Clinica c = this.clinicaService.getDadosClinica();
			if(c.getDtAbertura().isAfter(hrEntrada) || c.getDtEncerramento().isBefore(hrSaida))
				throw new ObjetoInvalidoException("Horários de entrada e/ou saída fora dos horários de funcionamento");
		}
		
	}

	public void update(MedicoFormDTO dto) {
		validaHorario(dto.getHrEntrada(),dto.getHrSaida());
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Medico m = u.getMedico();
		m.setHrEntrada(dto.getHrEntrada());
		m.setHrSaida(dto.getHrSaida());
		m.setCrm(dto.getCrm());
		List<Especializacao> especializacoes = this.especializacaoRepository.findByIdIn(dto.getEspecializacoes());
		Set<Especializacao> especializacoesSet = especializacoes.stream().collect(Collectors.toSet());
		m.setEspecializacoes(especializacoesSet);
		this.repository.save(m);
	}

	public Page<MedicoDTO> getAllPaginado(Pageable p) {
		Page<Medico> medicos = this.repository.findAll(p);
		 if(!medicos.isEmpty()) {
		    return new PageImpl<MedicoDTO>(
		    		medicos.getContent().stream().map(m -> new MedicoDTO(m)).collect(Collectors.toList()), p, medicos.getTotalElements());
		}else {
		  	throw new NoElementException("Não existem médicos cadastrados");
		}
	}
	
}
