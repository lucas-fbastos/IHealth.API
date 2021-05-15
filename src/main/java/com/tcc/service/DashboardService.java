package com.tcc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.Alergia;
import com.tcc.domain.Usuario;
import com.tcc.repository.AlergiaRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class DashboardService {

	
	
	@Autowired
	private AlergiaRepository alergiaRepository;
	
	
	@Autowired
	private UsuarioService userService;
	

	public Map<String, Object> getAlergiasPorTipoGeral(){
		Usuario usuario = this.userService.getUserLogado();
		List<Alergia> alergias = this.alergiaRepository.findByDadosMedicos(usuario.getDadosmedicos());
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
	
	
}
