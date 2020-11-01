package com.tcc.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.DTO.AlergiaDTO;
import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.DTO.DadosUserDTO;
import com.tcc.DTO.DoencaCronicaDTO;
import com.tcc.DTO.MedicamentoDTO;
import com.tcc.DTO.ProcedimentoMedicoDTO;
import com.tcc.DTO.ProcedimentoMedicoFormDTO;
import com.tcc.domain.Alergia;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.DoencaCronica;
import com.tcc.domain.Medicamento;
import com.tcc.domain.ProcedimentoMedico;
import com.tcc.domain.User;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.ProcedimentoMedicoRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.JwtUtil;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.QRCodeException;

@Service
public class QrCodeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired 
	private ProcedimentoMedicoService procedimentoMedicoService;
	
	@Autowired 
	private ProcedimentoMedicoRepository procedimentoMedicoRepository;
	
	@Autowired 
	private DadosMedicosService dadosMedicosService;
	
	@Autowired 
	private AlergiaService alergiaService;
	
	@Autowired
	private DoencaCronicaService doencaCronicaService;

	@Autowired
	private MedicamentoService medicamentoService;
	

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
	
	public DadosUserDTO getDadosMedicosByQRCode(String code) {
		User paciente = this.validateQrCodeGetUser(code);
		DadosMedicos dados = this.dadosMedicosRepository.findByUser(paciente).orElseThrow();
		DadosUserDTO dto = new DadosUserDTO();
		dto.setNome(paciente.getNome());
		dto.setTipoSanguineo(dados.getTipoSanguineo());
		dto.setAltura(dados.getAltura());
		dto.setPeso(dto.getPeso());
		dto.setVlImc(dados.getVlImc());
		dto.setDescImc(dados.getDescImc());
		dto.setAlergias(dados.getAlergias().stream().collect(Collectors.toList()));
		dados.getMedicamentos().forEach(m -> dto.addMedicamento(m));
		dados.getDoencasCronicas().forEach(d-> dto.addDoencasCronicas(d));
		LocalDate now = LocalDate.now();
		LocalDate dtNascimentoUser =  Instant.ofEpochMilli(paciente.getDtNascimento().getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		Integer idade =  now.getYear() - dtNascimentoUser.getYear();
		dto.setIdade(idade);
		if(dados.getProfissionalSaude()!=null) {
			dto.setNomeProfissionalSaude(dados.getProfissionalSaude().getNome());					
		}
		return dto;
	}
	
	public DadosUserDTO updateDadosMedicosByQrCode(String code, DadosMedicosDTO form) {
		User paciente = this.validateQrCodeGetUser(code);
		DadosMedicos dm = this.dadosMedicosService.update(form ,paciente);
		DadosUserDTO dto = new DadosUserDTO();
		dto.setAlergias(dm.getAlergias().stream().collect(Collectors.toList()));
		dto.setAltura(dm.getAltura());
		dto.setDoencasCronicas(dm.getDoencasCronicas().stream().collect(Collectors.toList()));
		dto.setMedicamentos(dm.getMedicamentos().stream().collect(Collectors.toList()));
		dto.setVlImc(dm.getVlImc());
		dto.setDescImc(dm.getDescImc());
		dto.setNome(dm.getUser().getNome());
		dto.setPeso(dm.getPeso());
		dto.setTipoSanguineo(dm.getTipoSanguineo());
		return dto;
		
	}
	
	public void saveProcedimentoByQrCode(ProcedimentoMedicoFormDTO form) {
		User paciente = this.validateQrCodeGetUser(form.getCode());
		this.procedimentoMedicoService.save(form, paciente);
	}
	
	public ProcedimentoMedicoFormDTO updateProcedimentoByQrCode(ProcedimentoMedicoFormDTO form) {
		User paciente = this.validateQrCodeGetUser(form.getCode());
		return this.procedimentoMedicoService.update(form,paciente);
	}
	
	public List<ProcedimentoMedicoDTO> getProcedimentosByQrCode(String code){
		User paciente = this.validateQrCodeGetUser(code);
		List<ProcedimentoMedico> procedimentos = this.procedimentoMedicoRepository.findByUserOrderByDtProcedimentoDesc(paciente);
		List<ProcedimentoMedicoDTO> list = procedimentos.stream().map(p -> new ProcedimentoMedicoDTO(p)).collect(Collectors.toList());
		return list;
		
	}
	
	public Alergia addAlergiaByQrCode(AlergiaDTO alergia) {
		User paciente = this.validateQrCodeGetUser(alergia.getCode());
		return this.alergiaService.addAlergia(alergia,paciente);
	}
	
	public DoencaCronica addDoencaCronicaByQrCode(DoencaCronicaDTO doencaCronica) {
		User paciente = this.validateQrCodeGetUser(doencaCronica.getCode());
		return this.doencaCronicaService.save(doencaCronica,paciente);
	}
	
	public Medicamento addMedicamentoByQrCode(MedicamentoDTO medicamento) {
		User paciente = this.validateQrCodeGetUser(medicamento.getCode());
		return this.medicamentoService.addMedicamentos(medicamento, paciente);
	}
	
	private User validateQrCodeGetUser(String code) {
		JwtUtil util = new JwtUtil();
		if(!util.QRCodetokenValido(code)) {
			throw new QRCodeException("Token Expirado");
		}
		Long id = JwtUtil.getIdFromToken(code);
		return this.userRepository.findById(id).orElseThrow();
	}
}
