package com.example.mircea.proiectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    private List<String> lst_err=new ArrayList<>();

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
                        int i=linearLayout.indexOfChild(view);
                                    linearLayout.addView(isCorrect,i+1);
                                    linearLayout.addView(answerzone,i+2);
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
                if(lst_err.isEmpty()){
                    Intent new_activity=new Intent(CreateTestActivity.this,TestSuccessActivity.class);
                    CreateTestActivity.this.startActivity(new_activity);
                }else{
                    int i=0;
                    String x="";
                    AlertDialog.Builder builder=new AlertDialog.Builder(CreateTestActivity.this);

                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    for (String s:lst_err) {
                        i++;

                        x=x+i+"."+s+"\n";
                    }
                    builder.setMessage(x);
                    builder.setTitle(i+" Error(s)!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            lst_err.clear();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    private void getData(){
        choiceTest=new ChoiceTest();
        List<TestAnswer> lst_answers=new ArrayList<>();
        List<TestQuestion> lst_question=new ArrayList<>();


        int question_no=0;
        int answer_no=0;

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
                    if(e.getText().length()!=0) {
                        question_no++;
                        testQuestion.setQuestion_text(e.getText().toString());
                        lst_question.add(testQuestion);
                        Log.i("QUESTION", testQuestion.getQuestion_text());
                    }else{
                        question_no++;
                        String question_err="You have question number "+question_no+ "left blank";
                        lst_err.add(question_err);
                    }
                }else if(e.getHint().equals("Insert Answer Here!")) {
                    if (e.getText().length() != 0) {
                        answer_no++;
                        testAnswer.setAnswer_text(e.getText().toString());
                        lst_answers.add(testAnswer);
                        Log.i("ANSWER", testAnswer.getAnswer_text());
                    } else {
                        answer_no++;
                        String answer_err = "You have answer number " + answer_no + " left blank from question" + question_no;
                        lst_err.add(answer_err);
                    }
                }
            }else if(v instanceof Switch){
                Switch f=(Switch) v;
                if(f.getText().equals("Multiple Correct Answer Question?") && question_no==0){
                   testQuestion=new TestQuestion();
                    testQuestion.setQuestion_no_ans(f.isChecked() ? 1:0);
                }else if(f.getText().equals("Multiple Correct Answer Question?") && question_no>=1){
                    testQuestion.setQuestion_answer_list(lst_answers);
                    testQuestion=new TestQuestion();
                    lst_answers=new ArrayList<>();
                    answer_no=0;

                    testQuestion.setQuestion_no_ans(f.isChecked() ? 1:0);

                }
                if(f.getText().equals("Correct Answer!")){
                    testAnswer=new TestAnswer();
                    testAnswer.setAnswer_right(f.isChecked() ? 1:0);
                }

            }
        }
        if(question_no==1){
            testQuestion.setQuestion_answer_list(lst_answers);
        }
        checkTest(lst_err,lst_question);
        choiceTest.setTest_question_lst(lst_question);
        choiceTest.setTest_question_no(question_no);

    }


private void checkTest(List<String> lst_err, List<TestQuestion> lst_question) {
    if (!lst_question.isEmpty()) {
        for (TestQuestion question : lst_question) {
            if (question.getQuestion_no_ans() == 1) {

                if(!question.getQuestion_answer_list().isEmpty()) {
                    int no_correct = 0;
                    int no_answers=0;
                    for (TestAnswer answer : question.getQuestion_answer_list()) {
                        no_answers++;
                        if (answer.isAnswer_right() == 1) {
                            no_correct++;
                        }
                    }
                    if (no_correct == 0) {
                        String err = "You have questions with no correct answers!";
                        lst_err.add(err);
                    } else if (no_correct == 1) {
                        String err = "Please mark 2 or more answers as correct at multiple answer question!";
                        lst_err.add(err);
                    }
                    if(no_answers==1){
                        String err="Please add more than one answer to your question(s)";
                        lst_err.add(err);
                    }
                }else{
                    String err="Please add answers to your question(s)";
                    lst_err.add(err);
                }
            }else {
                if (!question.getQuestion_answer_list().isEmpty()) {
                    int no_correct = 0;
                    int no_answers=0;
                    for (TestAnswer answer : question.getQuestion_answer_list()) {
                        no_answers++;
                        if (answer.isAnswer_right() == 1) {
                            no_correct++;
                        }
                    }
                    if (no_correct == 0) {
                        String err = "You have single answer questions with no correct answers";
                        lst_err.add(err);
                    }
                    if(no_answers==1){
                        String err="Please add more than one answer to your question(s)";
                        lst_err.add(err);
                    }
                }else{
                    String err="Please add answers to your question(s)";
                    lst_err.add(err);
                }
            }
        }
    }
}
}