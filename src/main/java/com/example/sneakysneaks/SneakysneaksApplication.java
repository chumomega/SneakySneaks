package com.example.sneakysneaks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SneakysneaksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SneakysneaksApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				.apis(RequestHandlerSelectors.any()) 
				// "any" will make documentation for entire API available
				// through Swagger
				// .apis(RequestHandlerSelectors.basePackage("com.example.sneakysneaks.controller"))
				.paths(PathSelectors.any())
				.build();
	}

}