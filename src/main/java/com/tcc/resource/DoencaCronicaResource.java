package com.tcc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.DoencaCronicaDTO;
import com.tcc.domain.DoencaCronica;
import com.tcc.service.DoencaCronicaService;

@RestController
@RequestMapping("/doencaCronica")
public class DoencaCronicaResource {

	@Autowired
	private DoencaCronicaService doencaCronicaService;
	
	@PostMapping
	private ResponseEntity<List<DoencaCronica>> save(@Valid @RequestBody List<DoencaCronicaDTO> list){
		List<DoencaCronica> saved = this.doencaCronicaService.save(list);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(saved);
	}
	
	@PutMapping
	public ResponseEntity<List<DoencaCronica>> update(@RequestBody @Valid List<DoencaCronicaDTO> doencas){
		List<DoencaCronica> list = this.doencaCronicaService.updateDoenca(doencas);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.doencaCronicaService.deleteDoenca(id);
		return ResponseEntity.noContent().build();
	}
}