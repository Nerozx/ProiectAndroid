package com.example.mircea.proiectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mircea.proiectandroid.model.Users;

import java.util.ArrayList;
import java.util.List;

public class SearchStudActivity extends AppCompatActivity {

    private ListView listView_stud;
    private EditText search_stud;

    private List<Users> lst_stud=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stud);
    }
}
