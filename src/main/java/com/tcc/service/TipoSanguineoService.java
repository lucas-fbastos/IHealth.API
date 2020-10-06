package com.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.TipoSanguineo;
import com.tcc.repository.TipoSanguineoRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class TipoSanguineoService {

	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	public List<TipoSanguineo> getAll(){
		try {
			return this.tipoSanguineoRepository.findAll();
		}catch(NoSuchElementException e) {
			throw new NoElementException("Tipos Sangúineos não encontrados");
		}
		
	}
}
