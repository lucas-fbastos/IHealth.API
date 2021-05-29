package com.tcc.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PutMapping("/{idPaciente}")
	public ResponseEntity<List<Medicamento>> updateMedicamentos(@RequestBody @Valid List<MedicamentoDTO> medicamentos,@PathVariable Long idPaciente){
		List<Medicamento> list = this.medicamentoService.update(medicamentos,idPaciente);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}/{idPaciente}")
	public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id,@PathVariable Long idPaciente){
		this.medicamentoService.deleteMedicamento(id,idPaciente);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<Page<Medicamento>> getMedicamentos(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, value = 10) Pageable p){
		return ResponseEntity.ok(this.medicamentoService.getByPacientePaged(p));
	}
}
