package com.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Consulta;
import com.tcc.domain.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario,Long>{


	Optional<Prontuario> findByConsulta(Consulta consulta);
}
