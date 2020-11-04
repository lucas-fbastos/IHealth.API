package com.tcc.service;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.DTO.DadosMedicosUserDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.TipoSanguineo;
import com.tcc.domain.User;
import com.tcc.enums.IMCEnum;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.TipoSanguineoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.service.exceptions.NoElementException;


@Service
public class DadosMedicosService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	@Autowired
	private UserService userService;
	
	public DadosMedicos update(DadosMedicosDTO dto) {
		try {
			User user = this.userService.getUserLogado();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(user).orElseThrow();
			dados.setDtAtualizacao(new Date());
			if(dto.getTipoSanguineo()!=null) {
				TipoSanguineo tipoSanguineo =  this.tipoSanguineoRepository.findById(dto.getTipoSanguineo()).orElseThrow();
				dados.setTipoSanguineo(tipoSanguineo);					
			}
			dados.setAltura(dto.getAltura());
			dados.setPeso(dto.getPeso());
			dados.setProfissionalSaude(null);
			this.calculaImc(dados);
			this.dadosMedicosRepository.save(dados);
			if(dados.getPeso() != null && dados.getAltura() != null && dados.getTipoSanguineo() != null) {
				user.addPerfil(PerfilEnum.ATIVO);
				user.getPerfis().remove(PerfilEnum.PENDENTE);
			}else{
				user.addPerfil(PerfilEnum.PENDENTE);
				user.getPerfis().remove(PerfilEnum.ATIVO);
			}
			this.userRepository.save(user);
			return dados;
		}catch(NoSuchElementException e) {
			throw new NoElementException("informação não encontrada");
		}
	}
	
	public DadosMedicos update(DadosMedicosDTO dto, User paciente) {
		try {
			User user = this.userService.getUserLogado();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(paciente).orElseThrow();
			dados.setDtAtualizacao(new Date());
			if(dto.getTipoSanguineo()!=null) {
				TipoSanguineo tipoSanguineo =  this.tipoSanguineoRepository.findById(dto.getTipoSanguineo()).orElseThrow();
				dados.setTipoSanguineo(tipoSanguineo);					
			}
			dados.setAltura(dto.getAltura());
			dados.setPeso(dto.getPeso());
			dados.setProfissionalSaude(user);
			this.calculaImc(dados);
			this.dadosMedicosRepository.save(dados);
			if(dados.getPeso() != null && dados.getAltura() != null && dados.getTipoSanguineo() != null) {
				paciente.addPerfil(PerfilEnum.ATIVO);
				paciente.getPerfis().remove(PerfilEnum.PENDENTE);
			}else{
				paciente.addPerfil(PerfilEnum.PENDENTE);
				paciente.getPerfis().remove(PerfilEnum.ATIVO);
			}
			this.userRepository.save(paciente);
			return dados;
		}catch(NoSuchElementException e) {
			throw new NoElementException("informação não encontrada");
		}
	}
	

	public DadosMedicosUserDTO getDadosMedicos() {
		
		try {
			User user = this.userService.getUserLogado();
			DadosMedicos dm = this.dadosMedicosRepository.findByUser(user).orElseThrow();
			DadosMedicosUserDTO dto = new DadosMedicosUserDTO();
			dto.setAlergias(dm.getAlergias());
			dto.setAltura(dm.getAltura());
			dto.setDescImc(dm.getDescImc());
			dto.setDoencasCronicas(dm.getDoencasCronicas());
			dto.setDtAtualizacao(dm.getDtAtualizacao());
			if(dm.getProfissionalSaude()!=null) {
				dto.setNomeProfissionalSaude(dm.getProfissionalSaude().getNome());					
			}
			dto.setId(dm.getId());
			LocalDate now = LocalDate.now();
			LocalDate dtNascimentoUser =  Instant.ofEpochMilli(user.getDtNascimento().getTime())
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
			Integer idade =  now.getYear() - dtNascimentoUser.getYear();
			dto.setIdade(idade);
			dto.setMedicamentos(dm.getMedicamentos());
			dto.setPeso(dm.getPeso());
			dto.setTipoSanguineo(dm.getTipoSanguineo());
			dto.setVlImc(dm.getVlImc());
			return dto;
		}catch(NoSuchElementException e) {
			throw new NoElementException("Usuário não encontrado");
		}
	}
	
	public void calculaImc(DadosMedicos dados) {
		if(dados.getAltura() > 0 && dados.getPeso() > 0) {
			Double imc = dados.getPeso()/(dados.getAltura() * dados.getAltura());		
			DecimalFormat formato = new DecimalFormat("#.##");      
			imc = Double.valueOf(formato.format(imc).replace(',','.'));
			IMCEnum descImc = IMCEnum.getImcDesc(imc);
			dados.setDescImc(descImc.getDescricao());
			dados.setVlImc(imc);
		}
	}
	
}
