package com.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Clinica;
import com.tcc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
	Optional<Endereco> findByClinicaEndereco(Clinica clinica);
}
