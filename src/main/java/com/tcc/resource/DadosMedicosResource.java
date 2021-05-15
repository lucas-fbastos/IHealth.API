package com.tcc.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.DadosMedicosDTO;
import com.tcc.DTO.DadosMedicosUserDTO;
import com.tcc.domain.DadosMedicos;
import com.tcc.service.DadosMedicosService;

@RestController
@RequestMapping("/dadosMedicos")
public class DadosMedicosResource {

	@Autowired
	private DadosMedicosService dadosMedicosService;
	
	@GetMapping
	public ResponseEntity<DadosMedicosUserDTO> getDadosMedicos(){
		DadosMedicosUserDTO dados = this.dadosMedicosService.getDadosMedicos();
		return ResponseEntity.ok(dados);
	}
	
	@PutMapping("/{idPaciente}")
	public ResponseEntity<DadosMedicos> updateDadosMedicos(@RequestBody @Valid DadosMedicosDTO dto, @PathVariable Long idPaciente){
		DadosMedicos dados = this.dadosMedicosService.update(dto,idPaciente);
		return ResponseEntity.ok(dados);
	}
}
