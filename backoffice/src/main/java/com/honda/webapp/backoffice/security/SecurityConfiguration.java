package com.honda.webapp.backoffice.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/login", "/images/**", "/css/**", "/js/**", "/fonts/**", "/webjars/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
        .anyRequest().hasAuthority("ADMIN"))
        .formLogin(form -> form
            .loginPage("/login"))
        .csrf(csrf -> csrf.disable());

    return http.build();
  }

  @Bean
  CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(List.of("http://localhost:5173"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/v1/**", config);
    return new CorsFilter(source);
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailService());
    // authProvider.setUserDetailsService(inMemoryUserDetailService());

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

  // ; Da commentare per login con db
  // @Bean
  // UserDetailsService inMemoryUserDetailService() {
  // return new InMemoryUserDetailsManager(
  // User.withUsername("admin")
  // .password(passwordEncoder().encode("admin123"))
  // .authorities("ADMIN")
  // .build());
  // }
}
