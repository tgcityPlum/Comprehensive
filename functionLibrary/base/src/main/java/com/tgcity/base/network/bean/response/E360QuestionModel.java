package com.tgcity.base.network.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class E360QuestionModel {
    private String name;
    private List<Question> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
