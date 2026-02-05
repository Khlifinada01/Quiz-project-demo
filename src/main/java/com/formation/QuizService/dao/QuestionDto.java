package com.formation.QuizService.dao;

import java.util.List;

public class QuestionDto {



        private List<Integer> questionsIds;

        public List<Integer> getQuestionsIds() {
            return questionsIds;
        }

        public void setQuestionsIds(List<Integer> questionsIds) {
            this.questionsIds = questionsIds;
        }
    }

