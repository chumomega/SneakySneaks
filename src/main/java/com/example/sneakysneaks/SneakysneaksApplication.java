package com.example.sneakysneaks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SneakysneaksApplication {
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

	public static void main(String[] args) {
		SpringApplication.run(SneakysneaksApplication.class, args);
	}
	

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				 .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				//.apis(RequestHandlerSelectors.any()) 
				// "any" will make documentation for entire API available
				// through Swagger
				 .apis(RequestHandlerSelectors.basePackage("com.example.sneakysneaks.controller"))
				.paths(PathSelectors.any())
				.build();
	}

}