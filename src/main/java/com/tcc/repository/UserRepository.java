package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {

	@Transactional(readOnly=true)
	public Usuario findByEmail(String email);
}
