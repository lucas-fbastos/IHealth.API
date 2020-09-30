package com.tcc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.MedicamentoDTO;
import com.tcc.domain.Medicamento;
import com.tcc.service.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoResource {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@PostMapping
	public ResponseEntity<List<Medicamento>> addMedicamentos(@RequestBody @Valid List<MedicamentoDTO> medicamentos){
		List<Medicamento> list = this.medicamentoService.addMedicamentos(medicamentos);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	public ResponseEntity<List<Medicamento>> updateMedicamentos(@RequestBody @Valid List<MedicamentoDTO> medicamentos){
		List<Medicamento> list = this.medicamentoService.update(medicamentos);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id){
		this.medicamentoService.deleteMedicamento(id);
		return ResponseEntity.noContent().build();
	}
}
