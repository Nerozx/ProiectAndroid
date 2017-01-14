package com.example.mircea.proiectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mircea.proiectandroid.model.ChoiceTest;
import com.example.mircea.proiectandroid.model.TestAnswer;

/**
 * Created by palti13 on 1/13/2017.
 */

public class TestUtility {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String TEST_TABLE = "TESTE";
    public static final String TEST_ID = "_id";
    public static final String MATERIE_ID = "IDMaterie";
    public static final String QUESTION_NO = "NrIntrebari";
    public static final String TEST_NAME="NumeTest";
    public static final String TEST_AUTHOR="Autor";

    private TestUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public TestUtility(Context context)
    {
        this.context = context;
        dbUtility = new TestUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void writeTest(ChoiceTest choiceTest){
        ContentValues cv=new ContentValues();
        cv.put(TEST_NAME,choiceTest.getTest_name());
        cv.put(TEST_AUTHOR,choiceTest.getTest_author());
        cv.put(QUESTION_NO,choiceTest.getTest_question_no());
        sqLiteDatabase.insert(TEST_TABLE,null,cv);
    }

    public Cursor getAnswers()
    {
        Cursor cursor = sqLiteDatabase.query(TEST_TABLE,null,null,null,null,null,null);
        return cursor;
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
