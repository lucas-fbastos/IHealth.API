package com.tcc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping()
	public ResponseEntity<Especializacao> save(@RequestBody Especializacao especializacao){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.especializacaoService.save(especializacao));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Especializacao> save(@RequestBody Especializacao especializacao, @PathVariable Long id){
		return ResponseEntity.ok(this.especializacaoService.update(especializacao, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.especializacaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
