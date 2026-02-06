package com.formation.quiz.service;

import com.formation.quiz.dao.Quiz;
import com.formation.quiz.feign.QuizInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizServiceImplTest {
    @Captor
    ArgumentCaptor<Quiz> quizCaptor;

    @Mock
    QuizInterface quizInterface;
    // cette annotation permet de creer un objet ( quizserviceimpl ) fictif et l'utiliser
    @Mock
    QuizRepo quizRepo;
    // cette annontation dit qq soit les dependances utilisées dans le service en bas ( dans inejct mcoks ) si il est
    // appelé avec @mock donc il sera directement injecté dans le service appelé avec injectmocks
    @InjectMocks
    QuizServiceImp quizServiceImp;

    private static final Logger log =
            LoggerFactory.getLogger(QuizServiceImp.class);
    @Test
    void createQuiz() {
        String category = "javaCategory";
        String title = "title";
        Integer numquestions = 4;

        log.info("Test feign avec category={} et questions={} ",category,numquestions);
        // tu configures le mock avant l'appel
        when(quizInterface.getQuestionsForQuiz(category,numquestions)).thenReturn(ResponseEntity.ok(List.of(1, 2, 3, 4)));

        System.out.println("second test de creation quiz");

// pour tester l'egalité de deux objets stockes avec jpa
        // il ne faut aps tomber en piege que mokito ne stocke rien
        // mais on peut tester ce qui a été envoyé au repository
        // WHEN
        quizServiceImp.createQuiz(category, title, numquestions);


       // ce capture va capture tout appel fait apr repo
        // THEN
        verify(quizRepo).save(quizCaptor.capture());

        Quiz savedQuiz = quizCaptor.getValue();
       if(savedQuiz==null){
           log.error("object returned null",savedQuiz);
       }
        assertEquals(category, savedQuiz.getCategory());
        assertEquals(title, savedQuiz.getTitle());
    }

}




