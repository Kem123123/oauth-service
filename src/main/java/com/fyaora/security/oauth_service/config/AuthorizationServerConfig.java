package com.fyaora.security.oauth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationServerConfig {

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // Apply default OAuth2 Authorization Server configuration
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // Customize security rules (optional)
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }
}
