package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentActivity extends AppCompatActivity {

    private Button startTestButton;
    private Button catalogButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        startTestButton = (Button)findViewById(R.id.start_button);
        catalogButton = (Button)findViewById(R.id.catalog_button);

        catalogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent new_activity = new Intent(StudentActivity.this, CatalogActivity.class);
                StudentActivity.this.startActivity(new_activity);
            }
        });
    }
}
