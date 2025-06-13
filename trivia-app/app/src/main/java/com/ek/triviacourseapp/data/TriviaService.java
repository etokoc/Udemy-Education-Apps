package com.ek.triviacourseapp.data;

import com.ek.triviacourseapp.model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TriviaService {

    @GET("https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json")
    Call<Object[][]> getTriviaQuestions();
}
