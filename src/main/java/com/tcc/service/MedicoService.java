package com.tcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tcc.DTO.MedicoDTO;
import com.tcc.DTO.MedicoFormDTO;
import com.tcc.domain.Especializacao;
import com.tcc.domain.Medico;
import com.tcc.domain.Usuario;
import com.tcc.repository.EspecializacaoRepository;
import com.tcc.repository.MedicoRepository;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;
	
	@Autowired
	private EspecializacaoRepository especializacaoRepository;
	
	
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
	
	public MedicoDTO getById(Long id){
		Medico m = this.repository.findById(id).orElseThrow();
		return new MedicoDTO(m.getId(),m.getEspecializacoes(),m.getCrm(),m.getUsuario());
	}

	public Medico save(MedicoFormDTO dto) {
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Medico m = new Medico();
		m.setCrm(dto.getCrm());
		List<Especializacao> especializacoes = this.especializacaoRepository.findByIdIn(dto.getEspecializacoes());
		Set<Especializacao> especializacoesSet = especializacoes.stream().collect(Collectors.toSet());
		m.setEspecializacoes(especializacoesSet);
		u = this.usuarioService.removePendente(u);
		m.setUsuario(u);
		try {
			return this.repository.save(m);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar Médico");
		}
	}

	public void update(MedicoFormDTO dto) {
		Usuario u = this.usuarioService.getById(dto.getIdUser());
		Medico m = u.getMedico();
		m.setCrm(dto.getCrm());
		List<Especializacao> especializacoes = this.especializacaoRepository.findByIdIn(dto.getEspecializacoes());
		Set<Especializacao> especializacoesSet = especializacoes.stream().collect(Collectors.toSet());
		m.setEspecializacoes(especializacoesSet);
		this.repository.save(m);
	}
	
}
