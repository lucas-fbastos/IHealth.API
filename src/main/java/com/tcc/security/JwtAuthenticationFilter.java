package com.tcc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.DTO.CredentialsDTO;
import com.tcc.domain.Usuario;
import com.tcc.repository.UserRepository;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private JwtUtil jwtUtil;
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	
	public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserRepository userRepository) {
		super();
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}



	@Override
	public Authentication attemptAuthentication( HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException{
		try {
			
			CredentialsDTO dto = new ObjectMapper()
					.readValue(req.getInputStream(), CredentialsDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
			Authentication auth = this.authenticationManager.authenticate(authToken);
			return auth;
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth
			) throws IOException, ServletException {
		
		String username = ((UserSecurity) auth.getPrincipal()).getUsername();
		Usuario user = this.userRepository.findByEmail(username);
		String token = this.jwtUtil.generateToken(user);
		res.addHeader("Authorization", "Bearer "+ token);
		res.addHeader("Content-type", "Application/json");
		res.getWriter().print("{\"token\":\""+token+"\"}");
	}
	
}
