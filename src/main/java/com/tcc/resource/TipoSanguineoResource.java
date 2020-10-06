package com.tcc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.domain.TipoSanguineo;
import com.tcc.service.TipoSanguineoService;

@RestController
@RequestMapping("/tipoSanguineo")
public class TipoSanguineoResource {

	@Autowired
	private TipoSanguineoService tipoSanguineoService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<TipoSanguineo> resultList = this.tipoSanguineoService.getAll();
		if(resultList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(resultList);
	}
}
