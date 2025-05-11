package com.cesarlead.users.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Users Service API",
                version = "1.0",
                description = "Microservicio de gestión de usuarios",
                contact = @Contact(
                        name = "Cesarlead",
                        url = "https://cesarlead.com",
                        email = "services@cesarlead.com"
                )
        )
)
@Configuration
public class OpenApiConfig {
}
