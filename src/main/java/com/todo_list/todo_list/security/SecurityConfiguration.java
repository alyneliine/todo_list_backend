package com.todo_list.todo_list.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.reactive.function.client.WebClient;
import com.todo_list.todo_list.service.CustomUserDetailsService;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
  JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  CustomUserDetailsService CustomUserDetailsService;

  @Bean
  PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(11);
  }

  @Bean
  DaoAuthenticationProvider authProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(CustomUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
               .authenticationProvider(authProvider())
               .build();
  }
  
  @Bean
  WebClient webClient(WebClient.Builder builder) {
      return builder.build();
  }
 
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(withDefaults()).csrf(csrf -> csrf.disable())
      .exceptionHandling(withDefaults())
      .sessionManagement(management -> management
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authorizeHttpRequests(authorize -> authorize
            
            .requestMatchers(PERMITALL_LIST).permitAll()
            .requestMatchers(AUTH_WHITELIST).authenticated()
            
            .anyRequest().denyAll()
          ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
      
    return http.build();
  }

  private static final String[] PERMITALL_LIST = {
    "/api/v1/auth/**",
    "/v3/api-docs/**",
    "/v3/api-docs.yaml",
    "/swagger-ui/**",
    "/swagger-ui.html",
    "/user/login",
    "/user"
  };

  private static final String[] AUTH_WHITELIST = {
    "/todo",
    "/todo/**"
  };

}
