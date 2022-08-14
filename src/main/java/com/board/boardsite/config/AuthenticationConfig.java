package com.board.boardsite.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthenticationConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/trip/users/join","/api/trip/users/login").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }
}
