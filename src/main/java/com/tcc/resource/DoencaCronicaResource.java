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
	
	@PostMapping("/{idPaciente}")
	private ResponseEntity<List<DoencaCronica>> save(@Valid @RequestBody List<DoencaCronicaDTO> list,@PathVariable Long idPaciente){
		List<DoencaCronica> saved = this.doencaCronicaService.save(list,idPaciente);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(saved);
	}
	
	@PutMapping("/{idPaciente}")
	public ResponseEntity<List<DoencaCronica>> update(@RequestBody @Valid List<DoencaCronicaDTO> doencas,@PathVariable Long idPaciente){
		List<DoencaCronica> list = this.doencaCronicaService.updateDoenca(doencas,idPaciente);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}/{idPaciente}")
	public ResponseEntity<Void> delete(@PathVariable Long id,@PathVariable Long idPaciente){
		this.doencaCronicaService.deleteDoenca(id,idPaciente);
		return ResponseEntity.noContent().build();
	}
}