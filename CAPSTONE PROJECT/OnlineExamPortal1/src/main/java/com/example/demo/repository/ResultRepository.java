package com.example.demo.repository;

import com.example.demo.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    
    /**
     * Custom query to fetch all results but sorted by the submission date.
     * This helps the teacher see the newest attempts at the top of the dashboard.
     */
    List<Result> findAllByOrderBySubmissionDateDesc();
}