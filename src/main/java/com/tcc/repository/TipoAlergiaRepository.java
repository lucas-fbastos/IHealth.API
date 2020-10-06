package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.TipoAlergia;

@Repository
public interface TipoAlergiaRepository extends JpaRepository<TipoAlergia,Integer>{

}
