package com.tcc.resource;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.AgendamentoDTO;
import com.tcc.DTO.ConsultaDTO;
import com.tcc.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaResource {

	@Autowired
	private ConsultaService service;
	
	@GetMapping("/horariosLivres")
	public ResponseEntity<List<LocalTime>> getHorariosLivres(@RequestParam(value = "agendamento", required = false) AgendamentoDTO agendamento){
		return ResponseEntity.ok(
				this.service.getHorariosLivres(agendamento));
		
	}
	
	@GetMapping("/temporalidade/{id}")
	public ResponseEntity<List<ConsultaDTO>> getConsultasTemporalidade(@PathVariable Integer indiceTemporalidade){
		return ResponseEntity.ok(this.service.getConsultasPorTempo(indiceTemporalidade));
	}
}
