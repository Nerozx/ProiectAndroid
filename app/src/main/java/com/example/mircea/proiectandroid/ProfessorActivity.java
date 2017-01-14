package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mircea.proiectandroid.model.Users;

public class ProfessorActivity extends AppCompatActivity {

    private Button search_btn;
    private Button create_btn;
    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        greeting = (TextView)findViewById(R.id.greeting);
        Intent i = getIntent();
        final Users loggedUser = (Users)i.getSerializableExtra("userLogat");
        greeting.setText("Hi, " + loggedUser.getUser_name());
        search_btn=(Button) findViewById(R.id.button_search);
        create_btn=(Button) findViewById(R.id.button_create_test);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent new_activity=new Intent(ProfessorActivity.this,CreateTestActivity.class);
                ProfessorActivity.this.startActivity(new_activity);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent new_activity=new Intent(ProfessorActivity.this,SearchStudActivity.class);
                ProfessorActivity.this.startActivity(new_activity);
            }
        });
    }
}
