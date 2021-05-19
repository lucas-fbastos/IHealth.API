package com.tcc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.ProntuarioDTO;
import com.tcc.DTO.ProntuarioformDTO;
import com.tcc.service.ProntuarioService;

@RestController()
@RequestMapping("/prontuario")
public class ProntuarioResource {

	@Autowired 
	private ProntuarioService prontuarioService;
	
	@PostMapping("/{idConsulta}")
	private ResponseEntity<ProntuarioDTO> iniciarProntuario(@PathVariable Long idConsulta){
		return ResponseEntity.ok(prontuarioService.inciaAtendimento(idConsulta));
	}
	
	@PutMapping("/finalizar")
	private ResponseEntity<ProntuarioDTO> finalizar(@RequestBody ProntuarioformDTO prontuario){
		return ResponseEntity.ok(prontuarioService.finalizaAtendimento(prontuario));
	}
}
