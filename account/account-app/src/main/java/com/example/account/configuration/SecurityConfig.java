package com.example.account.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("samin")
                .password("samin")
//                .password(passwordEncoder().encode("mypass"))
                .authorities("ROLE_ADMIN");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/securityNone")
              .permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .cors().disable()
              .csrf().disable()
              .httpBasic()
              .authenticationEntryPoint(authenticationEntryPoint);
      http.addFilterAfter(new AccountSecurityFilter(), BasicAuthenticationFilter.class);

      http.headers().frameOptions().disable();

      return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
