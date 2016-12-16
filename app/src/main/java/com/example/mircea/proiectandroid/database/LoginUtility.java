package com.example.mircea.proiectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.mircea.proiectandroid.model.Users;

/**
 * Created by palti13 on 12/16/2016.
 */

public class LoginUtility {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String USER_TABLE="USERS";
    public static final String USER_ID = "ID";
    public static final String USER_NAME = "Nume";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Parola";
    public static final String USER_TYPE = "Tip Cont";
    public static final String CREATE_TABLE_USER =
            " create table " + USER_TABLE + "(" + USER_ID
                    + "integer primary key autoincrement, " + USER_NAME +
                    " text, " + USER_EMAIL + " text, " +
                    USER_PASSWORD + " text, " + USER_TYPE + " integer);";

    private LoginUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public LoginUtility(Context context)
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

    public void insertUser(Users[] users)
    {
        for(int i=0;i<users.length;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put(USER_NAME,users[i].getUser_name());
            cv.put(USER_EMAIL,users[i].getUser_email());
            cv.put(USER_PASSWORD,users[i].getUser_password());
            long id = sqLiteDatabase.insert(USER_TABLE,null,cv);

            if(id != -1)
                Toast.makeText(context, "ID of inserted row : " + id,
                        Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getQuestions()
    {
        Cursor cursor = sqLiteDatabase.query(USER_TABLE,null,null,null,null,null,null);

        return cursor;
    }




    class DBUtility extends SQLiteOpenHelper
    {
        public DBUtility(Context context)
        {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_TABLE_USER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers)
        {

        }
    }
}