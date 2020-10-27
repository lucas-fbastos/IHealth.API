package com.tcc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.DadosUserDTO;
import com.tcc.DTO.DoencaCronicaDTO;
import com.tcc.DTO.MedicamentoDTO;
import com.tcc.DTO.ProcedimentoMedicoDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.ProcedimentoMedico;
import com.tcc.domain.User;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.ProcedimentoMedicoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.JwtUtil;
import com.tcc.security.UserSecurity;

@Service
public class QrCodeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired 
	private ProcedimentoMedicoRepository procedimentoMedicoRepository;
	

	public String generateQRCode(){
		try {
			UserSecurity logado = UserService.authenticated();
			String code = JwtUtil.generateTokenQRCode(logado.getId());
			return code;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public DadosUserDTO decodeQRCode(String code) {
		try {
			Long id = JwtUtil.getIdFromToken(code);
			
			User user = this.userRepository.findById(id).orElseThrow();
			DadosMedicos dados = this.dadosMedicosRepository.findByUser(user).orElseThrow();
			List<ProcedimentoMedico> procedimentos = this.procedimentoMedicoRepository.findByUserOrderByDtProcedimentoDesc(user);
			DadosUserDTO dto = new DadosUserDTO();
			
			dto.setNome(user.getNome());
			dto.setTipoSanguineo(dados.getTipoSanguineo().getDescricaoTipoSanguineo());
			dto.setAltura(dados.getAltura());
			dto.setPeso(dto.getPeso());
			dto.setImc(dados.getVlImc());
			dto.setImcDesc(dados.getDescImc());
			dto.setAlergias(dados.getAlergias().stream().collect(Collectors.toList()));
			dados.getMedicamentos().forEach(m -> dto.addMedicamento(new MedicamentoDTO(m)));
			procedimentos.forEach(p -> dto.addProcedimentoMedico(new ProcedimentoMedicoDTO(p)));
			dados.getDoencasCronicas().forEach(d-> dto.addDoencasCronicas(new DoencaCronicaDTO(d)));
			return dto;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
}
