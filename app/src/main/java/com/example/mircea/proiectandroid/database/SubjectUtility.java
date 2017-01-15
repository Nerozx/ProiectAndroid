package com.example.mircea.proiectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mircea on 15/1/2017.
 */

public class SubjectUtility {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String SUBJECT_TABLE = "MATERII";
    public static final String SUBJECT_NAME ="Nume";
    public static final String SUBJECT_ID="_id";

    private SubjectUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SubjectUtility(Context context)
    {
        this.context = context;
        dbUtility = new SubjectUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void writeSubject(String subject){
        ContentValues cv=new ContentValues();
        cv.put(SUBJECT_NAME,subject);
        sqLiteDatabase.insert(SUBJECT_TABLE,null,cv);
    }

    public class DBUtility extends SQLiteOpenHelper
    {
        public DBUtility(Context context)
        {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers)
        {

        }
    }


}
