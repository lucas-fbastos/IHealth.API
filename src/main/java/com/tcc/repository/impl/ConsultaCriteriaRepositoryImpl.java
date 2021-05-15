package com.tcc.repository.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tcc.DTO.filter.ConsultaFilter;
import com.tcc.domain.Consulta;
import com.tcc.domain.Especializacao;
import com.tcc.domain.Medico;
import com.tcc.repository.ConsultaCriteriaRepository;

@Repository
public class ConsultaCriteriaRepositoryImpl implements ConsultaCriteriaRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Page<Consulta> findByPacienteAndDates(ConsultaFilter filter, Pageable pageable) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Consulta> cq = cb.createQuery(Consulta.class);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		Root<Consulta> consulta = cq.from(Consulta.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getIdEspecializacao()!=null) {
			Subquery<Especializacao> especializacaoSubquery = cq.subquery(Especializacao.class);
			Root<Consulta> consultaSubRoot = especializacaoSubquery.correlate(consulta);
			Join<Consulta, Medico> consultaMedico = consultaSubRoot.join("medico");  
			Join<Medico, Especializacao> medicoEspecializacao = consultaMedico.join("especializacoes");
			especializacaoSubquery.select(medicoEspecializacao);
			especializacaoSubquery.where(cb.equal(medicoEspecializacao.get("id"), filter.getIdEspecializacao()));
			predicates.add(cb.exists(especializacaoSubquery));
		}
		if(filter.getMedico() != null)
			predicates.add(cb.equal(consulta.get("medico").get("id"), filter.getMedico()));
		if(filter.getPaciente()!=null) 
			predicates.add(cb.equal(consulta.get("paciente").get("id"), filter.getPaciente()));
		if(filter.getDtInicio()!=null)
			predicates.add(cb.greaterThanOrEqualTo(consulta.get("dtInicio"),  LocalDateTime.parse(filter.getDtInicio(), formatter)));
		if(filter.getDtFim()!=null)
			predicates.add(cb.greaterThanOrEqualTo(consulta.get("dtInicio"),  LocalDateTime.parse(filter.getDtFim(), formatter)));
		
		cq.where(predicates.toArray(new Predicate[0]));
	    cq.orderBy(cb.asc(consulta.get("dtInicio")));

	    List<Consulta> result = em.createQuery(cq).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();

	    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	    Root<Consulta> booksRootCount = countQuery.from(Consulta.class);
	    countQuery.select(cb.count(booksRootCount)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

	    Long count = em.createQuery(countQuery).getSingleResult();

	    Page<Consulta> resultSet = new PageImpl<>(result, pageable, count);
	    return resultSet;
	}

}
