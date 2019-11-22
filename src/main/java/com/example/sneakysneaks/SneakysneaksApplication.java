package com.example.sneakysneaks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
////@EnableSwagger2
public class SneakysneaksApplication {
	//private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

	public static void main(String[] args) {
		SpringApplication.run(SneakysneaksApplication.class, args);
	}
}