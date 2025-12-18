package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // NEW
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;         
import com.example.demo.model.Teacher;         
import com.example.demo.repository.StudentRepository; 
import com.example.demo.repository.TeacherRepository; 

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // NEW: Injected to encrypt passwords

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("teachers", teacherRepo.findAll());
        return "admin";
    }

    @PostMapping("/addStudent")
    public String addStudent(Student student) {
        // ENCRYPT PASSWORD BEFORE SAVING
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepo.save(student);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(Teacher teacher) {
        // ENCRYPT PASSWORD BEFORE SAVING
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepo.save(teacher);
        return "redirect:/admin/dashboard";
    }

    // NEW: Delete Student Logic
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // NEW: Delete Teacher Logic
    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}
