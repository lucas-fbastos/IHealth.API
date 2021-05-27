package com.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.ProntuarioDTO;
import com.tcc.DTO.ProntuarioformDTO;
import com.tcc.domain.Consulta;
import com.tcc.domain.Prontuario;
import com.tcc.repository.ProntuarioRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class ProntuarioService {

	@Autowired
	private ProntuarioRepository repository;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private AlergiaService alergiaService;
	
	@Autowired
	private DoencaCronicaService doencaCronicaService;
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	public ProntuarioDTO inciaAtendimento(Long idConsulta) {
		Consulta consulta = this.consultaService.verificaAtendimento(idConsulta);
		Optional<Prontuario> opProntuario = repository.findByConsulta(consulta);
		if(opProntuario.isPresent())
			return new ProntuarioDTO(opProntuario.get());
		
		return new ProntuarioDTO(cadastraProntuario(consulta));
	}

	public Prontuario cadastraProntuario(Consulta consulta) {
		Prontuario prontuario = new Prontuario();
		prontuario.setConsulta(consulta);
		return repository.save(prontuario);
	}

	public Prontuario save(ProntuarioformDTO prontuario) {
		Prontuario p = getById(prontuario.getId());
		p.setConcordouTermos(prontuario.getConcordaTermos());
		p.setDescProntuario(prontuario.getDesc());
		p.setTemAlergia(prontuario.getTemAlergia());
		p.setDescSumario(prontuario.getSumario());
		p.setTemDoencaCronica(prontuario.getTemDoencaCronica());
		p.setDiagnostico(prontuario.getDiagnostico());
		p = repository.save(p);
		if(prontuario.getAlergias()!=null && !prontuario.getAlergias().isEmpty()) 
			this.alergiaService.addAlergia(prontuario.getAlergias(), p.getConsulta().getPaciente(),p);
		
		if(prontuario.getMedicamentos()!=null && !prontuario.getMedicamentos().isEmpty()) 
			this.medicamentoService.addMedicamentos(prontuario.getMedicamentos(), p.getConsulta().getPaciente(),p);
		
		if(prontuario.getDoencas()!=null && !prontuario.getDoencas().isEmpty()) 
			this.doencaCronicaService.save(prontuario.getDoencas(), p.getConsulta().getPaciente(),p);
		
		return p;
	}
	
	public ProntuarioDTO finalizaAtendimento(ProntuarioformDTO prontuario) {
		Prontuario p = save(prontuario);
		Consulta consulta = this.consultaService.finalizaConsulta(p.getConsulta());
		p.setConsulta(consulta);
		return new ProntuarioDTO(p);
	}
	
	public Prontuario getById(Long id) {
		Optional<Prontuario> p = repository.findById(id);
		if(p.isPresent())
			return p.get();
		throw new NoElementException("Prontuário não encontrado");
	}
}
