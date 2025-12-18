package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;
    private int score;
    private int totalQuestions;
    private LocalDateTime submissionDate;

    public Result() {}

    public Result(String studentEmail, int score, int totalQuestions, LocalDateTime submissionDate) {
        this.studentEmail = studentEmail;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.submissionDate = submissionDate;
    }

    // Standard Getters and Setters for all fields...
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }
    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; }
}