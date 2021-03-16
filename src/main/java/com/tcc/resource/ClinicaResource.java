package com.tcc.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.ClinicaDTO;
import com.tcc.domain.Clinica;
import com.tcc.service.ClinicaService;

@RestController
@RequestMapping("/clinica")
public class ClinicaResource {

	@Autowired
	private ClinicaService clinicaService;
	
	@GetMapping()
	public ResponseEntity<Clinica> getClinica(){
		return ResponseEntity.ok(this.clinicaService.getDadosClinica());
	}
	
	@PostMapping()
	public ResponseEntity<Void> save(@RequestBody @Valid ClinicaDTO clinica) {
		this.clinicaService.saveClinica(clinica);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Clinica> update(@RequestBody @Valid ClinicaDTO clinica, @PathVariable Long id){
		return ResponseEntity.ok(this.clinicaService.update(clinica, id));
	}
	

}
