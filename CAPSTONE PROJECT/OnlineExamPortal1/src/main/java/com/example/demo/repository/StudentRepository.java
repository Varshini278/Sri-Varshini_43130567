package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Student; 

public interface StudentRepository extends JpaRepository<Student, Long> { 
    // Change this line
    Student findByEmail(String email);
}