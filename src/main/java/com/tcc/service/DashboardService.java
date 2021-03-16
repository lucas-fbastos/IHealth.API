package com.tcc.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.Alergia;
import com.tcc.domain.DoencaCronica;
import com.tcc.domain.Medicamento;
import com.tcc.domain.ProcedimentoMedico;
import com.tcc.domain.Usuario;
import com.tcc.repository.AlergiaRepository;
import com.tcc.repository.DoencaCronicaRepository;
import com.tcc.repository.MedicamentoRepository;
import com.tcc.repository.ProcedimentoMedicoRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class DashboardService {

	@Autowired
	private ProcedimentoMedicoRepository procedimentoMedicoRepository;
	
	@Autowired
	private AlergiaRepository alergiaRepository;
	
	@Autowired
	private DoencaCronicaRepository doencaCronicaRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private UsuarioService userService;
	
	public Map<String, Object> getProcedimentosPorTipoUltimoMes(){
		LocalDate dtRegistroFim  = LocalDate.now();
		LocalDate dtRegistroInicio     = dtRegistroFim.minus(1, ChronoUnit.MONTHS); 
		Usuario usuario = this.userService.getUserLogado();
		List<ProcedimentoMedico> procedimentos = this.procedimentoMedicoRepository.getAllBetweenDates(dtRegistroInicio, dtRegistroFim, usuario);
		if(procedimentos != null && !procedimentos.isEmpty()) 
			return this.separaPorTipoProcedimento(procedimentos);
		else 
			throw new NoElementException("Sem procedimentos no período");
		
	}
	
	public Map<String, Integer> getQuantitativos(){
		Usuario usuario = this.userService.getUserLogado();
		List<ProcedimentoMedico> procedimentos = this.procedimentoMedicoRepository.findByUser(usuario);
		List<Alergia> alergias = this.alergiaRepository.findByDadosMedicos(usuario.getDadosmedicos());
		List<DoencaCronica> doencas = this.doencaCronicaRepository.findByDadosMedicos(usuario.getDadosmedicos());
		List<Medicamento> medicamentos = this.medicamentoRepository.findByDadosMedicos(usuario.getDadosmedicos());
		Map<String, Integer> quantitativos = new HashMap<>();
		
		if(procedimentos != null ) 
			quantitativos.put("procedimentos", procedimentos.size());
		if(alergias != null ) 
			quantitativos.put("alergias", alergias.size());
		if(doencas != null ) 
			quantitativos.put("doencas", doencas.size());
		if(medicamentos != null ) 
			quantitativos.put("medicamentos", medicamentos.size());
		
		if(quantitativos.isEmpty())
			throw new NoElementException("Sem dados no período");
		
		return quantitativos;
		
	}
	
	public Map<String, Object> getProcedimentosPorTipoGeral(){
		Usuario usuario = this.userService.getUserLogado();
		List<ProcedimentoMedico> procedimentos = this.procedimentoMedicoRepository.findByUser(usuario);
		if(procedimentos != null && !procedimentos.isEmpty()) 
			return this.separaPorTipoProcedimento(procedimentos);
		else 
			throw new NoElementException("Sem procedimentos no período");
		
	}
	
	public Map<String, Object> getAlergiasPorTipoGeral(){
		Usuario usuario = this.userService.getUserLogado();
		List<Alergia> alergias = this.alergiaRepository.findByDadosMedicos(usuario.getDadosmedicos());
		if(alergias != null && !alergias.isEmpty()) 
			return this.separaPorTipoAlergia(alergias);
		else 
			throw new NoElementException("Sem Alergias cadastradas");
		
	}
	
	private Map<String, Object> separaPorTipoProcedimento(List<ProcedimentoMedico> procedimentos) {
		Integer total = procedimentos.size();
		Map<String,Integer> items = new HashMap<>();
		for(ProcedimentoMedico p : procedimentos) {
			if(p.getTipoProcedimento()!=null) {
				String desctipo = p.getTipoProcedimento().getDescTipoProcedimento();
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
	
	
}
