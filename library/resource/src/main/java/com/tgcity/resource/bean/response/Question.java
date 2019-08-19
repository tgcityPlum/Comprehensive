package com.tgcity.resource.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class Question {
    private String id;
    private String title;
    private List<QuestionAnswer> answers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }
}
