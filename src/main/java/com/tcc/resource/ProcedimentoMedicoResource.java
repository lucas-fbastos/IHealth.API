package com.tcc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.ProcedimentoMedicoDTO;
import com.tcc.DTO.ProcedimentoMedicoFormDTO;
import com.tcc.domain.TipoProcedimento;
import com.tcc.service.ProcedimentoMedicoService;

@RestController
@RequestMapping("/procedimentoMedico")
public class ProcedimentoMedicoResource {

	@Autowired 
	private ProcedimentoMedicoService procedimentoMedicoService;
	
	@GetMapping
	public ResponseEntity<List<ProcedimentoMedicoDTO>> getAll(){
		List<ProcedimentoMedicoDTO> dtos = this.procedimentoMedicoService.getAllByUser();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/tipos")
	public ResponseEntity<List<TipoProcedimento>> getAllTipos(){
		List<TipoProcedimento> tipos = this.procedimentoMedicoService.getTiposProcedimentosMedicos();
		return ResponseEntity.ok(tipos);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ProcedimentoMedicoFormDTO dto){
		this.procedimentoMedicoService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody ProcedimentoMedicoFormDTO dto){
		ProcedimentoMedicoFormDTO saved = this.procedimentoMedicoService.update(dto);
		return ResponseEntity.ok(saved);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		this.procedimentoMedicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
