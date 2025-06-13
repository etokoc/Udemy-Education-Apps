package com.ek.triviacourseapp.util;

import com.ek.triviacourseapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionParser {
    public static List<Question> parseQuestions(Object[][] data) {
        List<Question> questions = new ArrayList<>();
        for (Object[] item : data) {
            String answer = (String) item[0];
            boolean answerTrue = (Boolean) item[1];
            questions.add(new Question(answer, answerTrue));
        }
        return questions;
    }
}

