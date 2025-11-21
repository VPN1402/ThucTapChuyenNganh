package com.example.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails nhan= User.builder()
                .username("nhan")
                .password("{noop}123")
                .roles("EMPLOYE")
                .build();
        return new InMemoryUserDetailsManager(nhan);
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer.anyRequest().authenticated())
                .formLogin(
                form->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
        )
                .logout(logout->logout.permitAll()
                );
        return http.build();
    }
}
