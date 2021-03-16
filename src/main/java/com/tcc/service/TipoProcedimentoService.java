package com.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.TipoProcedimento;
import com.tcc.repository.TipoProcedimentoRepository;

@Service
public class TipoProcedimentoService {

	@Autowired
	private TipoProcedimentoRepository repository;
	
	public TipoProcedimento getById(Long id) {
		return this.repository.findById(id).orElseThrow();
	}
}
