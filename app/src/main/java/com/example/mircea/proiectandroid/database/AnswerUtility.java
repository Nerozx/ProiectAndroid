package com.example.mircea.proiectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.mircea.proiectandroid.model.TestAnswer;
import com.example.mircea.proiectandroid.model.TestQuestion;

/**
 * Created by palti13 on 1/13/2017.
 */

public class AnswerUtility {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String ANSWER_TABLE = "RASPUNSURI";
    public static final String ANSWER_ID = "_id";
    public static final String ANSWER_TEXT = "TextRaspuns";
    public static final String ANSWER_CORRECT = "Corect";

    private AnswerUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public AnswerUtility(Context context)
    {
        this.context = context;
        dbUtility = new AnswerUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertAnswer(TestAnswer[] answers)
    {
        for(int i=0;i<answers.length;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put(ANSWER_TEXT,answers[i].getAnswer_text());
            cv.put(ANSWER_CORRECT,answers[i].isAnswer_right());
            long id = sqLiteDatabase.insert(ANSWER_TABLE,null,cv);
        }
    }

    public Cursor getAnswers()
    {
        Cursor cursor = sqLiteDatabase.query(ANSWER_TABLE,null,null,null,null,null,null);

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
