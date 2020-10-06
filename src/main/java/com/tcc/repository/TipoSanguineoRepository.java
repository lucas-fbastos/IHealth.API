package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.TipoSanguineo;

@Repository
public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo,Integer>{

}
