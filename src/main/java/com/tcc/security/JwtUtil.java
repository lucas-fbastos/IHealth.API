package com.tcc.security;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tcc.domain.User;

import io.jsonwebtoken.Claims;
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

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims!=null) {
			String userName = (String) claims.get("sub");
			Date expirationDate = Date.from(Instant.ofEpochMilli((long) claims.get("expiration")));
			Date now = new Date(System.currentTimeMillis());
			if(userName!=null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {			
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
		
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims!=null) {
			return (String) claims.get("sub");
		}
		return null;
	}
}
