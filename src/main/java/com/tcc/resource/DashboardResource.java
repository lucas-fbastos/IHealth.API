package com.tcc.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/procedimentos/ultimoMes")
	public ResponseEntity<Map<String,Object>> getProcedimentosUltimoMes(){
		Map<String,Object> items = this.dashboardService.getProcedimentosPorTipoUltimoMes();
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/procedimentos")
	public ResponseEntity<Map<String,Object>> getProcedimentos(){
		Map<String,Object> items = this.dashboardService.getProcedimentosPorTipoGeral();
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/alergias")
	public ResponseEntity<Map<String,Object>> getAlergias(){
		Map<String,Object> items = this.dashboardService.getAlergiasPorTipoGeral();
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/quantitativo")
	public ResponseEntity<Map<String,Integer>> getQuantitativos(){
		Map<String,Integer> items = this.dashboardService.getQuantitativos();
		return ResponseEntity.ok(items);
	}
	
	
}
