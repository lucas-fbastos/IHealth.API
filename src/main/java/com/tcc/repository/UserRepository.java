package com.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	@Transactional(readOnly=true)
	public User findByEmail(String email);
}
