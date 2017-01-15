package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mircea.proiectandroid.database.CatalogUtility;
import com.example.mircea.proiectandroid.model.Catalog;
import com.example.mircea.proiectandroid.model.Users;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private Button startTestButton;
    private Button catalogButton;
    private TextView greeting;
    private List<Catalog> lst_catalog=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        greeting = (TextView)findViewById(R.id.greet);

        Intent i = getIntent();
        final Users loggedUser = (Users)i.getSerializableExtra("userLogat");
        greeting.setText("Hi, " + loggedUser.getUser_name());

        startTestButton = (Button)findViewById(R.id.start_button);
        catalogButton = (Button)findViewById(R.id.catalog_button);

        catalogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                CatalogUtility catalogUtility=new CatalogUtility(StudentActivity.this);
                catalogUtility.openDB();
                lst_catalog=catalogUtility.getCatalog(String.valueOf(loggedUser.getUser_id()));
                catalogUtility.closeDB();
                String[] catalog=convertList(lst_catalog);
                Intent new_activity = new Intent(StudentActivity.this, CatalogActivity.class);
                new_activity.putExtra("catalog",catalog);
                StudentActivity.this.startActivity(new_activity);
            }
        });

        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_activity=new Intent(StudentActivity.this, TestPickerActivity.class);
                new_activity.putExtra("userLogat",loggedUser);
                StudentActivity.this.startActivity(new_activity);
            }
        });
    }

    private String[] convertList(List<Catalog> lst_catalog){
        String[] catalog;
        List<String> lst_string=new ArrayList<>();
        for (Catalog cat:lst_catalog) {
            String s="";
            s=cat.getMaterie()+" punctaj obtinut: "+cat.getPunctaj();
            lst_string.add(s);
        }
        catalog=new String[lst_string.size()];
        catalog=lst_string.toArray(catalog);
        return catalog;
    }
}
