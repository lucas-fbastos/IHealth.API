package com.tcc.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcc.DTO.MedicamentoDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Medicamento;
import com.tcc.domain.Paciente;
import com.tcc.domain.Prontuario;
import com.tcc.domain.Usuario;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.MedicamentoRepository;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.PerfilInvalidoException;

@Service
public class MedicamentoService {
		
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Medicamento> addMedicamentos(List<MedicamentoDTO> medicamentos, Paciente p, Prontuario prontuario) {
		DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
		List<Medicamento> list = new ArrayList<>();
		for(MedicamentoDTO dto : medicamentos) {
			Medicamento m = new Medicamento();
			m.setId(null);
			m.setDuracaoTratamento(dto.getDuracaoTratamento());
			m.setDadosMedicos(dados);
			m.setDosagem(dto.getDosagem());
			m.setDescMedicamento(dto.getDesc());
			m.setDtRegistro(new Date(System.currentTimeMillis()));
			m.setProntuario(prontuario);
			list.add(m);
		}
		this.medicamentoRepository.saveAll(list);
		return list;
	}
	
	public Medicamento addMedicamentos(MedicamentoDTO dto, Long idPaciente) {
		Paciente paciente = pacienteService.getById(idPaciente);
		DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(paciente).orElseThrow();
		Medicamento m = new Medicamento();
		m.setId(null);
		m.setDadosMedicos(dados);
		m.setDescMedicamento(dto.getDesc());
		m.setDtRegistro(new Date(System.currentTimeMillis()));
		return this.medicamentoRepository.save(m);
	}

	public void deleteMedicamento(Long idMed, Long idPaciente) {
		Paciente p = pacienteService.getById(idPaciente);
		DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
		List<Medicamento> list = this.medicamentoRepository.findByDadosMedicos(dados);
		if(list!=null && !list.isEmpty()) {
			list = list.stream().filter(m -> m.getId() == idMed).collect(Collectors.toList());
			this.medicamentoRepository.delete(list.get(0));
		}else {
			throw new NoElementException("O medicamento não foi encontrado");
		}
	}
	
	
	public List<Medicamento> update(List<MedicamentoDTO> medicamentos, Long idPaciente ){
		Paciente p = pacienteService.getById(idPaciente);
		DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
		List<Medicamento> list = new ArrayList<>();
		for(MedicamentoDTO dto : medicamentos) {
			Medicamento m = this.medicamentoRepository.findById(dto.getId()).orElseThrow();
			m.setDadosMedicos(dados);
			m.setDescMedicamento(dto.getDesc());
			list.add(m);
		}
		this.medicamentoRepository.saveAll(list);
		return list;
	}

	public Page<Medicamento> getByPacientePaged(Pageable page) {
		Usuario u = this.usuarioService.getUserLogado();
		Paciente p = u.getPaciente();
		if(p==null) throw new PerfilInvalidoException("Apenas perfil de paciente pode solicitar tal consulta");
		return this.medicamentoRepository.findByDadosMedicos(p.getDadosmedicos(), page);
	}
}
