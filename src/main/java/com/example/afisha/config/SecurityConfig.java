package com.example.afisha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final CorsConfigurationSource configurationSource;

    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider, CorsConfigurationSource configurationSource) {
        this.authenticationProvider = authenticationProvider;
        this.configurationSource = configurationSource;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(h -> h
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/authenticate")
                        .defaultSuccessUrl("/")
                        .failureForwardUrl("/auth/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .cors(httpSecurityCorsConfigurer -> {
                    httpSecurityCorsConfigurer.configurationSource(configurationSource);
                })
                .csrf(AbstractHttpConfigurer::disable)// csrf disabled
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/login", "/auth/registration").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/fonts/**", "/images/**").permitAll()
                        .requestMatchers("/events/add").hasAnyRole("ORGANIZER", "ADMIN")
                        .requestMatchers("/users").hasRole("ADMIN")
                        .requestMatchers("/orders").hasRole("ADMIN")
                        .requestMatchers("/events/my").hasRole("ORGANIZER")
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .authenticationProvider(authenticationProvider);

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/error_role.html");
        };
    }
}
