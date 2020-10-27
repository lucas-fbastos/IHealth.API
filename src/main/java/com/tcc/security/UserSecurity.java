package com.tcc.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tcc.enums.PerfilEnum;

public class UserSecurity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long   id;
	private String email;
	private String senha;
	private List<String> perfil = new ArrayList<>();
	private  Collection<? extends GrantedAuthority> authorities;
	
	public UserSecurity(Long id, String email, String senha, Set<PerfilEnum> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		perfis.forEach(p -> perfil.add(p.getDesc()));
		this.authorities = perfis.stream().map( p -> new SimpleGrantedAuthority(p.getDesc())).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	public List<String> getPerfil() {
		return perfil;
	}

	public void setPerfil(List<String> perfil) {
		this.perfil = perfil;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
