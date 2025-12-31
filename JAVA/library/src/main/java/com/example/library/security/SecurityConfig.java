package com.example.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Cấu hình JdbcUserDetailsManager
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Bạn không cần set PasswordEncoder vào đây nếu đã khai báo @Bean ở trên,
        // Spring Security sẽ tự động tìm thấy và sử dụng nó.
        return jdbcUserDetailsManager;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails nhan= User.builder()
//                .username("nhan")
//                .password("{noop}123")
//                .roles("EMPLOYE","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(nhan);
//    }
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests(configurer->
                    configurer
                            .requestMatchers("/css/**", "/js/**", "/vendor/**", "/img/**").permitAll()
                            .requestMatchers("/", "/index", "/shop/**", "/theloai", "/chitiet/**","/dangky/**","/dangky").permitAll()
                            .requestMatchers("/admin/**").hasAnyRole("ADMIN","MANAGER")
                            .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                            .requestMatchers("/login").permitAll()
                            .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())
            .formLogin(
                    form->
                            form
                                    .loginPage("/login")
                                    .loginProcessingUrl("/authenticateTheUser")
                                    .defaultSuccessUrl("/index", true) // Đăng nhập xong quay về trang chủ
                                    .permitAll()
            )
            .logout(logout->logout
                    .logoutSuccessUrl("/index") // Đăng xuất xong quay về trang chủ luôn
                    .permitAll()
            );
    return http.build();
}
}
