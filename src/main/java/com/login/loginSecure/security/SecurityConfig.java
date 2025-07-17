package com.login.loginSecure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
        Replaces the DefaultSecurityFilterChain if implemented

     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //Configure HTTP request
        http.authorizeHttpRequests(request ->
                request
                        //Don't Authenticate public end points
                        .requestMatchers("/public/**").permitAll()
                        //authenticate all incoming requests
                        .anyRequest().authenticated());
        //Form base
//        http.formLogin(withDefaults());
        //Make API stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //Basic auth
        http.httpBasic(withDefaults());
        return http.build();
    }

}
