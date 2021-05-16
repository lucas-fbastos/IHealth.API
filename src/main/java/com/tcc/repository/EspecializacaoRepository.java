package com.tcc.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Especializacao;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao,Long>{
	
	List<Especializacao> findByIdIn(Set<Long> id);

	Optional<Especializacao> findByDescEspecializacao(String descEspecializacao);
}
