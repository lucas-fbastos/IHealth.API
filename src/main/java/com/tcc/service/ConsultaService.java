package com.tcc.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import com.tcc.DTO.ProntuarioDTO;
import com.tcc.DTO.filter.ConsultaFilter;
import com.tcc.domain.Clinica;
import com.tcc.domain.Consulta;
import com.tcc.domain.Medico;
import com.tcc.domain.Paciente;
import com.tcc.domain.TipoProcedimento;
import com.tcc.enums.StatusConsultaEnum;
import com.tcc.enums.TemporalidadeEnum;
import com.tcc.repository.ConsultaRepository;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.ObjetoInvalidoException;
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
		if(consultaList.isEmpty())
			return horariosGeral;
		
		return horariosGeral.stream().filter(h -> !consultaList.contains(h)).collect(Collectors.toList());	
		
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
											new PacienteDTO(c.getPaciente()), new MedicoDTO(c.getMedico()),c.getDtIncio(),
											c.getDtFim(), c.getTipoProcedimento(), c.getObservacao(), 
											c.getStatusConsulta(), new ProntuarioDTO(c.getProntuario())))
							  .collect(Collectors.toList());
		else 
			throw new NoElementException("Não existem consultas para hoje");
	}


	public List<LocalDateTime> getDatasByTemporalidade(TemporalidadeEnum temporalidadeEnum) {
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
			c.setStatusConsulta(StatusConsultaEnum.CONFIRMADA.getId());
			this.consultaRepository.save(c);
		}
	}


	public ConsultaDTO save(ConsultaDTO dto) {
		Paciente p = this.pacienteService.getById(dto.getIdPaciente());
		Medico m = this.medicoService.getById(dto.getIdMedico());
		Consulta c = new Consulta();
		c.setDtIncio(dto.getDtInicio());
		TipoProcedimento tp = this.tipoProcedimentoService.getById(dto.getIdTipoProcedimento()); 
		c.setTipoProcedimento(tp);
		c.setDtIncio(dto.getDtInicio());
		c.setDtFim(dto.getDtInicio().plus(tp.getDuracao()));
		c.setObservacao(dto.getObservacao());
		c.setMedico(m);
		c.setPaciente(p);
		c.setConfirmada(false);
		c.setStatusConsulta(StatusConsultaEnum.PENDENTE.getId());
		try {
			c = this.consultaRepository.save(c);
			return new ConsultaDTO(c);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar consulta");
		}
	}

	public Page<ConsultaDTO> findAllByMedico(Pageable p, Long idMedico, Integer indiceTemporalidade) {
		List<LocalDateTime> list = getDatasByTemporalidade(TemporalidadeEnum.values()[indiceTemporalidade]);
		Page<Consulta> consultas = this.consultaRepository.findAllByMedico_idAndDtInicioGreaterThanEqualAndDtFimLessThanEqual(
					p,idMedico,list.get(0),	list.get(1)); 
		return toPageDTO(p,consultas);
	}

	public void cancelaConsulta(Long id) {
		Consulta consulta = getById(id);
		consulta.setConfirmada(false);
		consulta.setStatusConsulta(StatusConsultaEnum.CANCELADA.getId());
		this.consultaRepository.save(consulta);
	}
	
	public Page<ConsultaDTO> filter(Pageable page, ConsultaFilter filter ) {
		Page<Consulta> consultas = this.consultaRepository.findByPacienteAndDates(filter, page);
		consultas.getContent().forEach(c->{if(c.getProntuario()!=null) c.getProntuario().setConsulta(null);});
		return toPageDTO(page,consultas);
	}

	public Consulta verificaAtendimento(Long idConsulta){
		Consulta consulta = getById(idConsulta);
		switch(consulta.getStatusConsulta()) {
			case PENDENTE:
				throw new ObjetoInvalidoException("Consulta precisa ser confirmada");
			case CANCELADA:
				throw new ObjetoInvalidoException("a consulta se encontra cancelada");
			case EM_ANDAMENTO:
				return consulta;
			case CONFIRMADA:
				return iniciarConsulta(consulta);
			default:
				throw new ObjetoInvalidoException("Consulta não possui um status válido");
		}
	}

	private Consulta iniciarConsulta(Consulta consulta) {
		consulta.setStatusConsulta(StatusConsultaEnum.EM_ANDAMENTO.getId());
		return consultaRepository.save(consulta);
	}

	public Consulta finalizaConsulta(Consulta consulta) {
		consulta.setStatusConsulta(StatusConsultaEnum.FINALIZADA.getId());
		return consultaRepository.save(consulta);
	}

}
