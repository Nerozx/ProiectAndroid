package com.example.mircea.proiectandroid.model;

/**
 * Created by Mircea on 13/12/2016.
 */

public class TestAnswer {
    private String answer_text;
    private int answer_id;
    private boolean answer_right;

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isAnswer_right() {
        return answer_right;
    }

    public void setAnswer_right(boolean answer_right) {
        this.answer_right = answer_right;
    }
}
