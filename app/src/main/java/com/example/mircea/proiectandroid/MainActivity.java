package com.example.mircea.proiectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        password_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //de aici poti sa iei textul inserat in pagina de login pentru baza de date
                Log.i("Username",username_field.getText().toString());
                Log.i("Password",password_field.getText().toString());
            }
        });
    }
}
