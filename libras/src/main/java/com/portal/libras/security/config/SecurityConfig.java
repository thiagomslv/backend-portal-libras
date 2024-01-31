package com.portal.libras.security.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {
    
    @Bean //Configura a cadeia de execução de filtros do Spring Security.
    SecurityFilterChain defauSecurityFilterChain(HttpSecurity http) throws Exception{
        
        //Não é ncessário nenhum tipo de gerencimento de seção do Spring Security. A seção será validada apenas por token JWT.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {

                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
                    
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));
                    config.setMaxAge(3600L);

                    return config;
                }
            }))
            .csrf(csrf -> csrf.disable())

            //A cadeia de filtros deve estar aqui.
            .authorizeHttpRequests(requests -> requests
            
                .anyRequest().permitAll()
            )
            .formLogin(formLogin -> formLogin.disable())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
