package com.tcc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.ProcedimentoMedicoDTO;
import com.tcc.DTO.ProcedimentoMedicoFormDTO;
import com.tcc.domain.ProcedimentoMedico;
import com.tcc.domain.TipoProcedimento;
import com.tcc.domain.Usuario;
import com.tcc.repository.ProcedimentoMedicoRepository;
import com.tcc.repository.TipoProcedimentoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;

@Service
public class ProcedimentoMedicoService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private ProcedimentoMedicoRepository procedimentoMedicoRepository;
	 
	@Autowired 
	private TipoProcedimentoRepository tipoProcedimentoRepository;

	public List<ProcedimentoMedicoDTO> getAllByUser(){
		
		UserSecurity logado = UsuarioService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			Usuario usuario = this.userRepository.findById(id).orElseThrow();
			List<ProcedimentoMedico> procedimentos = this.procedimentoMedicoRepository.findByUserOrderByDtProcedimentoDesc(usuario);
			List<ProcedimentoMedicoDTO> dtos = new ArrayList<>();
			if(procedimentos != null && !procedimentos.isEmpty()) {
				for(ProcedimentoMedico procedimento : procedimentos) {
					
					ProcedimentoMedicoDTO dto  = new ProcedimentoMedicoDTO(procedimento.getId(), procedimento.getDescricao(),
														procedimento.getDtRegistro(), 
														procedimento.getDtProcedimento(), procedimento.getUser().getNome(), 
														procedimento.getTipoProcedimento().getDescTipoProcedimento());
					dtos.add(dto);
				}
				return dtos;
			}else {
				throw new NoElementException("Não existem dados médicos para este usuário!");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
	
	public List<TipoProcedimento> getTiposProcedimentosMedicos(){
		List<TipoProcedimento> list= this.tipoProcedimentoRepository.findAll();
		if(list!= null && !list.isEmpty())
			return list;
		else
			throw new NoElementException("Não foram cadastrados tipos de procedimento");
	}
	
	public void save(ProcedimentoMedicoFormDTO dto) {
		UserSecurity logado = UsuarioService.authenticated();
		if(logado!=null) {
			Long id = logado.getId();
			Usuario usuario = this.userRepository.findById(id).orElseThrow();
			ProcedimentoMedico pm = new ProcedimentoMedico();
			TipoProcedimento tp = this.tipoProcedimentoRepository.findById(dto.getIdTipoProcedimento()).orElseThrow();
			pm.setDescricao(dto.getDescricao());
			pm.setDtRegistro(LocalDate.now());
			pm.setDtProcedimento(dto.getDtProcedimento());
			pm.setTipoProcedimento(tp);
			pm.setUser(usuario);
			this.procedimentoMedicoRepository.save(pm);
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
	
	public void save(ProcedimentoMedicoFormDTO dto, Usuario paciente) {
		ProcedimentoMedico pm = new ProcedimentoMedico();
		TipoProcedimento tp = this.tipoProcedimentoRepository.findById(dto.getIdTipoProcedimento()).orElseThrow();
		pm.setDescricao(dto.getDescricao());
		pm.setDtRegistro(LocalDate.now());
		pm.setDtProcedimento(dto.getDtProcedimento());
		pm.setTipoProcedimento(tp);
		pm.setUser(paciente);
		this.procedimentoMedicoRepository.save(pm);
	}

	public ProcedimentoMedicoFormDTO update(ProcedimentoMedicoFormDTO dto) {
		ProcedimentoMedico pm = this.procedimentoMedicoRepository.findById(dto.getId()).orElseThrow();
		TipoProcedimento tp = this.tipoProcedimentoRepository.findById(dto.getIdTipoProcedimento()).orElseThrow();
		
		pm.setDescricao(dto.getDescricao());
		
		pm.setDtProcedimento(dto.getDtProcedimento());
		pm.setTipoProcedimento(tp);
	
		this.procedimentoMedicoRepository.save(pm);
		return new ProcedimentoMedicoFormDTO(pm.getId(), pm.getDescricao(), 
				pm.getDtProcedimento(), pm.getTipoProcedimento().getId());
	}
	
	public ProcedimentoMedicoFormDTO update(ProcedimentoMedicoFormDTO dto, Usuario paciente) {
		
			ProcedimentoMedico pm = this.procedimentoMedicoRepository.findById(dto.getId()).orElseThrow();
			TipoProcedimento tp = this.tipoProcedimentoRepository.findById(dto.getIdTipoProcedimento()).orElseThrow();
			pm.setDescricao(dto.getDescricao());
			pm.setDtProcedimento(dto.getDtProcedimento());
			pm.setTipoProcedimento(tp);
			this.procedimentoMedicoRepository.save(pm);
			return new ProcedimentoMedicoFormDTO(pm.getId(), pm.getDescricao(), 
					pm.getDtProcedimento(), pm.getTipoProcedimento().getId());
		
		
	}

	public void delete(Long id) {
		ProcedimentoMedico pm = this.procedimentoMedicoRepository.findById(id).orElseThrow();
		UserSecurity logado = UsuarioService.authenticated();
		if(logado!=null) {
			Long idUser = logado.getId();
			Usuario usuario = this.userRepository.findById(idUser).orElseThrow();
			if(usuario.equals(pm.getUser())) {
				this.procedimentoMedicoRepository.delete(pm);
			}else {
				throw new DataIntegrityException("Usuário não autorizado a apagar tal recurso");
			}
		}else {
			throw new NoElementException("Usuário inválido, tente logar novamente");
		}
	}
}
