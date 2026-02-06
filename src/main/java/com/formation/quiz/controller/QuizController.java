package com.formation.quiz.controller;


import com.formation.quiz.dao.QuestionWrapper;
import com.formation.quiz.dao.QuizDto;
import com.formation.quiz.service.QuizServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

  QuizServiceImp quizServiceImp=new QuizServiceImp();

  @PostMapping("/create")
  public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {

      return quizServiceImp.createQuiz(quizDto.getCategory(),quizDto.getTitle(),quizDto.getNumquestions());
  }
  @PostMapping("/getQuizQuestions/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@PathVariable Integer quizId) {
      return quizServiceImp.getQuizQuestions(quizId);
  }


}
