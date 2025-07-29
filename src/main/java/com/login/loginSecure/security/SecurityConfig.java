package com.login.loginSecure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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
                        .requestMatchers("/api/**").permitAll()
                        //Authenticated or not deny access
                        .requestMatchers("/admin/**").denyAll()
                        //authenticate all incoming requests
                        .anyRequest().authenticated());

        http.csrf(AbstractHttpConfigurer::disable);
        //Form base
//        http.formLogin(withDefaults());
        //Make API stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //Basic auth
        http.httpBasic(withDefaults());
        return http.build();
    }

    /*
        InMemoryAuthentication for developing testing app

     */

    /*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        UserDetails userDetails = User.withUsername("user1")
//                .password("{noop}password1")
//                .roles("USER")
//                .build();
        //{noop} tells spring that we are aware there is kno encryption, plain text
        if(!manager.userExists("user1")){
            manager.createUser(User.withUsername("user1")
                            .password("{noop}password1")
                            .roles("USER")
                            .build());
        }
        if(!manager.userExists("admin1")){
            manager.createUser(User.withUsername("admin1")
                    .password("{noop}password1")
                            .roles("ADMIN")
                    .build());
        }
        return manager;
    }
*/
}