package com.tcc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tcc.DTO.filter.ColaboradorFilter;
import com.tcc.domain.Usuario;
import com.tcc.repository.UserCriteriaRepository;

@Repository
public class UserCriteriaRepositoryImpl implements UserCriteriaRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<Usuario> filterColaborador(ColaboradorFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<Usuario> usuario = cq.from(Usuario.class);
	
		if (filter.getPerfil()!=null)
		    predicates.add(usuario.join("perfis").in(filter.getPerfil()));
		
		if(filter.getCpf()!=null)
			predicates.add(cb.equal(usuario.get("cpf"),filter.getCpf()));
		
		if(filter.getEmail()!=null)
			predicates.add(cb.equal(usuario.get("email"),filter.getEmail()));
		
		if(filter.getNome()!=null)
			predicates.add(cb.equal(usuario.get("nome"), filter.getNome()));
		
		cq.where(predicates.toArray(new Predicate[0]));
		cq.orderBy(cb.asc(usuario.get("nome")));
		List<Usuario> result = em.createQuery(cq).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
	    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	    Root<Usuario> booksRootCount = countQuery.from(Usuario.class);
	    countQuery.select(cb.count(booksRootCount)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
	    Long count = em.createQuery(countQuery).getSingleResult();
	    Page<Usuario> resultSet = new PageImpl<>(result, pageable, count);
	    return resultSet;
		
		
	}
	
	
}
