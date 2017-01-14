package com.example.mircea.proiectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.mircea.proiectandroid.model.TestQuestion;

/**
 * Created by palti13 on 12/15/2016.
 */

public class QuestionUtility
{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String QUESTION_TABLE="INTREBARI";
    public static final String QUESTION_ID = "_id";
    public static final String QUESTION_TEXT = "TextIntrebare";
    public static final String QUESTION_NOANS = "NrRasp";
    public static final String QUESTION_IDANS = "IDRasp";
    public static final String QUESTION_SCORE = "Punctaj";
    public static final String CREATE_TABLE_QUESTION =
            " create table " + QUESTION_TABLE + "(" + QUESTION_ID
            + "integer primary key , " + QUESTION_TEXT +
                    " text, " + QUESTION_NOANS + " integer, " +
                    QUESTION_IDANS + " integer, " + QUESTION_SCORE + " integer);";

    private DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public QuestionUtility(Context context)
    {
        this.context = context;
        dbUtility = new DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertQuestion(TestQuestion[] questions)
    {
        for(int i=0;i<questions.length;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put(QUESTION_TEXT,questions[i].getQuestion_text());
            cv.put(QUESTION_NOANS,questions[i].getQuestion_no_ans());
            cv.put(QUESTION_SCORE,questions[i].getQuestion_points());
            long id = sqLiteDatabase.insert(QUESTION_TABLE,null,cv);
        }
    }

    public Cursor getQuestions()
    {
        Cursor cursor = sqLiteDatabase.query(QUESTION_TABLE,null,null,null,null,null,null);

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
        db.execSQL(CREATE_TABLE_QUESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers)
    {

    }
}
}