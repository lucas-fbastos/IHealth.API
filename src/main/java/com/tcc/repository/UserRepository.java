package com.tcc.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {

	@Transactional(readOnly=true)
	public Usuario findByEmail(String email);

	public Page<Usuario> findAllByPerfisIn(Pageable p, Set<Integer> perfils);
}
