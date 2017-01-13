package com.example.mircea.proiectandroid.model;

/**
 * Created by palti13 on 1/13/2017.
 */

public class Catalog {
    private int user_id;
    private int test_id;
    private int materie_id;
    private int punctaj;

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

    public int getMaterie_id() {
        return materie_id;
    }

    public void setMaterie_id(int materie_id) {
        this.materie_id = materie_id;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }
}
