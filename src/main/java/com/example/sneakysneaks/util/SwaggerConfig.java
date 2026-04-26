package com.example.sneakysneaks.util;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "SneakySneaks API", version = "1.0"))
public class SwaggerConfig {
}
