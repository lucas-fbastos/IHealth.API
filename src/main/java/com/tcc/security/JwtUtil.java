package com.tcc.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tcc.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(User user) {
		Map<String, Object> userData = new HashMap<>();
		userData.put("nome", user.getNome());
		userData.put("dtCadastro", user.getDtCadastro().toString());
		userData.put("expiration",new Date(System.currentTimeMillis() + this.expiration));
		userData.put("sub", user.getEmail());
		
		return Jwts.builder()
				.setClaims(userData)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
}
