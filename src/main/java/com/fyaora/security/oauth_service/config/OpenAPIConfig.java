package com.fyaora.security.oauth_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Fyaora Oauth Service Microservice", version = "1.0", description = "Fyaora Oauth Service"))
public class OpenAPIConfig {
}
