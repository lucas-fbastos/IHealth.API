package com.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.DadosMedicos;
import com.tcc.domain.DoencaCronica;

@Repository
public interface DoencaCronicaRepository extends JpaRepository<DoencaCronica,Long>{

	List<DoencaCronica> findByDadosMedicos(DadosMedicos dados);

}
