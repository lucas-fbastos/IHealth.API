package com.tcc.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.AlergiaDTO;
import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.DTO.DadosUserDTO;
import com.tcc.DTO.DoencaCronicaDTO;
import com.tcc.DTO.MedicamentoDTO;
import com.tcc.DTO.ProcedimentoMedicoDTO;
import com.tcc.DTO.ProcedimentoMedicoFormDTO;
import com.tcc.domain.Alergia;
import com.tcc.domain.DoencaCronica;
import com.tcc.domain.Medicamento;
import com.tcc.service.QrCodeService;

@RestController
@RequestMapping("/QRCode")
public class QRCodeResource {
	
	@Autowired 
	private QrCodeService qrCodeService;
	
	
	@GetMapping
	public ResponseEntity<?> generateToken(){
		Map<String,String> response = new HashMap<>();
		String token = this.qrCodeService.generateQRCode();
		response.put("token", token);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/doencaCronica")
	public ResponseEntity<?> addDoencaCronica( @Valid @RequestBody DoencaCronicaDTO dto ){
		DoencaCronica dc  = this.qrCodeService.addDoencaCronicaByQrCode(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dc);
	}
	
	@PostMapping("/medicamento")
	public ResponseEntity<?> addMedicamento( @Valid @RequestBody MedicamentoDTO dto ){
		Medicamento m  = this.qrCodeService.addMedicamentoByQrCode(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(m);
	}
	
	@PostMapping("/alergia")
	public ResponseEntity<?> addMedicamento( @Valid @RequestBody AlergiaDTO dto ){
		Alergia a  = this.qrCodeService.addAlergiaByQrCode(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(a);
	}
	
	@PostMapping("/addProcedimentosMedicos")
	public ResponseEntity<Void> addProcedimentosMedicos(@RequestBody @Valid ProcedimentoMedicoFormDTO dto){
		this.qrCodeService.saveProcedimentoByQrCode(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/addDadosMedicos")
	public ResponseEntity<DadosUserDTO> updateDadosMedicos(@RequestBody @Valid DadosMedicosDTO dto){
		DadosUserDTO dados = this.qrCodeService.updateDadosMedicosByQrCode(dto);
		return ResponseEntity.ok(dados);
	}
	
	@PostMapping("/dadosMedicos")
	public ResponseEntity<?> getDadosMedicos(@RequestBody Map<String,String> data){
		DadosUserDTO dto = this.qrCodeService.getDadosMedicosByQRCode(data.get("token"));
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/procedimentosMedicos")
	public ResponseEntity<?> getProcedimentosMedicos(@RequestBody Map<String,String> data){
		List<ProcedimentoMedicoDTO> dto = this.qrCodeService.getProcedimentosByQrCode(data.get("token"));
		return ResponseEntity.ok(dto);
	}
	
	
}