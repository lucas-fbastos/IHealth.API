package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.TipoProcedimento;

@Repository
public interface TipoProcedimentoRepository extends JpaRepository<TipoProcedimento,Long>{

}
