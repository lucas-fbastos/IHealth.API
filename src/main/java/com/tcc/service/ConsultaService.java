package com.tcc.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcc.DTO.AgendamentoDTO;
import com.tcc.DTO.ConsultaDTO;
import com.tcc.DTO.MedicoDTO;
import com.tcc.DTO.PacienteDTO;
import com.tcc.domain.Clinica;
import com.tcc.domain.Consulta;
import com.tcc.domain.Medico;
import com.tcc.domain.Paciente;
import com.tcc.domain.TipoProcedimento;
import com.tcc.enums.TemporalidadeEnum;
import com.tcc.repository.ConsultaRepository;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.TemporalidadeException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ClinicaService clinicaService;
	
	@Autowired
	private MedicoService medicoService;
		
	@Autowired
	private TipoProcedimentoService tipoProcedimentoService;
	
	@Autowired
	private PacienteService pacienteService;
	
	public List<LocalTime> getHorariosLivres(AgendamentoDTO agendamento) {
		Clinica clinica = this.clinicaService.getDadosClinica();
		TipoProcedimento tp = this.tipoProcedimentoService.getById(agendamento.getIdTipoProcedimento());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = formatter.withLocale( Locale.ROOT );  
		LocalDate date = LocalDate.parse(agendamento.getData(), formatter);
		if(agendamento.getIdMedico()!=null) {
			Medico medico = this.medicoService.getById(agendamento.getIdMedico());
			List<LocalTime> horariosMedico = this.getHorariosGeral(medico.getHrEntrada(), medico.getHrSaida(), tp.getDuracao());
			LocalDateTime entrada = LocalDateTime.of( date, medico.getHrEntrada());
			LocalDateTime saida = LocalDateTime.of(date, medico.getHrSaida());
			List<Consulta> consultaList = this.consultaRepository.getAllBetweenDatesByMedico(entrada, saida,medico.getId());
			List<LocalTime> consultas = this.extraiDatas(consultaList); 
			return this.comparaDatas(consultas,horariosMedico);
		}else {
			List<LocalTime> horariosGeral = this.getHorariosGeral(clinica.getDtAbertura(), clinica.getDtEncerramento(), tp.getDuracao());
			LocalDateTime abertura = LocalDateTime.of( date, clinica.getDtAbertura());
			LocalDateTime encerramento = LocalDateTime.of(date, clinica.getDtEncerramento());
			List<Consulta> consultaList = this.consultaRepository.getAllBetweenDates(abertura, encerramento);
			List<LocalTime> consultas = this.extraiDatas(consultaList); 
			return this.comparaDatas(consultas,horariosGeral);
		}

	}

	public Page<ConsultaDTO> findAll(Pageable p) {
	    Page<Consulta> consultas = this.consultaRepository.findAll(p); 
	    return toPageDTO(p,consultas);
	}
	
	public Page<ConsultaDTO> findAllByMedico(Pageable p, Long idMedico) {
	    Page<Consulta> consultas = this.consultaRepository.findAllByMedico_id(p,idMedico); 
	    return toPageDTO(p,consultas);
	}
	
	public Page<ConsultaDTO> findAllByPaciente(Pageable p, Long idPaciente) {
	    Page<Consulta> consultas = this.consultaRepository.findAllByPaciente_id(p,idPaciente); 
	    return toPageDTO(p,consultas);
	}
	
	private Page<ConsultaDTO> toPageDTO(Pageable p, Page<Consulta> consultas){
		 if(!consultas.isEmpty()) {
		    return new PageImpl<ConsultaDTO>(
		    		consultas.getContent().stream().map(c -> new ConsultaDTO(c)).collect(Collectors.toList()), p, consultas.getTotalElements());
		 }else {
		  	throw new NoElementException("Não existem consultas cadastradas para os parametros informados");
		 }
	}

	private List<LocalTime> extraiDatas(List<Consulta> consultaList) {
		if( consultaList!= null && !consultaList.isEmpty())
			return consultaList.stream().map(c -> c.getDtIncio().toLocalTime()).collect(Collectors.toList());
		else 
			return new ArrayList<LocalTime>();
	}


	private List<LocalTime> comparaDatas(List<LocalTime> consultaList, List<LocalTime> horariosGeral) {
		List<LocalTime> horariosLivres = new ArrayList<>();
		if(consultaList.isEmpty())
			return horariosGeral;
		
		for(LocalTime h : horariosGeral) {
			for(LocalTime c : consultaList) {
				if(!c.toString().equals(h.toString())) 
					horariosLivres.add(h);	
			}
		}
		return horariosLivres;
	}


	private List<LocalTime> getHorariosGeral(LocalTime dtAbertura, LocalTime dtEncerramento, Duration duracaoProcedimento) {
		long tempoAberto = Duration.between(dtAbertura,dtEncerramento ).toMinutes();
		long qtdHorarios = Math.round(tempoAberto / (duracaoProcedimento.getSeconds()/60));
		List<LocalTime> horarios = new ArrayList<>();
		horarios.add(dtAbertura);
		for(int i=0;i<qtdHorarios;i++) {
			dtAbertura = dtAbertura.plus(duracaoProcedimento);
			horarios.add(dtAbertura);
		}
		return horarios;
	}


	public List<ConsultaDTO> getConsultasPorTempo(Integer indiceTemporalidade) {
		List<LocalDateTime> datas = this.getDatasByTemporalidade(TemporalidadeEnum.values()[indiceTemporalidade]);
		List<Consulta> consultas = this.consultaRepository.getAllBetweenDates(datas.get(0), datas.get(1));
		if(consultas!=null && !consultas.isEmpty())
			return consultas.stream().map(c -> new ConsultaDTO(c.getId(),
											new PacienteDTO(c.getPaciente()), new MedicoDTO(c.getMedico()),c.getDtIncio(),c.getDtFim(),
											c.getTipoProcedimento(), c.getObservacao()))
							  .collect(Collectors.toList());
		else 
			throw new NoElementException("Não existem consultas para hoje");
		
	}


	private List<LocalDateTime> getDatasByTemporalidade(TemporalidadeEnum temporalidadeEnum) {
		LocalDateTime inicio = null;
		LocalDateTime fim = null;
		LocalDateTime agora = LocalDateTime.now();
		
		switch(temporalidadeEnum) {
			case DIARIA:
				inicio = LocalDateTime.of(agora.getYear(), agora.getMonth(), agora.getDayOfMonth(), 0,0);
				fim    = LocalDateTime.of(agora.getYear(), agora.getMonth(), agora.getDayOfMonth(), 23,59);
				break;
			case MENSAL:
				inicio = LocalDateTime.of(agora.getYear(), agora.getMonth(), 1, 0,0);
				fim    = LocalDateTime.of(agora.getYear(), agora.getMonth(), agora.withDayOfMonth(agora.toLocalDate().lengthOfMonth()).getDayOfMonth(), 23,59);
				break;
			case SEMANAL:
				inicio = LocalDateTime.of(agora.getYear(), agora.getMonth(), agora.getDayOfMonth(), 0,0);
				int ultimoDia= agora.toLocalDate().lengthOfMonth();
				ultimoDia =  ultimoDia <  (agora.getDayOfMonth()+7) ? ultimoDia : (agora.getDayOfMonth()+7);
				fim    = LocalDateTime.of(agora.getYear(), agora.getMonth(), ultimoDia, 23,59);
				break;
		}
		if(inicio!=null && fim!=null) {
			 List<LocalDateTime> list = new ArrayList<>();
			 list.add(inicio);
			 list.add(fim);
			 return list;
		}else {
			throw new TemporalidadeException("Temporalidade inválida");
		}
	}

	public Consulta getById(Long id) {
		Optional<Consulta> cOp = this.consultaRepository.findById(id);
		if(cOp.isPresent())
			return cOp.get();
		throw new NoElementException("Consulta não encontrada");
	}

	public void confirmaConsulta(Long id) {
		Consulta c = getById(id);
		if(!c.isConfirmada()) {
			c.setConfirmada(Boolean.TRUE);
			this.consultaRepository.save(c);
		}
	}


	public ConsultaDTO save(ConsultaDTO dto) {
		Paciente p = this.pacienteService.getById(dto.getIdPaciente());
		Medico m = this.medicoService.getById(dto.getIdMedico());
		Consulta c = new Consulta();
		c.setDtIncio(dto.getDtInicio());
		TipoProcedimento tp = this.tipoProcedimentoService.getById(dto.getTipoProcedimentoId()); 
		c.setTipoProcedimento(tp);
		c.setDtFim(dto.getDtInicio().plus(tp.getDuracao()));
		c.setObservacao(dto.getObservacao());
		c.setMedico(m);
		c.setPaciente(p);
		c.setConfirmada(Boolean.FALSE);
		try {
			c = this.consultaRepository.save(c);
			return new ConsultaDTO(c);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar consulta");
		}
	}
}
