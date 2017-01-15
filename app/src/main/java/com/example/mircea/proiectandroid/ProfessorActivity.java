package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mircea.proiectandroid.database.LoginUtility;
import com.example.mircea.proiectandroid.model.Users;

import java.util.ArrayList;
import java.util.List;

public class ProfessorActivity extends AppCompatActivity {

    private Button search_btn;
    private Button create_btn;
    private TextView greeting;
    private List<Users> lst_stud = new ArrayList<>();

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
                new_activity.putExtra("logged",loggedUser);
                ProfessorActivity.this.startActivity(new_activity);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUtility loginUtility = new LoginUtility(ProfessorActivity.this);
                loginUtility.openDB();
                lst_stud=loginUtility.getStudents("1");
                loginUtility.closeDB();
                List<String> lst_users=new ArrayList<String>();
                List<String> lst_users_id=new ArrayList<String>();
                for (Users u: lst_stud) {
                    lst_users.add(u.getUser_name());
                    lst_users_id.add((String.valueOf(u.getUser_id())));
                }
                String[] stud = new String[lst_users.size()];
                stud = lst_users.toArray(stud);
                String[] id=new String[lst_users_id.size()];
                id=lst_users_id.toArray(id);


                Intent new_activity=new Intent(ProfessorActivity.this,SearchStudActivity.class);
                new_activity.putExtra("stud", stud);
                new_activity.putExtra("id",id);
                ProfessorActivity.this.startActivity(new_activity);
            }
        });
    }
}
