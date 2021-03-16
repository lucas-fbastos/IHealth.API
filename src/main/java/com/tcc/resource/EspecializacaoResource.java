package com.tcc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.domain.Especializacao;
import com.tcc.service.EspecializacaoService;

@RestController
@RequestMapping("/especializacao")
public class EspecializacaoResource {

	@Autowired
	private EspecializacaoService especializacaoService;
	
	@GetMapping()
	public ResponseEntity<List<Especializacao>> getAll(){
		return ResponseEntity.ok(this.especializacaoService.getAll());
	}
}
