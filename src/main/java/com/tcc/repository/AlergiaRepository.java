package com.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcc.DTO.report.TipoQuantidade;
import com.tcc.domain.Alergia;
import com.tcc.domain.DadosMedicos;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia,Long>{

	List<Alergia> findByDadosMedicos(DadosMedicos dados);

	@Query(value = " Select new com.tcc.DTO.report.TipoQuantidade(t.descTipo, count(a)) from Alergia a join a.tipoAlergia t where a.dadosMedicos.id = :idDadosMedicos group by t.descTipo")
	List<TipoQuantidade> findTotalAlergiasPorTipo(Long idDadosMedicos);
	
}
