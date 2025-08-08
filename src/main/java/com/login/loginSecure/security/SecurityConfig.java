package com.login.loginSecure.security;

import com.login.loginSecure.model.AppRole;
import com.login.loginSecure.model.Role;
import com.login.loginSecure.model.User;
import com.login.loginSecure.repositories.RoleRepository;
import com.login.loginSecure.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import java.time.LocalDate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, //allows pre & post annotations
        securedEnabled = true,        //enables secure annotation
        jsr250Enabled = true)         //enables roles allowed annotation
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
        Create users for

     */
//    @Bean
//    public CommandLineRunner initData(RoleRepository roleRepository,
//                                      UserRepository userRepository) {
//        return args -> {
//            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
//                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_USER)));
//
//            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
//                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_ADMIN)));
//
//            if (!userRepository.existsByUsername("user1")) {
//                User user1 = new User("user1", "user1@example.com", "{noop}password1");
//                user1.setAccountNonLocked(false);
//                user1.setAccountNotExpired(true);
//                user1.setCredentialsNonExpired(true);
//                user1.setEnabled(true);
//                user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
//                user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
//                user1.setTwoFactorEnabled(false);
//                user1.setSignUpMethod("email");
//                user1.setRole(userRole);
//                userRepository.save(user1);
//            }
//
//            if (!userRepository.existsByUsername("admin")) {
//                User admin = new User("admin", "admin@example.com", "{noop}adminPass");
//                admin.setAccountNonLocked(true);
//                admin.setAccountNotExpired(true);
//                admin.setCredentialsNonExpired(true);
//                admin.setEnabled(true);
//                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
//                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
//                admin.setTwoFactorEnabled(false);
//                admin.setSignUpMethod("email");
//                admin.setRole(adminRole);
//                userRepository.save(admin);
//            }
//        };
//    }

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