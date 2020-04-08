package de.smava.homework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_write")
                .pathMatchers(HttpMethod.GET, "/api/**").hasAuthority("SCOPE_read")
                .pathMatchers(HttpMethod.PUT, "/api/**").hasAuthority("SCOPE_write")
                .pathMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("SCOPE_write")
                .pathMatchers(HttpMethod.POST, "/oauth/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt().jwkSetUri("http://localhost:9180/.well-known/jwks.json");
        return http.build();
    }
}
