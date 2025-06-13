package com.ek.triviacourseapp.data;


import com.ek.triviacourseapp.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
