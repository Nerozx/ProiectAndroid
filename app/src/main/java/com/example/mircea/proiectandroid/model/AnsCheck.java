package com.example.mircea.proiectandroid.model;

/**
 * Created by Mircea on 15/1/2017.
 */

public class AnsCheck {
    private int id;
    private int isRight;
    private int question;

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsRight() {
        return isRight;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }
}
