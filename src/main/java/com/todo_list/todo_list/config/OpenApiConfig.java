package com.todo_list.todo_list.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration  
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class OpenApiConfig {
  @Bean
  OpenAPI usersMicroserviceOpenAPI() {
      return new OpenAPI()
              .info(new Info()
                .title("TODO list - API")
                .description("API de para criação de TODOs!")
                .version("2.0")
              );
  }
}