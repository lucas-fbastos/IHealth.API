package com.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{

	List<Paciente> findByUsuario_nomeContainsIgnoreCase(String nome);
}
