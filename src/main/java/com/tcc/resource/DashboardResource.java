package com.tcc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.AdministradorDashDTO;
import com.tcc.DTO.AuxiliarDashDTO;
import com.tcc.DTO.MedicoDashDTO;
import com.tcc.DTO.PacienteDashDTO;
import com.tcc.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

	@Autowired
	private DashboardService service;
	
	@GetMapping("/paciente")
	public ResponseEntity<PacienteDashDTO> getPacienteReport(){
		return ResponseEntity.ok(service.getDashPaciente());
	}
	
	@GetMapping("/administrador/{idTemporalidade}")
	public ResponseEntity<AdministradorDashDTO> getAdministradorReport(@PathVariable Integer idTemporalidade){
		return ResponseEntity.ok(service.getDashAdministrador(idTemporalidade));
	}
	
	@GetMapping("/auxiliar/{idTemporalidade}")
	public ResponseEntity<AuxiliarDashDTO> getAuxiliarReport(@PathVariable Integer idTemporalidade){
		return ResponseEntity.ok(service.getDashAuxiliar(idTemporalidade));
	}
	
	@GetMapping("/medico/{idTemporalidade}")
	public ResponseEntity<MedicoDashDTO> getMedicoReport(@PathVariable Integer idTemporalidade){
		return ResponseEntity.ok(service.getDashMedico(idTemporalidade));
	}
	
}
