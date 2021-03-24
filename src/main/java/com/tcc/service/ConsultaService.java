package com.tcc.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.AgendamentoDTO;
import com.tcc.DTO.ConsultaDTO;
import com.tcc.DTO.MedicoDTO;
import com.tcc.DTO.PacienteDTO;
import com.tcc.domain.Clinica;
import com.tcc.domain.Consulta;
import com.tcc.domain.TipoProcedimento;
import com.tcc.enums.TemporalidadeEnum;
import com.tcc.repository.ConsultaRepository;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.TemporalidadeException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ClinicaService clinicaService;
	
	@Autowired
	private TipoProcedimentoService tipoProcedimentoService;
	
	
	public List<LocalTime> getHorariosLivres(AgendamentoDTO agendamento) {
		
		Clinica clinica = this.clinicaService.getDadosClinica();
		TipoProcedimento tp = this.tipoProcedimentoService.getById(agendamento.getIdTipoProcedimento());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = formatter.withLocale( Locale.ROOT );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		LocalDate date = LocalDate.parse(agendamento.getData(), formatter);
		List<LocalTime> horariosGeral = this.getHorariosGeral(clinica.getDtAbertura(), clinica.getDtEncerramento(), tp.getDuracao());
		LocalDateTime abertura = LocalDateTime.of( date, clinica.getDtAbertura());
		LocalDateTime encerramento = LocalDateTime.of(date, clinica.getDtEncerramento());
		
		List<Consulta> consultaList = this.consultaRepository.getAllBetweenDates(abertura, encerramento);
		List<LocalTime> consultas = this.extraiDatas(consultaList); 

		return this.comparaDatas(consultas,horariosGeral);

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
											c.getTipoProcedimento()))
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
}
