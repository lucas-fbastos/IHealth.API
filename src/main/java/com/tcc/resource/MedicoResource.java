package com.tcc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import com.tcc.DTO.MedicoFormDTO;
import com.tcc.domain.Medico;
import com.tcc.service.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoResource {

	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	public ResponseEntity<Page<MedicoDTO>> getAllPaginado(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, value = 10) Pageable p){
		return ResponseEntity.ok(this.medicoService.getAllPaginado(p));
	}
	
	
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
		return ResponseEntity.ok(this.medicoService.getDTOById(id));
	}
	
	@PostMapping()
	public ResponseEntity<Medico> save(@RequestBody MedicoFormDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.medicoService.save(dto));
	}
	
	@PutMapping()
	public ResponseEntity<Void> update(@RequestBody MedicoFormDTO dto){
		this.medicoService.update(dto);
		return ResponseEntity.noContent().build();
	}
}
