package com.ek.triviacourseapp.data;


import com.ek.triviacourseapp.model.Question;
import com.ek.triviacourseapp.util.QuestionParser;


import java.util.ArrayList;
import java.util.List;

public class Repository {

    TriviaService service;

    public Repository(TriviaService service) {
        this.service = service;
    }

    ArrayList<Question> questionArrayList = new ArrayList<>();

    public void getQuestions(final AnswerListAsyncResponse callBack) {
        service.getTriviaQuestions().enqueue(new retrofit2.Callback<>() {
            @Override
            public void onResponse(retrofit2.Call<Object[][]> call, retrofit2.Response<Object[][]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    questionArrayList.clear(); // Listeyi temizle
                    questionArrayList.addAll(QuestionParser.parseQuestions(response.body()));
                    callBack.processFinished(questionArrayList);
                } else {
                    System.out.println("Response not successful veya body null: " + response.code() + ", " + response.message());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Object[][]> call, Throwable t) {
                System.out.println("Retrofit onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
