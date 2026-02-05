package com.formation.QuizService.feign;


import com.formation.QuizService.dao.Answer;
import com.formation.QuizService.dao.QuestionDto;
import com.formation.QuizService.dao.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// c est un client rest que tu lui passe le serveur au quel tu veux te connecter( qu'il est dejà sur eureka) et il le fait pour toi et tu lui passe ente () le nom du service.
// ecrit sur le serveur eureka
@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {

// tu mets les amethodes que tu veux appeler depuis le service appelé depuis eureka en mentionnant juste la declaaration
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numquestions);



    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Answer> responses) ;

    @PostMapping("/getQuestions")

    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questions_Ids);

}
