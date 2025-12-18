package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // 1. Shows your custom login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    // 2. Redirects users based on their role after successful login
    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication auth) {
        // This gets the roles of the logged-in user (e.g., [ROLE_ADMIN])
        String authorities = auth.getAuthorities().toString();
        
        System.out.println("Login Successful! User Authorities: " + authorities);

        // Spring Security usually adds "ROLE_" to the strings from our UserDetailsService
        if (authorities.contains("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (authorities.contains("ROLE_TEACHER")) {
            return "redirect:/teacher/dashboard"; // Ensure this URL matches your TeacherController
        } else if (authorities.contains("ROLE_STUDENT")) {
            return "redirect:/student/dashboard"; // Ensure this URL matches your StudentController
        }
        
        // Fallback
        return "redirect:/login?error";
    }
}