package com.tcc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestApi {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	@GetMapping
	public String getPass() {
		return pe.encode("asdqwe123");
	}
}
