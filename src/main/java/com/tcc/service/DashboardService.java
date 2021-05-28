package com.tcc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.PacienteDashDTO;
import com.tcc.DTO.report.TipoQuantidade;
import com.tcc.domain.Alergia;
import com.tcc.domain.Consulta;
import com.tcc.domain.Medico;
import com.tcc.domain.Usuario;
import com.tcc.repository.AlergiaRepository;
import com.tcc.repository.ConsultaRepository;
import com.tcc.repository.ProntuarioRepository;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.PerfilInvalidoException;

@Service
public class DashboardService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private AlergiaRepository alergiaRepository;
	
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	
	@Autowired
	private UsuarioService userService;
	

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
	
}
