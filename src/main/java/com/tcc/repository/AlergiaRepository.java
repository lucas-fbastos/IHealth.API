package com.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Alergia;
import com.tcc.domain.DadosMedicos;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia,Long>{

	List<Alergia> findByDadosMedicos(DadosMedicos dados);

}
