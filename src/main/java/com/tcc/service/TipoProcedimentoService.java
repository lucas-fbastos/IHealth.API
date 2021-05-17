package com.tcc.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.TipoProcedimento;
import com.tcc.repository.TipoProcedimentoRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class TipoProcedimentoService {

	@Autowired
	private TipoProcedimentoRepository repository;
	
	public TipoProcedimento getById(Long id) {
		try {
			return this.repository.findById(id).orElseThrow();			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Tipo de procedimento não encontrado");
		}
	}
	
	public List<TipoProcedimento> getAll(){
		List<TipoProcedimento> tipos = this.repository.findAll();
		if(tipos!=null && !tipos.isEmpty())
			return tipos;
		throw new NoElementException("Não existem tipos de procesimento cadastrados");
	}
}
