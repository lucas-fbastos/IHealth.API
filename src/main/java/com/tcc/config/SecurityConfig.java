package com.tcc.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tcc.repository.UserRepository;
import com.tcc.security.JwtAuthenticationFilter;
import com.tcc.security.JwtAuthorizationFilter;
import com.tcc.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final String[] URLS_LIBERADAS = {
		"/h2-console/**",
		"/test/**"
	};
	
	private static final String[] URLS_LIBERADAS_GET = {
			"/h2-console/**",
			"/user/confirmar"
	};
	
	private static final String[] URLS_LIBERADAS_POST = {
			"/user"
	};
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.csrf().disable();
		http.cors();
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, URLS_LIBERADAS_GET).permitAll()
			.antMatchers(HttpMethod.POST,URLS_LIBERADAS_POST).permitAll()
			.antMatchers(URLS_LIBERADAS).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JwtAuthenticationFilter(jwtUtil, authenticationManager(), this.userRepository));
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil , userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        List<String> origins = new ArrayList<>();
	        origins.add("*");
	        List<String> methods = new ArrayList<>();
	        methods.add("HEAD");
	        methods.add("GET");
	        methods.add("POST");
	        methods.add("PUT");
	        methods.add("DELETE");
	        methods.add("PATCH");
	        methods.add("OPTIONS");
	        
	       configuration.setAllowedOrigins(origins);
	       configuration.setAllowedMethods(methods);
	       configuration.setAllowCredentials(true);
	       configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
	       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	       source.registerCorsConfiguration("/**", configuration);
	       return source;
	    }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
