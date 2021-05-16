package com.tcc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.DTO.filter.ColaboradorFilter;
import com.tcc.domain.Usuario;

public interface UserCriteriaRepository {

	Page<Usuario> filterColaborador(ColaboradorFilter filter,Pageable pageable); 
}
