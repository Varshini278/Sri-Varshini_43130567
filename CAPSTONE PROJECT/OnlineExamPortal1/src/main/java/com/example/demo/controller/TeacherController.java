package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Question;          
import com.example.demo.repository.QuestionRepository; 
import com.example.demo.repository.ResultRepository; 

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private ResultRepository resultRepo; 

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Prepare empty question object for the 'Add' form
        model.addAttribute("question", new Question());
        
        // Fetch all questions for the bank
        model.addAttribute("questions", questionRepo.findAll());
        
        // UPDATED: Use your new custom repository method to see newest scores first
        model.addAttribute("results", resultRepo.findAllByOrderBySubmissionDateDesc()); 
        
        return "teacher"; 
    }

    @PostMapping("/save")
    public String saveQuestion(
            @RequestParam("question") String questionText,
            @RequestParam("optionA") String a,
            @RequestParam("optionB") String b,
            @RequestParam("optionC") String c,
            @RequestParam("optionD") String d,
            @RequestParam("correctAnswer") String correct) {
        
        try {
            Question q = new Question();
            q.setQuestion(questionText);
            q.setOptionA(a);
            q.setOptionB(b);
            q.setOptionC(c);
            q.setOptionD(d);
            q.setCorrectAnswer(correct);
            
            questionRepo.save(q);
            return "redirect:/teacher/dashboard?success";
        } catch (Exception e) {
            System.err.println("SAVE ERROR: " + e.getMessage());
            return "redirect:/teacher/dashboard?error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        try {
            questionRepo.deleteById(id);
            return "redirect:/teacher/dashboard?deleted";
        } catch (Exception e) {
            return "redirect:/teacher/dashboard?error";
        }
    }

    @GetMapping("/add")
    public String addQuestion(Model model) {
        return showDashboard(model);
    }
}
