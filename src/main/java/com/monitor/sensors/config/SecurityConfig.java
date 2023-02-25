package com.monitor.sensors.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/inject", "/login").permitAll()
                .antMatchers("PATCH", "DELETE", "POST", "/sensors/new", "/sensors/edit/*")
                .hasRole("ADMINISTRATOR")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/sensors", true)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException)
                        -> response.sendRedirect("/sensors"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
