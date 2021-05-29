package com.tcc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.AdministradorDashDTO;
import com.tcc.DTO.AuxiliarDashDTO;
import com.tcc.DTO.MedicoDashDTO;
import com.tcc.DTO.PacienteDashDTO;
import com.tcc.DTO.report.TipoQuantidade;
import com.tcc.domain.Alergia;
import com.tcc.domain.Consulta;
import com.tcc.domain.Medico;
import com.tcc.domain.Usuario;
import com.tcc.enums.StatusConsultaEnum;
import com.tcc.enums.TemporalidadeEnum;
import com.tcc.repository.AlergiaRepository;
import com.tcc.repository.ConsultaRepository;
import com.tcc.repository.ProntuarioRepository;
import com.tcc.repository.UserRepository;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.PerfilInvalidoException;

@Service
public class DashboardService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ConsultaService consultService;
	
	@Autowired
	private AlergiaRepository alergiaRepository;
	
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	public Map<String, Object> getAlergiasPorTipoGeral(){
		Usuario usuario = this.userService.getUserLogado();
		List<Alergia> alergias = this.alergiaRepository.findByDadosMedicos(usuario.getPaciente().getDadosmedicos());
		if(alergias != null && !alergias.isEmpty()) 
			return this.separaPorTipoAlergia(alergias);
		else 
			throw new NoElementException("Sem Alergias cadastradas");
		
	}
	

	private Map<String, Object> separaPorTipoAlergia(List<Alergia> alergias) {
		Integer total = alergias.size();
		Map<String,Integer> items = new HashMap<>();
		for(Alergia a : alergias) {
			if(a.getTipoAlergia()!=null) {
				String desctipo = a.getTipoAlergia().getDescTipo();
				Set<String> keys = items.keySet();
				if(!keys.contains(desctipo))
					items.put(desctipo, 1);
				else
					items.put(desctipo, items.get(desctipo)+1);
			}
		}
		Map<String,Object> result = new HashMap<>();
		result.put("total", total);
		result.put("quantidade", items);
		return result;
	}
	
	
	public AdministradorDashDTO getDashAdministrador(Integer idTemporalidade) {
		
		AdministradorDashDTO dto = new AdministradorDashDTO();
		
		List<TipoQuantidade> tpList  = new ArrayList<>();
		if(idTemporalidade < 3) {
			TemporalidadeEnum temporalidade = TemporalidadeEnum.values()[idTemporalidade];
			List<LocalDateTime> dates = consultService.getDatasByTemporalidade(temporalidade);
			tpList = this.consultaRepository.getQuantitativoStatusConsulta(dates.get(0), dates.get(1));
			dto.setMedicoAtendimento(this.consultaRepository.getQuantitativoConsultaMedico(dates.get(0), dates.get(1)));
			dto.setPacienteConsulta(this.consultaRepository.getQuantitativoConsultaPaciente(dates.get(0), dates.get(1)));
			dto.setEspecializacoesMes(this.quantificaEspecialidadesPorConsultas(this.consultaRepository.getAllBetweenDates(dates.get(0), dates.get(1))));
		}else {
			tpList = this.consultaRepository.getQuantitativoStatusConsulta();
			dto.setMedicoAtendimento(this.consultaRepository.getQuantitativoConsultaMedicos());
			dto.setPacienteConsulta(this.consultaRepository.getQuantitativoConsultaPaciente());
			dto.setEspecializacoesMes(this.quantificaEspecialidadesPorConsultas(this.consultaRepository.findAll()));
		}
		dto.setConsultaMensalPorStatus(castIdToDescStatus(tpList));
		dto.setQuantitativoUsuarios(this.quantificaPerfilPorUsuario(this.userRepository.findAll()));
		return dto;
	}
	
	
	private List<TipoQuantidade> quantificaPerfilPorUsuario(List<Usuario> users) {
		List<TipoQuantidade> tpList  = new ArrayList<>();
		users.forEach(u -> {
			u.getPerfis().forEach(
					e -> {
						if(tpList.stream().filter(
								x-> x.getDescricao().compareTo(e.getDesc())==0)
									.collect(Collectors.toList()).size()==0) {
							tpList.add(new TipoQuantidade(e.getDesc(),1l));
						}else {
							tpList.forEach(i -> {
								if(i.getDescricao()==e.getDesc())
									i.setQuantidade(i.getQuantidade()+1);
							});
						}
					});
			
		});
		return tpList;
	}


	private List<TipoQuantidade> castIdToDescStatus(List<TipoQuantidade> tpList) {
		 tpList.forEach(i -> 
		i.setDescricao(
				StatusConsultaEnum.toEnum(
						Integer.parseInt(i.getDescricao()))
				.getDescStatus())
				);
		return tpList;
	}


	public PacienteDashDTO getDashPaciente() {
		PacienteDashDTO dto = new PacienteDashDTO();
		Usuario usuario = this.userService.getUserLogado();
		if(usuario.getPaciente()==null) throw new PerfilInvalidoException("Usuário não é paciente");
		dto.setQuantitativosPorTipoAlergia(this.alergiaRepository.findTotalAlergiasPorTipo(usuario.getPaciente().getDadosmedicos().getId()));
		dto.setQuantitativosPorTipoProcedimento(consultaRepository.getQuantitativoTipoConsulta(usuario.getPaciente().getId()));
		dto.setQuantiativoAlergia((long) usuario.getPaciente().getDadosmedicos().getAlergias().size());
		dto.setQuantiativoDoencaCronica((long) usuario.getPaciente().getDadosmedicos().getDoencasCronicas().size());
		dto.setQuantiativoMedicamentos((long) usuario.getPaciente().getDadosmedicos().getMedicamentos().size());
		dto.setDiagnosticosPorData(this.prontuarioRepository.getDiagnosticosPorPaciente(usuario.getPaciente().getId()));
		List<Consulta> consultas = this.consultaRepository.findAllByPaciente_id(usuario.getPaciente().getId());
		dto.setQuantitativosPorTipoEspecializacao(quantificaEspecialidadesPorConsultas(consultas));
		Long qtd = 0L;
		for(TipoQuantidade tp : dto.getQuantitativosPorTipoProcedimento())
			qtd+=tp.getQuantidade();
		dto.setQuantitativoProcedimento(qtd);
		return dto;
	}

	private List<TipoQuantidade> quantificaEspecialidadesPorConsultas(List<Consulta> consultas) {
		List<TipoQuantidade> tpList  = new ArrayList<>();
		consultas.forEach(c -> {
			Medico m = c.getMedico();
			m.getEspecializacoes().forEach(
					e -> {
						if(tpList.stream().filter(
								x-> x.getDescricao().compareTo(e.getDescEspecializacao())==0)
									.collect(Collectors.toList()).size()==0) {
							tpList.add(new TipoQuantidade(e.getDescEspecializacao(),1l));
						}else {
							tpList.forEach(i -> {
								if(i.getDescricao()==e.getDescEspecializacao())
									i.setQuantidade(i.getQuantidade()+1);
							});
						}
					});
			
		});
		return tpList;
	}


	public AuxiliarDashDTO getDashAuxiliar(Integer idTemporalidade) {
		AuxiliarDashDTO dto = new AuxiliarDashDTO();
		List<TipoQuantidade> tpList  = new ArrayList<>();
		if(idTemporalidade < 3) {
			TemporalidadeEnum temporalidade = TemporalidadeEnum.values()[idTemporalidade];
			List<LocalDateTime> dates = consultService.getDatasByTemporalidade(temporalidade);
			tpList = this.consultaRepository.getQuantitativoStatusConsulta(dates.get(0), dates.get(1));
		}else {
			tpList = this.consultaRepository.getQuantitativoStatusConsulta();
		}
		dto.setConsultaMensalPorStatus(castIdToDescStatus(tpList));
		dto.setQuantitativoUsuarios(this.quantificaPerfilPorUsuario(this.userRepository.findAll()));
		return dto;
	}
	
	

	public MedicoDashDTO getDashMedico(Integer idTemporalidade) {
		MedicoDashDTO dto = new MedicoDashDTO();
		Usuario usuario = this.userService.getUserLogado();
		if(usuario.getMedico()==null) throw new PerfilInvalidoException("Usuário não é médico");
		List<TipoQuantidade> tpList  = new ArrayList<>();
		if(idTemporalidade < 3) {
			TemporalidadeEnum temporalidade = TemporalidadeEnum.values()[idTemporalidade];
			List<LocalDateTime> dates = consultService.getDatasByTemporalidade(temporalidade);
			tpList = this.consultaRepository.getQuantitativoStatusConsultaMedico(dates.get(0), dates.get(1),usuario.getMedico().getId());
			dto.setPacienteConsulta(this.consultaRepository.getQuantitativoConsultaPacienteMedico(dates.get(0),dates.get(1),usuario.getMedico().getId()));
		}else {
			dto.setPacienteConsulta(this.consultaRepository.getQuantitativoConsultaPacienteMedico(usuario.getMedico().getId()));
			tpList = this.consultaRepository.getQuantitativoStatusConsultaMedico(usuario.getMedico().getId());
		}
		dto.setConsultaMensalPorStatus(castIdToDescStatus(tpList));
		return dto;
	}
	
	
	
}
