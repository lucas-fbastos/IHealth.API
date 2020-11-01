package com.tcc.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
		List<String> listPerfil = new ArrayList<>();
		user.getPerfis().forEach(p -> listPerfil.add(p.getDesc().substring(5)));
		userData.put("nome", user.getNome());
		userData.put("dtCadastro", user.getDtCadastro().toString());
		userData.put("expiration",new Date(System.currentTimeMillis() + this.expiration));
		userData.put("sub", user.getEmail());
		userData.put("perfis", listPerfil);
		
		return Jwts.builder()
				.setClaims(userData)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public static String generateTokenQRCode(Long id) {
		Map<String, Object> userData = new HashMap<>();
		userData.put("sub",id);
		Long exp = 3600000l;
		userData.put("exp",new Date(System.currentTimeMillis() + exp));
		return Jwts.builder()
				.setClaims(userData)
				.signWith(SignatureAlgorithm.HS512, "SecretApiApplicativo".getBytes())
				.compact();
	}
	
	public static Long getIdFromToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey("SecretApiApplicativo".getBytes()).parseClaimsJws(token).getBody();
			return Long.valueOf(String.valueOf(claims.get("sub")));
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
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
	
	public boolean QRCodetokenValido(String token) {
		Claims claims = Jwts.parser().setSigningKey("SecretApiApplicativo".getBytes()).parseClaimsJws(token).getBody();
		if(claims!=null) {
			
			Date expirationDate = Date.from(Instant.ofEpochMilli((long) claims.get("exp")));
			Date now = new Date(System.currentTimeMillis());
			if(expirationDate != null && now.before(expirationDate)) {
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
