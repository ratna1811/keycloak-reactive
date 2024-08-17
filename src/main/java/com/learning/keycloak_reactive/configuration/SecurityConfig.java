package com.learning.keycloak_reactive.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;
import static org.springframework.security.config.Customizer.withDefaults; // Import this@Configuration@EnableWebFluxSecuritypublicclassSecurityConfig {

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/employees/admin").hasRole("admin")
                        .pathMatchers("/employees/user/**").hasRole("user")
                        .anyExchange().authenticated())
                .oauth2Login(withDefaults()) // OAuth2 login configuration
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())) // OAuth2 resource server with JWT support
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF for simplicity
                .build();
    }

}
