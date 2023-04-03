package com.middleware.libertymutualmiddlewarechallenge.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Liberty Mutual Insurance API Spec")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .termsOfService("terms")
                        .contact(new Contact().email("simple@gmail.com"))
                        .license(new License().name("GNU"))
                        .version("1.0")
                );
    }
}
