package com.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.domain.Especializacao;
import com.tcc.repository.EspecializacaoRepository;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.ObjetoInvalidoException;

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
	
	public Especializacao save(Especializacao especializacao) {
		Optional<Especializacao> especializacaoSalva = this.repository.findByDescEspecializacao(especializacao.getDescEspecializacao());
		if(especializacaoSalva.isPresent())
			throw new ObjetoInvalidoException("Especialidade já existe");
		else
			return this.repository.save(especializacao);
	}
	
	public Especializacao update(Especializacao especializacao, Long id) {
		Especializacao e = getById(id);
		e.setDescEspecializacao(especializacao.getDescEspecializacao());
		return this.repository.save(e);
			
	}

	public void delete(Long id) {
		Especializacao e = getById(id);
		this.repository.delete(e);
	}
	
	public Especializacao getById(Long id) {
		Optional<Especializacao> especializacaoSalva = this.repository.findById(id);
		if(especializacaoSalva.isEmpty())
			throw new NoElementException("Especializacção não encontrado");
		return especializacaoSalva.get();
	}
}
