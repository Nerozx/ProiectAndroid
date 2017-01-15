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
    public static final String QUESTION_MULTCH = "RaspMult";
    public static final String QUESTION_SCORE = "Punctaj";
    public static final String ID_Test="IDTest";


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

    public void insertQuestion(TestQuestion questions)
    {

            ContentValues cv = new ContentValues();
            cv.put(QUESTION_TEXT,questions.getQuestion_text());
            cv.put(QUESTION_SCORE,questions.getQuestion_points());
            cv.put(QUESTION_MULTCH,questions.getQuestion_multch());
            cv.put(ID_Test,questions.getQuestion_id());
            sqLiteDatabase.insert(QUESTION_TABLE,null,cv);

    }

    public String getQuestionId(String column)
    {
        Cursor cursor = sqLiteDatabase.query(QUESTION_TABLE,null,null,null,null, null, null );
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToLast();
        String id = cursor.getString(cursor.getColumnIndex(column));
        cursor.close();
        return id;
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