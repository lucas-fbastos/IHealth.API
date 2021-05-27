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
import com.tcc.domain.Paciente;
import com.tcc.domain.Prontuario;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.DoencaCronicaRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class DoencaCronicaService {

	@Autowired
	private DoencaCronicaRepository doencaCronicaRepository;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	public List<DoencaCronica> save(List<DoencaCronicaDTO> listDTO, Paciente p, Prontuario prontuario) {
		DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
		List<DoencaCronica> list = new ArrayList<>();
		try{
			for(DoencaCronicaDTO dto : listDTO) {
				DoencaCronica doenca = new DoencaCronica();
				doenca.setId(null);
				doenca.setDadosMedicos(dados);
				doenca.setDescDoenca(dto.getDescDoenca());
				doenca.setProntuario(prontuario);
				list.add(doenca);
			}
		return this.doencaCronicaRepository.saveAll(list);
		}catch(NoSuchElementException e) {
			throw new NoElementException("Informação não encontrada");
		}
	}

	public List<DoencaCronica> updateDoenca(List<DoencaCronicaDTO> doencas, Long idPaciente) {
		try {		
			Paciente p = pacienteService.getById(idPaciente);
			DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
			List<DoencaCronica> list = new ArrayList<>();
			for(DoencaCronicaDTO dto : doencas) {
				DoencaCronica doenca = this.doencaCronicaRepository.findById(dto.getId()).orElseThrow();
				doenca.setDadosMedicos(dados);
				doenca.setDescDoenca(dto.getDescDoenca());
				list.add(doenca);
			}
			this.doencaCronicaRepository.saveAll(list);
			return list;
		}catch(NoSuchElementException e) {
			throw new NoElementException("Doença não encontrada");
		}
	}

	public void deleteDoenca(Long idDoenca, Long idPaciente) {
		Paciente p = pacienteService.getById(idPaciente);
		DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
		List<DoencaCronica> list = this.doencaCronicaRepository.findByDadosMedicos(dados);
		if(list!=null && !list.isEmpty()) {
			list = list.stream().filter(m -> m.getId() == idDoenca).collect(Collectors.toList());
			if(list.isEmpty())
				throw new NoElementException("Não foram encontradas doencas para atualizar");	
			this.doencaCronicaRepository.delete(list.get(0));
		}else {
			throw new NoElementException("Não foram encontradas doencas para atualizar");
		}	
	}
	
}
