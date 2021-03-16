package com.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long>{
		
	public List<Medico> findByEspecializacoes_id(Long id);
}
