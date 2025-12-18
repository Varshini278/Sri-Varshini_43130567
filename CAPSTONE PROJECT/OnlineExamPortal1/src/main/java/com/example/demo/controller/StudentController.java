package com.example.demo.controller;

import com.example.demo.model.Question;
import com.example.demo.model.Result;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.ResultRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student") 
public class StudentController {

    @Autowired
    private QuestionRepository questionRepo; 

    @Autowired
    private ResultRepository resultRepo; 

    @GetMapping("/dashboard")
    public String showExam(Model model) {
        List<Question> questions = questionRepo.findAll();
        model.addAttribute("questions", questions);
        return "student"; 
    }

    @PostMapping("/submit")
    public String submitExam(@RequestParam Map<String, String> allParams, Principal principal, Model model) {
        int score = 0;
        List<Question> questions = questionRepo.findAll();

        for (Question q : questions) {
            String userAnswer = allParams.get("q_" + q.getId());
            if (userAnswer != null && q.getCorrectAnswer().trim().equalsIgnoreCase(userAnswer.trim())) {
                score++;
            }
        }

        // Identify Student
        String email = (principal != null) ? principal.getName() : "Guest_User";
        
        // Ensure your Result.java has a constructor matching: (String, int, int, LocalDateTime)
        Result examResult = new Result(email, score, questions.size(), LocalDateTime.now());
        resultRepo.save(examResult);

        // Matching result.html variables ${score} and ${total}
        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());

        return "result"; 
    }
}