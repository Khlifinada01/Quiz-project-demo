package com.formation.QuizService.controller;


import com.formation.QuizService.dao.QuestionWrapper;
import com.formation.QuizService.dao.QuizDto;
import com.formation.QuizService.service.QuizServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

  @Autowired
  private QuizServiceImp quizServiceImp;

  @PostMapping("/create")
  public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {

      return quizServiceImp.createQuiz(quizDto.getCategory(),quizDto.getTitle(),quizDto.getNumquestions());
  }
  @PostMapping("/getQuizQuestions/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@PathVariable Integer quizId) {
      return quizServiceImp.getQuizQuestions(quizId);
  }


}
