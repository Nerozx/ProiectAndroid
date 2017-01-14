package com.example.mircea.proiectandroid.model;

/**
 * Created by palti13 on 1/13/2017.
 */

public class Catalog {
    private int user_id;
    private int test_id;
    private String materie;
    private float punctaj;
    private int credite;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public float getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(float punctaj) {
        this.punctaj = punctaj;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public int getCredite() {
        return credite;
    }

    public void setCredite(int credite) {
        this.credite = credite;
    }
}
