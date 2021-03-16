package com.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Usuario;

public interface DadosMedicosRepository extends JpaRepository<DadosMedicos,Long>{

	public Optional<DadosMedicos> findByUser(Usuario user);
}
