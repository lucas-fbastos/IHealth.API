package com.tcc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.AlergiaDTO;
import com.tcc.domain.Alergia;
import com.tcc.domain.TipoAlergia;
import com.tcc.service.AlergiaService;

@RestController
@RequestMapping("/alergia")
public class AlergiaResource {

	@Autowired
	private AlergiaService alergiaService;
	
	@GetMapping
	public ResponseEntity<?> getAllTiposAlergia(){
		List<TipoAlergia> alergias = this.alergiaService.getAllTipoAlergia();
		if(alergias.isEmpty()) {
			return ResponseEntity.ok(alergias);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody List<AlergiaDTO> alergias){
		List<Alergia> list = this.alergiaService.addAlergia(alergias);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	public ResponseEntity<List<Alergia>> updateAlergia(@RequestBody @Valid List<AlergiaDTO> alergias){
		List<Alergia> list = this.alergiaService.update(alergias);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Void> deleteAlergia(@PathVariable Long id){
		this.alergiaService.deleteAlergia(id);
		return ResponseEntity.noContent().build();
	}
}
