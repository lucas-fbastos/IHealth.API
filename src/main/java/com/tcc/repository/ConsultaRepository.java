package com.tcc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcc.domain.Consulta;


public interface ConsultaRepository extends JpaRepository<Consulta,Long>{

	@Query(value = "from Consulta C where dtInicio BETWEEN :dtI AND :dtF")
	public List<Consulta> getAllBetweenDates(@Param("dtI")LocalDateTime dtIncio,@Param("dtF")LocalDateTime dtFim );
	
	@Query(value = "from Consulta c join fetch c.medico medico where dtInicio BETWEEN :dtI AND :dtF AND medico.id = :idMedico")
	public List<Consulta> getAllBetweenDatesByMedico(@Param("dtI")LocalDateTime dtIncio,@Param("dtF")LocalDateTime dtFim, @Param("idMedico") Long idMedico );

}
