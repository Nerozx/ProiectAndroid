package com.example.mircea.proiectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class CreateTestActivity extends AppCompatActivity {

    private Button addQuestion_btn;
    private Button createTest_btn;
    private EditText subject_box;
    private EditText test_name_box;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);
        addQuestion_btn=(Button) findViewById(R.id.question_adder);
        createTest_btn=(Button) findViewById(R.id.button_create_test);
        subject_box=(EditText) findViewById(R.id.subject_box);
        test_name_box=(EditText) findViewById(R.id.test_name_box);
        linearLayout=(LinearLayout) findViewById(R.id.question_zone);
        addQuestion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText questionzone=new EditText(CreateTestActivity.this);
                Switch multipleans_switch=new Switch(CreateTestActivity.this);
                Button add_answer=new Button(CreateTestActivity.this);
                questionzone.setHint("Insert Question Here");
                add_answer.setText("Add Answer");
                multipleans_switch.setText("Multiple Correct Answer Question?");

                add_answer.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        EditText answerzone = new EditText(CreateTestActivity.this);
                        answerzone.setHint("Insert Answer Here!");
                        Switch isCorrect=new Switch(CreateTestActivity.this);
                        isCorrect.setText("Correct Answer!");
                        linearLayout.addView(isCorrect);
                        linearLayout.addView(answerzone);
                    }

                });
                linearLayout.addView(multipleans_switch);
                linearLayout.addView(questionzone);
                linearLayout.addView(add_answer);
            }
        });

        createTest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
