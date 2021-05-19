package com.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.DocumentoMedico;
import com.tcc.domain.Prontuario;

@Repository
public interface DocumentoMedicoRepository extends JpaRepository<DocumentoMedico,Long>{

	List<DocumentoMedico> findByProntuario(Prontuario p);
	
}
