package com.tcc;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TccApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Brasil"));
		SpringApplication.run(TccApplication.class, args);
	}

}
