package com.tcc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.PacienteDTO;
import com.tcc.DTO.PacienteFilter;
import com.tcc.domain.Paciente;
import com.tcc.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteResource {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping("/pesquisa")
	public ResponseEntity<List<PacienteDTO>> filter(PacienteFilter pacienteFilter){
		return ResponseEntity.ok(this.pacienteService.filter(pacienteFilter));
	}
		
	@PostMapping
	public ResponseEntity<Paciente> save(@RequestBody PacienteDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pacienteService.save(dto));
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody PacienteDTO dto){
		this.pacienteService.update(dto);
		return ResponseEntity.noContent().build();
	}
}
