package com.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.Especializacao;
import com.tcc.repository.EspecializacaoRepository;
import com.tcc.service.exceptions.NoElementException;

@Service
public class EspecializacaoService {

	@Autowired
	private EspecializacaoRepository repository;
	
	public List<Especializacao> getAll() {
		List<Especializacao> list = this.repository.findAll();
		if(list!=null && !list.isEmpty())
			return list;
		else
			throw new NoElementException("Não existem especializações cadastradas");
	}
}
