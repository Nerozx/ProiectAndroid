package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mircea.proiectandroid.database.AnswerUtility;
import com.example.mircea.proiectandroid.database.DatabaseHelper;
import com.example.mircea.proiectandroid.database.QuestionUtility;
import com.example.mircea.proiectandroid.model.ChoiceTest;
import com.example.mircea.proiectandroid.model.TestAnswer;
import com.example.mircea.proiectandroid.model.TestQuestion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private ChoiceTest choiceTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        choiceTest=(ChoiceTest) intent.getSerializableExtra("choicetest");
        linearLayout=(LinearLayout) findViewById(R.id.test_zone);
        getTest();
        createTest();

    }


    private  void getTest(){
        DatabaseHelper myDBHelper=new DatabaseHelper(this);
        try {
            myDBHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        QuestionUtility questionUtil=new QuestionUtility(TestActivity.this);
        questionUtil.openDB();
        AnswerUtility answerUtil=new AnswerUtility(TestActivity.this);
        answerUtil.openDB();
        choiceTest.setTest_question_lst(questionUtil.getQuestion(String.valueOf(choiceTest.getTest_id())));
        for (TestQuestion question:choiceTest.getTest_question_lst()) {
            question.setQuestion_answer_list(answerUtil.getAnswerList(String.valueOf(question.getQuestion_id())));
        }
        questionUtil.closeDB();
        answerUtil.closeDB();
        myDBHelper.close();
    }

    private void createTest(){
        List<TestQuestion> lst_question=choiceTest.getTest_question_lst();
        List<TestAnswer> lst_answer;
        TextView title=new TextView(TestActivity.this);
        title.setText(choiceTest.getTest_subject()+" - "+choiceTest.getTest_name());
        title.setTextSize(25);
        title.setGravity(Gravity.CENTER);
        linearLayout.addView(title);
        for (TestQuestion question:lst_question) {
            lst_answer=question.getQuestion_answer_list();
            TextView question_zone=new TextView(TestActivity.this);
            question_zone.setTextSize(20);
            question_zone.setText(question.getQuestion_text());
            linearLayout.addView(question_zone);
            if(question.getQuestion_multch()==0){
                RadioGroup radiogrup=new RadioGroup(this);
                radiogrup.setOrientation(RadioGroup.VERTICAL);
                for (TestAnswer answer:lst_answer) {
                    RadioButton radio_btn=new RadioButton(this);
                    radio_btn.setText(answer.getAnswer_text());
                    radio_btn.setTextSize(15);
                    radiogrup.addView(radio_btn);
                }
                linearLayout.addView(radiogrup);
            }else if (question.getQuestion_multch()==1){
                for(TestAnswer answer:lst_answer){
                    CheckBox check=new CheckBox(this);
                    check.setText(answer.getAnswer_text());
                    linearLayout.addView(check);
                }
            }
        }
    }
}
