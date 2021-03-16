package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Especializacao;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao,Long>{

}
