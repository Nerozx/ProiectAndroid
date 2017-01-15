package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mircea.proiectandroid.database.DatabaseHelper;
import com.example.mircea.proiectandroid.database.TestUtility;
import com.example.mircea.proiectandroid.model.ChoiceTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TestPickerActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_picker);
        DatabaseHelper myDBHelper=new DatabaseHelper(this);
        try {
            myDBHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TestUtility testUtility=new TestUtility(this);
        testUtility.openDB();
        final List<ChoiceTest> choiceTests=testUtility.getTestList();
        String[] list=getListEntry(choiceTests);
        String[] id=getListId(choiceTests);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView=(ListView)findViewById(R.id.testpicker_lstv);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent new_activity=new Intent(TestPickerActivity.this,TestActivity.class);
                new_activity.putExtra("choicetest",choiceTests.get(i));
                TestPickerActivity.this.startActivity(new_activity);

            }
        });

    }

    private String[] getListEntry(List<ChoiceTest> choiceTest_lst){
        String [] listEntry;
        List<String> lst_temp=new ArrayList<>();
        for (ChoiceTest choiceTest:choiceTest_lst) {
            String s=choiceTest.getTest_subject()+": "+choiceTest.getTest_name()+" - "+choiceTest.getTest_author();
            lst_temp.add(s);
        }
        listEntry=new String[lst_temp.size()];
        listEntry=lst_temp.toArray(listEntry);
        return listEntry;
    }

    private String[] getListId(List<ChoiceTest> choiceTest_lst){
        String[] listId;
        List<String> lst_temp=new ArrayList<>();
        for (ChoiceTest choiceTest:choiceTest_lst) {
            String s=String.valueOf(choiceTest.getTest_id());
            lst_temp.add(s);
        }
        listId=new String[lst_temp.size()];
        listId=lst_temp.toArray(listId);
        return listId;
    }
}
