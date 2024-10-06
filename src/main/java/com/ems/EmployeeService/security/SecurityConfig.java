package com.ems.EmployeeService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF if not needed
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/emp/**").authenticated() // Secure employee API
                                .anyRequest().permitAll() // Allow other requests
                )
                //.oauth2Login(Customizer.withDefaults());
                .oauth2Login(oauth2 -> oauth2
                .loginPage("/oauth2/authorization/okta")
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
        );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Replace "https://dev-67010459.okta.com/oauth2/default" with your issuer URI
        String issuerUri = "https://dev-67010459.okta.com/oauth2/default";
        return NimbusJwtDecoder.withJwkSetUri(issuerUri + "/v1/keys").build();
    }
}
