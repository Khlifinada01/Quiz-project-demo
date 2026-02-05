package com.formation.QuizService.service;

import com.formation.QuizService.dao.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
