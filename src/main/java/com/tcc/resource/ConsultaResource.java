package com.tcc.resource;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.AgendamentoDTO;
import com.tcc.DTO.ConsultaDTO;
import com.tcc.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaResource {

	@Autowired
	private ConsultaService service;
	
	
	@GetMapping
	public ResponseEntity<Page<ConsultaDTO>> getAll(@PageableDefault(sort = {"id", "dtInicio"}, direction = Sort.Direction.ASC, value = 10) Pageable p){
		return ResponseEntity.ok(this.service.findAll(p));
	}
	
	@GetMapping("/horariosLivres")
	public ResponseEntity<List<LocalTime>> getHorariosLivres(AgendamentoDTO agendamento){
		return ResponseEntity.ok(
				this.service.getHorariosLivres(agendamento));	
	}
	
	@PutMapping("/confirmaConsulta/{id}")
	public ResponseEntity<Void> confirmaConsulta(@PathVariable Long id){
		this.service.confirmaConsulta(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/temporalidade/{indiceTemporalidade}")
	public ResponseEntity<List<ConsultaDTO>> getConsultasTemporalidade(@PathVariable Integer indiceTemporalidade){
		return ResponseEntity.ok(this.service.getConsultasPorTempo(indiceTemporalidade));
	}
	
	@PostMapping
	public ResponseEntity<ConsultaDTO> save(@RequestBody ConsultaDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(this.service.save(dto));
	}
}