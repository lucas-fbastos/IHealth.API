package com.tcc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcc.domain.ProcedimentoMedico;
import com.tcc.domain.User;

@Repository
public interface ProcedimentoMedicoRepository extends JpaRepository<ProcedimentoMedico, Long>{

	List<ProcedimentoMedico> findByUser(User user);
	List<ProcedimentoMedico> findByUserOrderByDtProcedimentoDesc(User user); 
	Page<ProcedimentoMedico> findByUserOrderByDtProcedimentoDesc(User user, Pageable p); 
	@Query(value = "from ProcedimentoMedico t where dtRegistro BETWEEN :dtInicio AND :dtFim and user = :user")
	public List<ProcedimentoMedico> getAllBetweenDates(@Param("dtInicio")LocalDate dtRegistroInicio,@Param("dtFim")LocalDate dtRegistroFim,@Param("user") User user);

}
