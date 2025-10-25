package com.maytri.reality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for testing; enable later if needed
            .authorizeHttpRequests(auth -> auth
                // ✅ Public pages and forms
                .requestMatchers("/", "/index", "/contact", "/enquiry", "/modal-enquiry").permitAll()
                // ✅ Static assets and H2
                .requestMatchers("/images/**", "/css/**", "/js/**", "/h2-console/**").permitAll()
                // ✅ Admin routes are protected
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // ✅ Everything else is allowed
                .anyRequest().permitAll()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/admin/enquiries", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        // for H2 console to work properly
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
