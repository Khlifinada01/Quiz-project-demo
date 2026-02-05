package com.formation.QuizService.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class QuestionWrapper {
    public QuestionWrapper() {
    }

    public QuestionWrapper(Integer id, String questionname, String difficulty, String category) {
        this.id = id;
        this.questionname = questionname;
        this.difficulty = difficulty;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionname;
    private String difficulty;
    private String category;
}
