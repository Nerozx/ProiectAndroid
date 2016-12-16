package com.example.mircea.proiectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.mircea.proiectandroid.model.ChoiceTest;
import com.example.mircea.proiectandroid.model.TestAnswer;
import com.example.mircea.proiectandroid.model.TestQuestion;

import java.util.ArrayList;
import java.util.List;

public class CreateTestActivity extends AppCompatActivity {

    private Button addQuestion_btn;
    private Button createTest_btn;
    private EditText subject_box;
    private EditText test_name_box;
    private LinearLayout linearLayout;
    private ChoiceTest choiceTest;
    private TestQuestion testQuestion;
    private TestAnswer testAnswer;

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
                getData();
            }
        });
    }

    private void getData(){
        choiceTest=new ChoiceTest();


        List<String> lst_err=new ArrayList<>();
        if(!subject_box.getText().toString().equals("")){
            choiceTest.setTest_subject(subject_box.getText().toString());
            Log.i("TEST_SUBJECT",choiceTest.getTest_subject());
        }else{
            String subject_err="Please insert a subject!";
            lst_err.add(subject_err);
        }
        if(!test_name_box.getText().toString().equals("")){
            choiceTest.setTest_name(test_name_box.getText().toString());
            Log.i("TEST_NAME",choiceTest.getTest_name());
        }else{
            String name_err="Please insert a name";
            lst_err.add(name_err);
        }
        for(int i=0;i<linearLayout.getChildCount();i++){
            Object v=linearLayout.getChildAt(i);
            if(v instanceof EditText){
                EditText e=(EditText) v;
                if(e.getHint().equals("Insert Question Here")){
                    if(e.getText().length()!=0){
                        testQuestion=new TestQuestion();
                        testQuestion.setQuestion_text(e.getText().toString());
                        Log.i("QUESTION",testQuestion.getQuestion_text());
                    }else{
                        String question_err="You have question(s) left blank";
                        lst_err.add(question_err);
                    }
                }else if(e.getHint().equals("Insert Answer Here!")){
                    testAnswer=new TestAnswer();
                    testAnswer.setAnswer_text(e.getText().toString());
                    Log.i("ANSWER",testAnswer.getAnswer_text());
                }else{
                    String answer_err="You have answer(s) left blank";
                    lst_err.add(answer_err);
                }
            }
        }

    }
}
