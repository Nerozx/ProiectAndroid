package com.example.mircea.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mircea.proiectandroid.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Intent intent=getIntent();
        String[] catalog=intent.getStringArrayExtra("catalog") ;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, catalog );
        listView=(ListView)findViewById(R.id.catalog_lstv);
        listView.setAdapter(arrayAdapter);

    }
}
