package com.tcc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.domain.TipoProcedimento;
import com.tcc.service.TipoProcedimentoService;

@RestController
@RequestMapping("/procedimentoMedico")
public class TipoProcedimentoResource {

	@Autowired
	private TipoProcedimentoService service;
	
	@GetMapping("/tipos")
	public ResponseEntity<List<TipoProcedimento>> getAll(){
		return ResponseEntity.ok(this.service.getAll());
	}
	
}
