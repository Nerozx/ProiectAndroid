package com.example.mircea.proiectandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mircea.proiectandroid.database.CatalogUtility;
import com.example.mircea.proiectandroid.database.DatabaseHelper;
import com.example.mircea.proiectandroid.database.LoginUtility;
import com.example.mircea.proiectandroid.model.Users;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private EditText username_field;
    private EditText password_field;
    private LoginUtility loginUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deleteDatabase("testeGrila.db");
        DatabaseHelper myDbHelper = new DatabaseHelper(this);
        myDbHelper = new DatabaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        } catch (SQLException sqle) {

            try {
                throw sqle;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        loginUtility = new LoginUtility(this);
        loginUtility = loginUtility.openDB();
        login_button = (Button) findViewById(R.id.login_button);
        username_field = (EditText) findViewById(R.id.username_field);
        password_field = (EditText) findViewById(R.id.password_field);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //de aici poti sa iei textul inserat in pagina de login pentru baza de date
                Log.i("Username", username_field.getText().toString());
                Log.i("Password", password_field.getText().toString());
                String userName = username_field.getText().toString();
                String password = password_field.getText().toString();
                String storedPassword = loginUtility.getUser(userName, loginUtility.USER_PASSWORD);

                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    String user_type = loginUtility.getUser(userName, loginUtility.USER_TYPE);
                    if (Integer.valueOf(user_type) == 0) {
                        Users loggedUser = new Users();
                        loggedUser.setUser_name(userName);
                        loggedUser.setUser_id(Integer.valueOf(loginUtility.getUser(userName, loginUtility.USER_ID)));
                        Intent new_activity = new Intent(MainActivity.this, ProfessorActivity.class);
                        new_activity.putExtra("userLogat", loggedUser);
                        MainActivity.this.startActivity(new_activity);
                    } else if (Integer.valueOf(user_type) == 1) {
                        Users loggedUser = new Users();
                        loggedUser.setUser_name(userName);
                        loggedUser.setUser_id(Integer.valueOf(loginUtility.getUser(userName, loginUtility.USER_ID)));
                        Intent new_activity = new Intent(MainActivity.this, StudentActivity.class);
                        new_activity.putExtra("userLogat", loggedUser);
                        MainActivity.this.startActivity(new_activity);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username or Password wrong", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
