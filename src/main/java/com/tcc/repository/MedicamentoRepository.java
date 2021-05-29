package com.tcc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{

	public List<Medicamento> findByDadosMedicos(DadosMedicos dadosMedicos);
	public Page<Medicamento> findByDadosMedicos(DadosMedicos dadosMedicos, Pageable p);
}
