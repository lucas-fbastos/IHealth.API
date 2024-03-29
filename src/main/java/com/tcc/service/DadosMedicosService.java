package com.tcc.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.DTO.DadosMedicosUserDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Paciente;
import com.tcc.domain.TipoSanguineo;
import com.tcc.domain.Usuario;
import com.tcc.enums.IMCEnum;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.TipoSanguineoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.PerfilInvalidoException;


@Service
public class DadosMedicosService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	@Autowired
	private UsuarioService userService;
		
	public DadosMedicos update(DadosMedicosDTO dto) {
		try {
			Usuario user = this.userService.getUserLogado();
			
			Paciente p = user.getPaciente();
			if(p==null) throw new PerfilInvalidoException("Este não é um perfil válido de paciente");
			DadosMedicos dados = this.dadosMedicosRepository.findByPaciente(p).orElseThrow();
			dados.setDtAtualizacao(LocalDateTime.now());
			if(dto.getTipoSanguineo()!=null) {
				TipoSanguineo tipoSanguineo =  this.tipoSanguineoRepository.findById(dto.getTipoSanguineo()).orElseThrow();
				dados.setTipoSanguineo(tipoSanguineo);					
			}
			dados.setAltura(dto.getAltura());
			dados.setPeso(dto.getPeso());
			this.calculaImc(dados);
			this.dadosMedicosRepository.save(dados);
			Usuario u = p.getUsuario();
			if(dados.getPeso() != null && dados.getAltura() != null && dados.getTipoSanguineo() != null) {
				u.addPerfil(PerfilEnum.ATIVO);
				u.getPerfis().remove(PerfilEnum.PENDENTE);
			}else{
				u.addPerfil(PerfilEnum.PENDENTE);
				u.getPerfis().remove(PerfilEnum.ATIVO);
			}
			this.userRepository.save(u);
			return dados;
		}catch(NoSuchElementException e) {
			throw new NoElementException("informação não encontrada");
		}
	}
	

	public DadosMedicosUserDTO getDadosMedicos() {
		try {
			Usuario user = this.userService.getUserLogado();
			DadosMedicos dm = this.dadosMedicosRepository.findByPaciente(user.getPaciente()).orElseThrow();
			DadosMedicosUserDTO dto = new DadosMedicosUserDTO();
			dto.setAlergias(dm.getAlergias());
			dto.setAltura(dm.getAltura());
			dto.setDescImc(dm.getDescImc());
			dto.setDoencasCronicas(dm.getDoencasCronicas());
			dto.setDtAtualizacao(dm.getDtAtualizacao());
			dto.setId(dm.getId());
			Integer idade =  LocalDate.now().getYear() - user.getDtNascimento().getYear();
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
