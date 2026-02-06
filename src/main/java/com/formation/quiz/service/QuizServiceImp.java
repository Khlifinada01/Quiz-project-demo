package com.formation.quiz.service;


import com.formation.quiz.dao.*;
import com.formation.quiz.feign.QuizInterface;
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

        Optional<Quiz> quizReturned = quizRepo.findById(idQuiz);
        if(quizReturned.isPresent()) {
            Quiz quiz = quizReturned.get();

            List<Integer> quizQuestionsIds = quiz.getQuestionsIds();

            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestionsIds(quizQuestionsIds);
            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestions(questionDto.getQuestionsIds());
            return ResponseEntity.ok(questions.getBody());
        }
        return null;
    }
}
