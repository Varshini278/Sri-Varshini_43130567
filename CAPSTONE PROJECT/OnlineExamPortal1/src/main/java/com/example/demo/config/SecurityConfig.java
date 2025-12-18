package com.example.demo.config;

import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF is disabled, so 400 is not a security rejection
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/api/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/teacher/**").hasRole("TEACHER")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .defaultSuccessUrl("/loginSuccess", true) 
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            // 1. Check Hardcoded Admin
            if ("admin@gmail.com".equals(email)) {
                return User.builder()
                    .username("admin@gmail.com")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build();
            }

            // 2. Check Student Database
            var student = studentRepo.findByEmail(email);
            if (student != null) {
                return User.builder()
                    .username(student.getEmail())
                    .password(student.getPassword()) 
                    .roles("STUDENT")
                    .build();
            }

            // 3. Check Teacher Database
            var teacher = teacherRepo.findByEmail(email);
            if (teacher != null) {
                return User.builder()
                    .username(teacher.getEmail())
                    .password(teacher.getPassword())
                    .roles("TEACHER")
                    .build();
            }

            throw new UsernameNotFoundException("User not found: " + email);
        };
    }
}