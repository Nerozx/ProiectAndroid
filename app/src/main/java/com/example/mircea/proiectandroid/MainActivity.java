package com.example.mircea.proiectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button login_button;
    private EditText username_field;
    private EditText password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button=(Button)findViewById(R.id.login_button);
        username_field=(EditText)findViewById(R.id.username_field);
        password_field=(EditText)findViewById(R.id.password_field);
    }
}
