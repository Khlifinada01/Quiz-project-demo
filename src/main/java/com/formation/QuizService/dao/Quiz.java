package com.formation.QuizService.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {


     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String title;
    private String category;
    private String nbreQuestion;

    // k tabda one to many de dux cotés donc twali many to many
    //@ManyToMany
    //private List<Question> questions;
    // ki tabda manytomany mais type mahouc entité mais haja unique kima
    // List<Integer> WA9THA TA3ML @ElementCollcetion list<integer> objet moch @manytomany
    @ElementCollection
    private List<Integer> questionsIds;

    public Quiz(int id, List<Integer> questionsids, String category, String title, String nbreQuestion) {
        this.id = id;
        this.questionsIds = questionsids;
        this.category = category;
        this.title = title;
        this.nbreQuestion = nbreQuestion;
    }

    public Quiz() {

    }
}
