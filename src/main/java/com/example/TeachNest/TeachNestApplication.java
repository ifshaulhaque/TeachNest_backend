package com.example.TeachNest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TeachNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeachNestApplication.class, args);
	}

}
