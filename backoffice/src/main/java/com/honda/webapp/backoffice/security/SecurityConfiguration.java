package com.honda.webapp.backoffice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/login", "/images/**", "/css/**", "/js/**", "/fonts/**", "/webjars/**").permitAll()
        .anyRequest().hasAuthority("ADMIN"))
        .formLogin(form -> form
            .loginPage("/login"))
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable());

    return http.build();
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailService());

    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  DatabaseUserDetailService userDetailService() {
    return new DatabaseUserDetailService();
  }

  @Bean
  PasswordEncoder passwordEncoder() {

    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
