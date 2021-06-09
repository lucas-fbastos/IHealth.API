package com.tcc.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tcc.DTO.ClinicaDTO;
import com.tcc.domain.Clinica;
import com.tcc.domain.Endereco;
import com.tcc.repository.ClinicaRepository;
import com.tcc.repository.EnderecoRepository;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;

@Service
public class ClinicaService {

	@Autowired
	private ClinicaRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Clinica getDadosClinica() {
		Optional<Clinica> clinicas = this.repository.findById(1l);
		if(clinicas.isPresent()) {
				Clinica c = clinicas.get();
				Optional<Endereco> endereco = this.enderecoRepository.findByClinicaEndereco(c);
				if(endereco.isPresent())
					c.setEndereco(endereco.get());
				return c;
		}
		else 
			throw new NoElementException("Os dados da clínica não foram cadastrados");
	}
	
	public void saveClinica(ClinicaDTO dto) {
		Clinica c = new Clinica();
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime abertura = LocalTime.parse(dto.getDtAbertura(), parser);
		LocalTime encerramento = LocalTime.parse(dto.getDtEncerramento(), parser);
		c.setDtAbertura(abertura);
		c.setDtEncerramento(encerramento);
		c.setNome(dto.getNome());
		c.setDescMissao(dto.getMissao());
		c.setDescValores(dto.getValores());
		c.setDescVisao(dto.getValores());
		try {
			this.repository.save(c);
			if(dto.getEndereco()!=null) {
				Endereco e = new Endereco(dto.getEndereco());
				e.setClinicaEndereco(c);
				this.enderecoRepository.save(e);
			}
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar Clinica");
		}
	}

	public Clinica update(ClinicaDTO dto, Long id) {
		Clinica c = this.getById(id);
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime abertura = LocalTime.parse(dto.getDtAbertura(), parser);
		LocalTime encerramento = LocalTime.parse(dto.getDtEncerramento(), parser);
		c.setDtAbertura(abertura);
		c.setDtEncerramento(encerramento);
		c.setNome(dto.getNome());
		c.setDescMissao(dto.getMissao());
		c.setDescValores(dto.getValores());
		c.setDescVisao(dto.getVisao());
		Endereco endereco = c.getEndereco();
		if(dto.getEndereco()!=null) {	
			if(c.getEndereco()!= null && dto.getEndereco().getId() == null)
				throw new DataIntegrityException("Violação na atualização de endereço");
		}
		try {
			c = this.repository.save(c);
			if(c.getEndereco()==null && endereco !=null)
				this.enderecoRepository.delete(endereco);
			
			if(dto.getEndereco()!=null) {
				Endereco e = new Endereco(dto.getEndereco());
				e.setClinicaEndereco(c);
				this.enderecoRepository.save(e);
			}
			return c;	
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao salvar Clinica");
		}
	}
	
	public Clinica getById(Long id) {
		try {
			return this.repository.findById(id).orElseThrow();			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Clinica não encontrada");
		}
	}
}
