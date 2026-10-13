package com.patientmanagement.hospital.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.patientmanagement.hospital.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider)
            throws Exception {

        http
            .cors(cors ->
                cors.configurationSource(corsConfigurationSource())
            )

            .csrf(csrf -> csrf.disable())

            .authenticationProvider(authenticationProvider)

            .authorizeHttpRequests(auth -> auth

                // Login and logout
                .requestMatchers("/auth/**").permitAll()

                // Patient APIs
                .requestMatchers(HttpMethod.GET, "/hospital/**")
                    .hasAnyRole("ADMIN", "DOCTOR", "RECEPTIONIST")

                .requestMatchers(HttpMethod.POST, "/hospital/**")
                    .hasAnyRole("ADMIN", "RECEPTIONIST")

                .requestMatchers(HttpMethod.PUT, "/hospital/**")
                    .hasAnyRole("ADMIN", "DOCTOR", "RECEPTIONIST")

                .requestMatchers(HttpMethod.DELETE, "/hospital/**")
                    .hasRole("ADMIN")

                // Doctor APIs
                .requestMatchers(HttpMethod.GET, "/doctor/**")
                    .hasAnyRole("ADMIN", "DOCTOR", "RECEPTIONIST")

                .requestMatchers(HttpMethod.POST, "/doctor/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/doctor/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/doctor/**")
                    .hasRole("ADMIN")

                // Appointment APIs
                .requestMatchers(HttpMethod.GET, "/appointment/**")
                    .hasAnyRole("ADMIN", "DOCTOR", "RECEPTIONIST")

                .requestMatchers(HttpMethod.POST, "/appointment/**")
                    .hasAnyRole("ADMIN", "RECEPTIONIST")

                .requestMatchers(HttpMethod.PUT, "/appointment/**")
                    .hasAnyRole("ADMIN", "DOCTOR")

                .requestMatchers(HttpMethod.DELETE, "/appointment/**")
                    .hasRole("ADMIN")

                // Other endpoints require authentication
                .anyRequest().authenticated()
            )

            .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
            "http://localhost:5173",
            "http://localhost:5174"
        ));

        configuration.setAllowedMethods(List.of(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}