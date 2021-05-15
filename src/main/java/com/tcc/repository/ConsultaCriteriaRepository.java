package com.tcc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.DTO.filter.ConsultaFilter;
import com.tcc.domain.Consulta;

public interface ConsultaCriteriaRepository {
	
	 Page<Consulta> findByPacienteAndDates(ConsultaFilter consulta, Pageable pageable);
}
