package com.formation.QuizService.service;


import com.formation.QuizService.dao.*;
import com.formation.QuizService.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImp {


    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuizInterface quizInterface;
    //private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, String title, Integer numquestions) {
        // là pour appeler un api depuis un autre microservice on appelle cette interface de feign et on passe els paramétres que attend l'api
        List<Integer> questionsIds = quizInterface.getQuestionsForQuiz(category, numquestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setQuestionsIds(questionsIds);
        quiz.setCategory(category);
        quiz.setTitle(title);
        quizRepo.save(quiz);


        return new ResponseEntity("saved", HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer idQuiz) {

        Quiz quiz = quizRepo.findById(idQuiz).get();
        List<Integer> quizQuestionsIds = quiz.getQuestionsIds();
       QuestionDto QuestionDto = new QuestionDto();
        QuestionDto.setQuestionsIds(quizQuestionsIds);
        ResponseEntity<List<QuestionWrapper>> questions=  quizInterface.getQuestions(QuestionDto.getQuestionsIds());
        return ResponseEntity.ok(questions.getBody());
    }
}
