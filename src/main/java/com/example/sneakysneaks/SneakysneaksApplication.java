package com.example.sneakysneaks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
////@EnableSwagger2
//@ComponentScan("com.example.sneakysneaks.controller")
//@ComponentScan("com.example.sneakysneaks.database")
public class SneakysneaksApplication {
	//private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

	public static void main(String[] args) {
		SpringApplication.run(SneakysneaksApplication.class, args);
	}
}