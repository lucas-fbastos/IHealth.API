package com.tcc.resource;

import java.util.List;

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

import com.tcc.DTO.MedicoDTO;
import com.tcc.domain.Medico;
import com.tcc.service.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoResource {

	@Autowired
	private MedicoService medicoService;
	
	@GetMapping("/all")
	public ResponseEntity<List<MedicoDTO>> getAll(){
		return ResponseEntity.ok(this.medicoService.getAll());
	}
	
	@GetMapping("/especializacao/{id}")
	public ResponseEntity<List<MedicoDTO>> getByEspecializacao(@PathVariable Long id){
		return ResponseEntity.ok(this.medicoService.getByEspecializacao(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoDTO> getById(@PathVariable Long id){
		return ResponseEntity.ok(this.medicoService.getById(id));
	}
	
	@PostMapping()
	public ResponseEntity<Medico> save(@RequestBody MedicoDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.medicoService.save(dto));
	}
	
	@PutMapping()
	public ResponseEntity<Void> update(@RequestBody MedicoDTO dto){
		this.medicoService.update(dto);
		return ResponseEntity.noContent().build();
	}
}
