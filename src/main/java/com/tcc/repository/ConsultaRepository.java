package com.tcc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcc.DTO.report.TipoQuantidade;
import com.tcc.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>, ConsultaCriteriaRepository {

	@Query(value = "from Consulta C where dtInicio BETWEEN :dtI AND :dtF")
	public List<Consulta> getAllBetweenDates(@Param("dtI") LocalDateTime dtIncio, @Param("dtF") LocalDateTime dtFim);

	@Query(value = "from Consulta c join fetch c.medico medico where dtInicio BETWEEN :dtI AND :dtF AND medico.id = :idMedico")
	public List<Consulta> getAllBetweenDatesByMedico(@Param("dtI") LocalDateTime dtIncio,
			@Param("dtF") LocalDateTime dtFim, @Param("idMedico") Long idMedico);

	@Query(value = "select new com.tcc.DTO.report.TipoQuantidade(tp.descTipoProcedimento, count(tp)) from Consulta c join  c.tipoProcedimento tp where c.paciente.id = :idPaciente group by tp.descTipoProcedimento")
	public List<TipoQuantidade> getQuantitativoTipoConsulta(@Param("idPaciente") Long idPaciente);

	public Page<Consulta> findAllByMedico_id(Pageable p, Long idMedico);

	public Page<Consulta> findAllByPaciente_id(Pageable p, Long idPaciente);

	public Page<Consulta> findAllByMedico_idAndDtInicioGreaterThanEqualAndDtFimLessThanEqual(Pageable p, Long idMedico,
			LocalDateTime dtIncio, LocalDateTime dtFim);

	public List<Consulta> findAllByPaciente_id(Long idPaciente);

	@Query(value = "select new com.tcc.DTO.report.TipoQuantidade(cast (c.statusConsulta as text), count(c.statusConsulta)) from Consulta c where c.dtInicio > :dtInicio and c.dtInicio <:dtFim group by c.statusConsulta")
	public List<TipoQuantidade> getQuantitativoStatusConsulta(@Param("dtInicio") LocalDateTime dtInicio,
			@Param("dtFim") LocalDateTime dtFim);

	@Query(value = "select new com.tcc.DTO.report.TipoQuantidade(u.nome, count(c)) from Consulta c join c.medico m join m.usuario u where c.dtInicio > :dtInicio and c.dtInicio <:dtFim group by u.nome order by 2 desc")
	public List<TipoQuantidade> getQuantitativoConsultaMedicoMes(@Param("dtInicio") LocalDateTime dtInicio,
			@Param("dtFim") LocalDateTime dtFim);

	@Query(value = "select new com.tcc.DTO.report.TipoQuantidade(u.nome, count(c)) from Consulta c join c.paciente p join p.usuario u where c.dtInicio > :dtInicio and c.dtInicio <:dtFim group by u.nome order by 2 desc")
	public List<TipoQuantidade> getQuantitativoConsultaPacienteMes(@Param("dtInicio") LocalDateTime dtInicio,
			@Param("dtFim") LocalDateTime dtFim);

	@Query(value = "select new com.tcc.DTO.report.TipoQuantidade(e.descEspecializacao, count(c)) from Consulta c join c.medico m join m.especializacoes e where c.dtInicio > :dtInicio and c.dtInicio <:dtFim group by e.descEspecializacao order by 2 desc")
	public List<TipoQuantidade> getQuantitativoConsultaEspecialidadesMes(@Param("dtInicio") LocalDateTime dtInicio,
			@Param("dtFim") LocalDateTime dtFim);
}
