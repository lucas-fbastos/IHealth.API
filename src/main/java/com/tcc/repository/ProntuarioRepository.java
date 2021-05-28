package com.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcc.DTO.report.ProntuarioConsultaReport;
import com.tcc.domain.Consulta;
import com.tcc.domain.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario,Long>{


	Optional<Prontuario> findByConsulta(Consulta consulta);
	
	@Query(value = "select new com.tcc.DTO.report.ProntuarioConsultaReport(tp.descTipoProcedimento, c.dtInicio, p.diagnostico ) "
			+ "from Prontuario p join  p.consulta c join c.tipoProcedimento tp where c.paciente.id = :idPaciente order by c.dtInicio desc")
	public List<ProntuarioConsultaReport> getDiagnosticosPorPaciente(@Param("idPaciente") Long idPaciente );

}
